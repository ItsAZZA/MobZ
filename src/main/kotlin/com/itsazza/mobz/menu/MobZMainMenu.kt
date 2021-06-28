package com.itsazza.mobz.menu

import com.itsazza.mobz.spawnEgg
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import de.themoep.inventorygui.GuiElementGroup
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

object MobZMainMenu {
    fun open(player: Player) {
        create().show(player)
    }

    private fun create(): InventoryGui {
        val gui = MenuTemplate.create(
            "MobZ Editor",
            MenuTemplate.Type.LIST_MENU
        )

        val listOfMobs = EntityType.values().filter {
            it.isSpawnable && it.isAlive && (it == EntityType.SHEEP || it.spawnEgg() != Material.SHEEP_SPAWN_EGG)
        }.sortedBy {
            it.name
        }

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
        val spawnEggItem = entityType.spawnEgg().item

        return StaticGuiElement(
            '0',
            spawnEggItem,
            {
                val player = it.event.whoClicked as Player
                if (it.event.isLeftClick) {
                    // Opens the menu
                    return@StaticGuiElement true
                } else if (it.event.isRightClick) {
                    player.inventory.addItem(spawnEggItem)
                    return@StaticGuiElement true
                }
                return@StaticGuiElement true
            },
            "§6§l${StringUtil.bountifyCapitalized(entityType.name)}",
            "§7Create spawn eggs, command blocks,",
            "§7spawners and summon for this mob",
            "§0 ",
            "§e§lL-CLICK §7to edit properties",
            "§e§lR-CLICK §7to get spawn egg"
        )
    }
}