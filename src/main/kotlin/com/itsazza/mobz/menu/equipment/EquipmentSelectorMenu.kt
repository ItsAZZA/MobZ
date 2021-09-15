package com.itsazza.mobz.menu.equipment

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import com.itsazza.mobz.util.mutateMeta
import de.themoep.inventorygui.GuiElementGroup
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class EquipmentSelectorMenu {
    private lateinit var player: Player
    private lateinit var materials: List<Material>
    private lateinit var equipmentData: MutableMap<EquipmentSlot, ItemStack>
    private lateinit var data: NBTContainer
    private lateinit var mobMenu: MobMenu
    private lateinit var equipmentSlot: EquipmentSlot

    fun open(player: Player, equipmentData: MutableMap<EquipmentSlot, ItemStack>, equipmentSlot: EquipmentSlot, data: NBTContainer, mobMenu: MobMenu, materials: List<Material>) {
        this.player = player
        this.materials = materials
        this.equipmentData = equipmentData
        this.data = data
        this.mobMenu = mobMenu
        this.equipmentSlot = equipmentSlot

        create().show(player)
    }

    private fun create(): InventoryGui {
        val gui = MenuTemplate.create(
            "Custom Equipment Menu",
            MenuTemplate.Type.LIST_MENU
        )

        val group = GuiElementGroup('0')
        for (material in materials) {
            group.addElement(createItemSelectButton(material))
        }

        gui.addElements(
            group,
            Buttons.close,
            Buttons.nextPage,
            Buttons.previousPage,
            Buttons.backInHistory
        )
        if (equipmentSlot == EquipmentSlot.HAND || equipmentSlot == EquipmentSlot.OFF_HAND || equipmentSlot == EquipmentSlot.HEAD) {
            gui.addElement(customItemButton)
        }
        gui.setFiller(Material.BLACK_STAINED_GLASS_PANE.item)
        return gui
    }

    private fun createItemSelectButton(material: Material): StaticGuiElement {
        val item = material.item.mutateMeta<ItemMeta> {
            it.addItemFlags(
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }

        return StaticGuiElement(
            '!',
            item,
            {
                equipmentData[equipmentSlot] = material.item
                MobEquipmentMenu(data, equipmentData, mobMenu).open(player)
                return@StaticGuiElement true
            },
            "§6§l${StringUtil.beautifyCapitalized(material.name)}",
            "§7Select this item as item for",
            "§7the ${StringUtil.beautifyCapitalized(equipmentSlot.name)} equipment slot",
            "§0 ",
            "§eClick to select!"
        )
    }

    private val customItemButton : StaticGuiElement
        get() = StaticGuiElement(
            'a',
            Material.OAK_SIGN.item,
            {
                it.gui.destroy()
                CustomItemConversation().start(player, data, equipmentData, equipmentSlot, mobMenu)
                return@StaticGuiElement true
            },
            "§6§lCustom Item",
            "§7Search for another item to",
            "§7use in the equipment slot",
            "§0 ",
            "§eClick to search!"
        )
}