package com.itsazza.mobz.menu.equipment

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import com.itsazza.mobz.util.mutateMeta
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class MobEquipmentMenu(
    val data: NBTContainer,
    private val equipmentData: MutableMap<EquipmentSlot, ItemStack>,
    val menu: MobMenu
) {
    private lateinit var player: Player

    fun open(player: Player) {
        this.player = player
        create().show(player)
    }

    fun create(): InventoryGui {
        val gui = MenuTemplate.create(
            "Armor Menu",
            arrayOf(
                "    1    ",
                "   234   ",
                "    5    ",
                "    6    ",
                "         ",
                "   =@s   "
            )
        )

        gui.addElements(
            Buttons.close,
            Buttons.createBackButton(menu.menuInstance),
            saveButton,
            createEquipmentButton(EquipmentSlot.HEAD, '1', headItems),
            createEquipmentButton(EquipmentSlot.HAND, '2', handItems),
            createEquipmentButton(EquipmentSlot.CHEST, '3', chestItems),
            createEquipmentButton(EquipmentSlot.OFF_HAND, '4', handItems),
            createEquipmentButton(EquipmentSlot.LEGS, '5', legItems),
            createEquipmentButton(EquipmentSlot.FEET, '6', bootItems)
        )

        gui.setFiller(Material.BLACK_STAINED_GLASS_PANE.item)
        return gui
    }

    private fun createEquipmentButton(equipmentSlot: EquipmentSlot, char: Char, materials: List<Material>): StaticGuiElement {
        return StaticGuiElement(
            char,
            equipmentData[equipmentSlot]?.mutateMeta<ItemMeta> {
                it.addItemFlags(
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_POTION_EFFECTS,
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_DESTROYS,
                    ItemFlag.HIDE_UNBREAKABLE
                )
            } ?: Material.LIGHT_GRAY_STAINED_GLASS_PANE.item,
            {
                EquipmentSelectorMenu().open(player, equipmentData, equipmentSlot, data, menu, materials)
                return@StaticGuiElement true
            },
            "§6§l${StringUtil.beautifyCapitalized(equipmentSlot.name)}",
            "§7Select the item in the mob's",
            "§7${StringUtil.beautifyLowerCase(equipmentSlot.name)}",
            "§0 ",
            "§eClick to select!"
        )
    }


    private val saveButton: StaticGuiElement
        get() = StaticGuiElement(
            's',
            Material.GREEN_DYE.item,
            {
                // Do saving stuff
                val player = it.event.whoClicked as Player
                player.sendMessage(NBTItem(this.equipmentData[EquipmentSlot.HAND]).compound.toString())
                return@StaticGuiElement true
            },
            "§2§lSave Equipment",
            "§7Saves the armor and applies",
            "§7it to the ${StringUtil.beautifyLowerCase(menu.mobType.name)} mob",
            "§0 ",
            "§eClick to save!"
        )

    private val headItems = listOf(
        Material.LEATHER_HELMET,
        Material.GOLDEN_HELMET,
        Material.IRON_HELMET,
        Material.CHAINMAIL_HELMET,
        Material.DIAMOND_HELMET,
        Material.NETHERITE_HELMET,
        Material.ZOMBIE_HEAD,
        Material.PLAYER_HEAD,
        Material.DRAGON_HEAD,
        Material.CREEPER_HEAD
    )

    private val handItems = listOf(
        Material.WOODEN_SWORD,
        Material.GOLDEN_SWORD,
        Material.IRON_SWORD,
        Material.DIAMOND_SWORD,
        Material.NETHERITE_SWORD,
        Material.WOODEN_AXE,
        Material.GOLDEN_AXE,
        Material.IRON_AXE,
        Material.DIAMOND_AXE,
        Material.NETHERITE_AXE,
        Material.WOODEN_PICKAXE,
        Material.GOLDEN_PICKAXE,
        Material.IRON_PICKAXE,
        Material.DIAMOND_PICKAXE,
        Material.NETHERITE_PICKAXE,
        Material.WOODEN_SHOVEL,
        Material.GOLDEN_SHOVEL,
        Material.IRON_SHOVEL,
        Material.DIAMOND_SHOVEL,
        Material.NETHERITE_SHOVEL,
        Material.WOODEN_HOE,
        Material.GOLDEN_HOE,
        Material.IRON_HOE,
        Material.DIAMOND_HOE,
        Material.NETHERITE_HOE
    )

    private val chestItems = listOf(
        Material.LEATHER_CHESTPLATE,
        Material.GOLDEN_CHESTPLATE,
        Material.IRON_CHESTPLATE,
        Material.CHAINMAIL_CHESTPLATE,
        Material.DIAMOND_CHESTPLATE,
        Material.NETHERITE_CHESTPLATE
    )

    private val legItems = listOf(
        Material.LEATHER_LEGGINGS,
        Material.GOLDEN_LEGGINGS,
        Material.IRON_LEGGINGS,
        Material.CHAINMAIL_LEGGINGS,
        Material.DIAMOND_LEGGINGS,
        Material.NETHERITE_LEGGINGS
    )

    private val bootItems = listOf(
        Material.LEATHER_BOOTS,
        Material.GOLDEN_BOOTS,
        Material.IRON_BOOTS,
        Material.CHAINMAIL_BOOTS,
        Material.DIAMOND_BOOTS,
        Material.NETHERITE_BOOTS
    )
}