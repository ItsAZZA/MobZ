package com.itsazza.mobz.menu.potion

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import com.itsazza.mobz.util.minecraftID
import com.itsazza.mobz.util.mutateMeta
import de.themoep.inventorygui.GuiElementGroup
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class MobPotionMenu(var data: NBTContainer, val menu: MobMenu, private val potionData: HashSet<PotionEffect> = HashSet()) {
    private lateinit var menuInstance: InventoryGui

    fun open(player: Player) {
        createPotionMenu().show(player)
    }

    private fun createPotionMenu() : InventoryGui {
        val gui = MenuTemplate.create(
            "Mob Potion Effects",
            arrayOf (
                " 0000000 ",
                "000000000",
                "000000000",
                " 0000000 ",
                "         ",
                "   =@1   ",
            )
        )

        val group = GuiElementGroup('0')
        PotionEffectType.values()
            .sortedBy { it.name }
            .forEach {
                group.addElement(createPotionSelectButton(it))
            }

        gui.addElements(
            group,
            saveButton,
            Buttons.close,
            Buttons.createBackButton(menu.menuInstance)
        )
        gui.setFiller(Material.BLACK_STAINED_GLASS_PANE.item)
        menuInstance = gui
        return gui
    }

    @Suppress("DEPRECATION")
    private val saveButton: StaticGuiElement
        get() = StaticGuiElement(
            '1',
            Material.GREEN_DYE.item,
            {
                val player = it.event.whoClicked as Player
                if (potionData.isEmpty()) {
                    menu.menuInstance.show(player)
                    return@StaticGuiElement true
                }
                val potionDataArray = arrayListOf<String>()
                potionData.forEach { potion ->
                    potionDataArray.add("{Id:${potion.type.id},Amplifier:${potion.amplifier},Duration:${potion.duration},Ambient:${if (potion.isAmbient) "1b" else "0b"},ShowParticles:${if (potion.hasParticles()) "1b" else "0b"}}")
                }
                data.removeKey("ActiveEffects")
                data.mergeCompound(NBTContainer("{ActiveEffects:[${potionDataArray.joinToString(",")}]}"))
                menu.apply {this.data = data }.menuInstance.show(player)
                return@StaticGuiElement true
            },
            "§2§lSave Potions",
            "§7Save the current potion",
            "§7effects and apply them to",
            "§7to the ${StringUtil.beautifyCapitalized(menu.mobType.name)} mob",
            "§0 ",
            "§eClick to save!"
        )

    private fun createPotionSelectButton(potionEffectType: PotionEffectType): StaticGuiElement {
        val potion = potionData.firstOrNull { potionEffect -> potionEffect.type == potionEffectType }
        val potionEnabled = potion != null

        return StaticGuiElement(
            '!',
            Material.POTION.item.mutateMeta<PotionMeta> {
                it.color = potionEffectType.color
                if (potionEnabled) {
                    it.addEnchant(Enchantment.LUCK, 1, true)
                    it.addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
                it.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
            },
            {
                val player = it.event.whoClicked as Player
                if (potionEnabled) {
                    if (it.event.isLeftClick) {
                        MobPotionCreateMenu(PotionEffect(potionEffectType, potion!!.duration, potion.amplifier, potion.isAmbient, potion.hasParticles()), menu, data, potionData).open(player)
                    } else {
                        potionData.remove(potion)
                        MobPotionMenu(data, menu, potionData).open(player)
                    }
                    return@StaticGuiElement true
                } else {
                    MobPotionCreateMenu(PotionEffect(potionEffectType, 20, 0, false, false), menu, data, potionData).open(player)
                    return@StaticGuiElement true
                }
            },
            "§6§l${StringUtil.beautifyCapitalized(potionEffectType.name)}",
            "§8${potionEffectType.minecraftID()}",
            "§0 ",
            "§7Modify and apply this",
            "§7potion effect",
            if (potionEnabled) "\n§7Current: §e${StringUtil.beautifyCapitalized(potionEffectType.name)} ${
                potion!!.amplifier.plus(1)
            } §9(${getPotionDuration(potion.duration / 20)})" else "",
            "§0 ",
            if (potionEnabled) "§e§lL-CLICK §7to edit effect\n§e§lR-CLICK §7to remove effect" else "§eClick to select!"
        )
    }
}