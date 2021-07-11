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
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

abstract class MobMenu(private val mobType: EntityType) {
    abstract val data: NBTContainer

    private val gui: InventoryGui = InventoryGui(
        Mobz.instance,
        null,
        arrayOf(
            "         ",
            " 0000000 ",
            " 0000000 ",
            " 0000000 ",
            " 0000000 ",
            "< 12@34 >"
        )
    ).also {
        it.setCloseAction { false }
        it.setFiller(Material.GREEN_STAINED_GLASS_PANE.item)
        it.addElements(
            Buttons.close,
            Buttons.nextPage,
            Buttons.previousPage,
            spawnEggButton,
            commandBlockButton,
            spawnerButton
        )
    }

    open val basicMobAttributes: MutableList<BasicMobAttribute> = mutableListOf(
        BasicMobAttribute.NO_AI,
        BasicMobAttribute.PERSISTENT,
        BasicMobAttribute.INVULNERABLE,
        BasicMobAttribute.SILENT,
        BasicMobAttribute.CAN_PICK_UP_LOOT,
        BasicMobAttribute.GLOWING
    )

    open val buttons: MutableList<GuiElement> = mutableListOf()

    open fun create() : InventoryGui {
        basicMobAttributes.forEach {
            buttons.add(createBasicAttributeButton(it))
        }

        val group = GuiElementGroup('0')
        group.addElements(buttons)

        gui.title = "${StringUtil.bountifyCapitalized(mobType.name)} Menu"
        gui.addElement(group)
        return gui
    }

    private fun createBasicAttributeButton(attribute: BasicMobAttribute) : GuiStateElement {
        val state = if (data.hasKey(attribute.nbtAttribute)) "true" else "false"
        val description = attribute.description.map { "§7$it" }.toTypedArray()

        return GuiStateElement(
            '!',
            state,
            GuiStateElement.State(
                {
                    when (attribute.dataType) {
                        AttributeDataType.INT -> data.setInteger(attribute.nbtAttribute, 1)
                        AttributeDataType.BYTE -> data.setByte(attribute.nbtAttribute, 1.toByte())
                    }
                },
                "true",
                attribute.icon.item.also {
                    val itemMeta = it.itemMeta!!
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    itemMeta.addEnchant(Enchantment.LUCK, 1, true)
                    it.itemMeta = itemMeta
                },
                "§6§l${StringUtil.bountifyCapitalized(attribute.name)}",
                "§0 ",
                *description,
                "§0 ",
                "§2➤ true §8/ false",
                "§0 ",
                "§eClick to toggle!"
            ),
            GuiStateElement.State(
                {
                    data.removeKey(attribute.nbtAttribute)
                },
                "false",
                attribute.icon.item,
                "§6§l${StringUtil.bountifyCapitalized(attribute.name)}",
                "§0 ",
                *description,
                "§0 ",
                "§8true / §c➤ false",
                "§0 ",
                "§eClick to toggle!"
            )
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

    private val commandBlockButton: StaticGuiElement
    get() = StaticGuiElement(
        '3',
        Material.COMMAND_BLOCK.item,
        {
            val player = it.event.whoClicked as Player
            val type = mobType.key.toString()
            val command = "minecraft:summon $type ~ ~1 ~ $data"
            val commandBlock = NBT.commandBlockWithCommand(command)
            player.inventory.addItem(commandBlock)
            return@StaticGuiElement true
        }
    )

    private val spawnerButton: StaticGuiElement
    get() = StaticGuiElement(
        '4',
        Material.SPAWNER.item,
        {
            val player = it.event.whoClicked as Player
            player.inventory.addItem(NBT.spawner(mobType, data.toString()))
            return@StaticGuiElement true
        }
    )
}