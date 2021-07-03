package com.itsazza.mobz.menu

import com.itsazza.mobz.AttributeDataType
import com.itsazza.mobz.BasicMobAttribute
import com.itsazza.mobz.Mobz
import com.itsazza.mobz.spawnEgg
import com.itsazza.mobz.util.NBT
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import de.themoep.inventorygui.*
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

abstract class MobMenu(private val mobType: EntityType, private val data: NBTContainer = NBTContainer()) {
    open fun open(player: Player) {
       create().show(player)
    }

    private val gui: InventoryGui = InventoryGui(
        Mobz.instance,
        null,
        "",
        arrayOf(
            "         ",
            " 0000000 ",
            " 0000000 ",
            " 0000000 ",
            " 0000000 ",
            "< 12=34  >"
        )
    ).also {
        it.setCloseAction { false }
        it.setFiller(Material.GREEN_STAINED_GLASS_PANE.item)
        it.addElements(
            Buttons.close,
            Buttons.nextPage,
            Buttons.previousPage,
            spawnEggButton
        )
    }

    open val basicMobAttributes: MutableList<BasicMobAttribute> = mutableListOf(
        BasicMobAttribute.NO_AI,
        BasicMobAttribute.PERSISTENT,
        BasicMobAttribute.INVULNERABLE,
        BasicMobAttribute.SILENT,
        BasicMobAttribute.CAN_PICK_UP_LOOT
    )

    open val buttons: MutableList<GuiElement> = mutableListOf()

    open fun create() : InventoryGui {
        basicMobAttributes.forEach {
            buttons.add(createBasicAttributeButton(it))
        }

        val group = GuiElementGroup('0')
        group.addElements(buttons)
        gui.addElement(group)
        return gui
    }

    private fun createBasicAttributeButton(attribute: BasicMobAttribute) : StaticGuiElement {
        return StaticGuiElement(
            '!',
            attribute.icon.item,
            {
                // Get the NBTContainer and add/remove the element, maybe make it state element
                when (attribute.dataType) {
                    AttributeDataType.INT -> data.setInteger(attribute.nbtAttribute, 1)
                    AttributeDataType.BYTE -> data.setByte(attribute.nbtAttribute, 1.toByte())
                }
                return@StaticGuiElement true
            },
            "§6§l${StringUtil.bountifyCapitalized(attribute.name)}",
            "§0 ",
            "§7Some description"
        )
    }

    open val spawnEggButton: StaticGuiElement
    get() = StaticGuiElement(
        '2',
        mobType.spawnEgg.item,
        {
            val player = it.event.whoClicked as Player
            player.inventory.addItem(NBT.spawnEgg(mobType.spawnEgg, data.toString()))
            return@StaticGuiElement true
        }
    )
}