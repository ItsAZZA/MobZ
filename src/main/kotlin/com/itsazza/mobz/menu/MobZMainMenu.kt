package com.itsazza.mobz.menu

import com.itsazza.mobz.menu
import com.itsazza.mobz.spawnEgg
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import com.itsazza.mobz.util.mutateMeta
import de.themoep.inventorygui.GuiElementGroup
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta

object MobZMainMenu {
    fun open(player: Player) {
        create().show(player)
    }

    fun create(): InventoryGui {
        val gui = MenuTemplate.create(
            "MobZ Editor",
            MenuTemplate.Type.LIST_MENU
        )

        val listOfMobs = EntityType.values()
            .filter { it.isSpawnable && it.isAlive }
            .sortedBy { it.name }

        val group = GuiElementGroup('0')
        for (mob in listOfMobs) {
            group.addElement(createMobMenuButton(mob))
        }

        gui.addElements(
            group,
            Buttons.close,
            Buttons.nextPage,
            Buttons.previousPage
        )
        gui.setFiller(Material.LIGHT_BLUE_STAINED_GLASS_PANE.item)
        return gui
    }

    private fun createMobMenuButton(entityType: EntityType) : StaticGuiElement {
        val spawnEggItem = entityType.spawnEgg.item

        return StaticGuiElement(
            '0',
            spawnEggItem,
            {
                val player = it.event.whoClicked as Player
                if (it.event.isLeftClick) {
                    entityType.menu?.create()?.show(player) ?: run {
                        player.sendMessage("§cNo menu found for the mob!")
                        player.playSound(player.location, Sound.ENTITY_VILLAGER_NO, 1f, 1f)
                    }
                    return@StaticGuiElement true
                } else if (it.event.isRightClick) {
                    giveSpawnEgg(player, entityType)
                    return@StaticGuiElement true
                }
                return@StaticGuiElement true
            },
            "§6§l${StringUtil.beautifyCapitalized(entityType.name)}",
            "§7Create spawn eggs, command blocks,",
            "§7spawners and summon for this mob",
            "§0 ",
            "§e§lL-CLICK §7to edit properties",
            "§e§lR-CLICK §7to get spawn egg"
        )
    }

    private fun giveSpawnEgg(player: Player, entityType: EntityType) {
        when (entityType.name) {
            "WITHER",
            "SNOWMAN",
            "IRON_GOLEM",
            "ILLUSIONER",
            "GIANT",
            "ENDER_DRAGON",
            "ARMOR_STAND" -> {
                val spawnEgg = Material.SHEEP_SPAWN_EGG.item
                val nbtItem = NBTItem(spawnEgg)
                val tag = NBTContainer("{EntityTag:{id:\"${entityType.key.toString().replace("minecraft:", "")}\"}}")
                nbtItem.mergeCompound(tag)

                player.inventory.addItem(
                    nbtItem.item.mutateMeta<ItemMeta> {
                        it.setDisplayName("§f${StringUtil.beautifyCapitalized(entityType.name)} Spawn Egg")
                    }
                )
            }
            else -> player.inventory.addItem(entityType.spawnEgg.item)
        }
    }
}