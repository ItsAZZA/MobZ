package com.itsazza.mobz.util.menu

import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.tippedArrow
import de.themoep.inventorygui.GuiBackElement
import de.themoep.inventorygui.GuiPageElement
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object Buttons {
    fun createBackButton(gui: InventoryGui) : StaticGuiElement {
        return StaticGuiElement('=',
            tippedArrow(Color.RED),
            {
                val player = it.event.whoClicked as Player
                gui.show(player)
                return@StaticGuiElement true
            },
            "§c§lBack"
        )
    }

    val backInHistory : GuiBackElement
        get() = GuiBackElement('=',
            tippedArrow(Color.RED),
            "§c§lBack"
        )

    val close: StaticGuiElement
        get() = StaticGuiElement('@',
            ItemStack(Material.BARRIER),
            {
                it.gui.destroy()
                return@StaticGuiElement true
            },
            "§c§lClose Menu"
        )

    val previousPage: GuiPageElement
        get() = GuiPageElement(
            '<',
            Material.ARROW.item,
            GuiPageElement.PageAction.PREVIOUS,
            "§6§lPrevious",
            "§7Go to page %prevpage%"
        )

    val nextPage: GuiPageElement
        get() = GuiPageElement(
            '>',
            Material.ARROW.item,
            GuiPageElement.PageAction.NEXT,
            "§6§lNext",
            "§7Go to page %nextpage%"
        )
}