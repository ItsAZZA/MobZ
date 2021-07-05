package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.item
import de.themoep.inventorygui.GuiStateElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

class AxolotlMenu : MobMenu(EntityType.AXOLOTL) {
    override val buttons = super.buttons.also {
        it.add(axolotlTypeSelector)
    }

    private val axolotlTypeSelector: GuiStateElement
    get() = GuiStateElement(
        '!',
        GuiStateElement.State(
            {
                data.removeKey("Variant")
            },
            "0",
            Material.AXOLOTL_BUCKET.item,
            "§6§lAxolotl Type",
            "§0 ",
            "§7Defines the color of",
            "§7the axolotl mob",
            "§0 ",
            "§2➤ Random",
            "§8Pink",
            "§8Brown",
            "§8Gold",
            "§8Light Blue",
            "§8Purple",
            "§0 ",
            "§eClick to toggle!"
        ),
        GuiStateElement.State(
            {
                data.setInteger("Variant", 0)
            },
            "0",
            Material.AXOLOTL_BUCKET.item,
            "§6§lAxolotl Type",
            "§0 ",
            "§7Defines the color of",
            "§7the axolotl mob",
            "§0 ",
            "§8Random",
            "§2➤ Pink",
            "§8Brown",
            "§8Gold",
            "§8Light Blue",
            "§8Purple",
            "§0 ",
            "§eClick to toggle!"
        ),
        GuiStateElement.State(
            {
                data.setInteger("Variant", 1)
            },
            "1",
            Material.AXOLOTL_BUCKET.item,
            "§6§lAxolotl Type",
            "§0 ",
            "§7Defines the color of",
            "§7the axolotl mob",
            "§0 ",
            "§8Random",
            "§8Pink",
            "§2➤ Brown",
            "§8Gold",
            "§8Light Blue",
            "§8Purple",
            "§0 ",
            "§eClick to toggle!"
        ),
        GuiStateElement.State(
            {
                data.setInteger("Variant", 2)
            },
            "2",
            Material.AXOLOTL_BUCKET.item,
            "§6§lAxolotl Type",
            "§0 ",
            "§7Defines the color of",
            "§7the axolotl mob",
            "§0 ",
            "§8Random",
            "§8Pink",
            "§8Brown",
            "§2➤ Gold",
            "§8Light Blue",
            "§8Purple",
            "§0 ",
            "§eClick to toggle!"
        )
        ,
        GuiStateElement.State(
            {
                data.setInteger("Variant", 3)
            },
            "3",
            Material.AXOLOTL_BUCKET.item,
            "§6§lAxolotl Type",
            "§0 ",
            "§7Defines the color of",
            "§7the axolotl mob",
            "§0 ",
            "§8Random",
            "§8Pink",
            "§8Brown",
            "§8Gold",
            "§2➤ Light Blue",
            "§8Purple",
            "§0 ",
            "§eClick to toggle!"
        ),
        GuiStateElement.State(
            {
                data.setInteger("Variant", 4)
            },
            "4",
            Material.AXOLOTL_BUCKET.item,
            "§6§lAxolotl Type",
            "§0 ",
            "§7Defines the color of",
            "§7the axolotl mob",
            "§0 ",
            "§8Random",
            "§8Pink",
            "§8Brown",
            "§8Gold",
            "§8Light Blue",
            "§2➤ Purple",
            "§0 ",
            "§eClick to toggle!"
        )
    )
}