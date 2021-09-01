package com.itsazza.mobz.menu

import com.itsazza.mobz.Mobz
import com.itsazza.mobz.menu.mobs.TraderLlamaMobMenu
import com.itsazza.mobz.spawnEgg
import com.itsazza.mobz.util.*
import com.itsazza.mobz.util.menu.Buttons
import de.themoep.inventorygui.*
import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTEntity
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.ItemMeta

abstract class MobMenu(val mobType: EntityType) {
    open val data: NBTContainer = NBTContainer()
    lateinit var menuInstance: InventoryGui

    private val gui: InventoryGui = InventoryGui(
        Mobz.instance,
        null,
        arrayOf(
            "         ",
            " 0000000 ",
            " 0000000 ",
            " 0000000 ",
            " 0000000 ",
            "< 12=34 >"
        )
    ).also {
        it.setCloseAction { false }
        it.setFiller(Material.GREEN_STAINED_GLASS_PANE.item)
        it.addElements(
            Buttons.createBackButton(MobZMainMenu.create()),
            Buttons.nextPage,
            Buttons.previousPage,
            spawnMobButton,
            spawnEggButton,
            commandBlockButton,
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

    open fun create(): InventoryGui {
        basicMobAttributes.forEach {
            buttons.add(createBasicAttributeButton(it).also { button ->
                button.setState(if (data.hasKey(it.nbtAttribute) || it.dataType == AttributeDataType.BYTE_ZERO) "true" else "false")
            }
            )
        }

        val group = GuiElementGroup('0')
        group.addElements(buttons)

        gui.title = "${StringUtil.beautifyCapitalized(mobType.name)} Menu"
        gui.addElement(group)
        menuInstance = gui
        return gui
    }

    private fun createBasicAttributeButton(attribute: BasicMobAttribute): GuiStateElement {
        val description = attribute.description.map { "§7$it" }.toTypedArray()

        return GuiStateElement(
            '!',
            GuiStateElement.State(
                {
                    when (attribute.dataType) {
                        AttributeDataType.INT -> data.setInteger(attribute.nbtAttribute, 1)
                        AttributeDataType.BYTE -> data.setByte(attribute.nbtAttribute, 1.toByte())
                        AttributeDataType.BYTE_ZERO -> data.removeKey(attribute.nbtAttribute)
                    }
                },
                "true",
                attribute.icon.item.mutateMeta<ItemMeta> {
                    it.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                    it.addEnchant(Enchantment.LUCK, 1, true)
                },
                "§6§l${StringUtil.beautifyCapitalized(attribute.name)}",
                *description,
                "§0 ",
                "§2➤ true §8/ false",
                "§0 ",
                "§eClick to toggle!"
            ),
            GuiStateElement.State(
                {
                    when (attribute.dataType) {
                        AttributeDataType.BYTE_ZERO -> {
                            data.setByte(attribute.nbtAttribute, 0.toByte())
                        }
                        else -> data.removeKey(attribute.nbtAttribute)
                    }
                },
                "false",
                attribute.icon.item.mutateMeta<ItemMeta> {
                    it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                },
                "§6§l${StringUtil.beautifyCapitalized(attribute.name)}",
                *description,
                "§0 ",
                "§8true / §c➤ false",
                "§0 ",
                "§eClick to toggle!"
            )
        )
    }

    open val spawnMobButton: StaticGuiElement
        get() = StaticGuiElement(
            '1',
            Material.HEART_OF_THE_SEA.item,
            {
                val player = it.event.whoClicked as Player
                val loc = player.location
                val world = loc.world ?: return@StaticGuiElement true
                val mob = world.spawnEntity(loc, mobType)
                val nbtEntity = NBTEntity(mob)
                data.removeKey("EntityTag")
                nbtEntity.mergeCompound(data)
                return@StaticGuiElement true
            },
            "§6§lSummon Mob",
            "§7Summons the created mob",
            "§7with these settings at",
            "§7your current location",
            "§0 ",
            "§eClick to summon!"
        )

    open val spawnEggButton: StaticGuiElement
        get() = StaticGuiElement(
            '2',
            mobType.spawnEgg.item,
            {
                val player = it.event.whoClicked as Player
                player.inventory.addItem(NBT.spawnEgg(mobType.spawnEgg, data.toString()))
                return@StaticGuiElement true
            },
            "§6§lSpawn Egg",
            "§7Get a spawn egg with this",
            "§7mob and these settings",
            "§0 ",
            "§eClick to get!"
        )

    private val commandBlockButton: StaticGuiElement
        get() = StaticGuiElement(
            '3',
            Material.COMMAND_BLOCK.item,
            {
                val player = it.event.whoClicked as Player
                val type = mobType.key.toString()
                if (it.event.isLeftClick) {
                    data.removeKey("EntityTag")
                    val command = "minecraft:summon $type ~ ~1 ~ $data"
                    val commandBlock = NBT.commandBlockWithCommand(command)
                    player.inventory.addItem(commandBlock)
                    return@StaticGuiElement true
                } else {
                    val commandBlock = NBT.commandBlockWithSpawnerSetBlock(mobType, data.toString())
                    player.inventory.addItem(commandBlock)
                    return@StaticGuiElement true
                }
            },
            "§6§lCommand Block",
            "§0 ",
            "§7Get a command block with",
            "§7a summon or setblock command",
            "§7for this mob and these settings",
            "§0 ",
            "§e§lL-CLICK §7for summon command",
            "§e§lR-CLICK §7for setblock command"
        )

    fun typeSelector(types: Array<String>, dataID: String, dataName: String, description: List<String>, material: Material, defaultState: String = "Random", offSet: Int = 0): GuiStateElement {
        val states = arrayListOf<GuiStateElement.State>()
        val typeAmount = types.size
        val icon = material.item

        types.forEachIndexed { index, type ->
            states.add(
                GuiStateElement.State(
                    {
                        data.setInteger(dataID, index + offSet)
                    },
                    "${index + offSet}",
                    icon,
                    "§6§l$dataName",
                    *description.map{ "§7$it" }.toTypedArray(),
                    "§0 ",
                    "§7Current: §e$type §8(${index + 1}/${typeAmount})",
                    "§0 ",
                    "§eClick to toggle!"
                )
            )
        }

        return GuiStateElement(
            '!',
            GuiStateElement.State(
                {
                    data.removeKey(dataID)
                },
                "0",
                icon,
                "§6§l$dataName",
                *description.map{ "§7$it" }.toTypedArray(),
                "§0 ",
                "§7Current: §e$defaultState §8(0/${typeAmount})",
                "§0 ",
                "§eClick to toggle!"
            ),
            *states.toTypedArray()
        )
    }
}