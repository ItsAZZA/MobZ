package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.BasicMobAttribute
import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.item
import de.themoep.inventorygui.StaticGuiElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

object AxolotlMenu : MobMenu(EntityType.AXOLOTL) {
    override val basicMobAttributes = super.basicMobAttributes.also {
            it.add(BasicMobAttribute.BABY)
        }

    override val buttons = super.buttons.also {
        it.add(axolotlTypeSelector)
    }

    private val axolotlTypeSelector: StaticGuiElement
    get() = StaticGuiElement(
        '0',
        Material.AXOLOTL_BUCKET.item,
        {
            // Changes the axolotl type, perhaps toggle option instead of a menu
            return@StaticGuiElement true
        },
        "§6§lAxolotl Type",
        "§0 ",
        "§7Changes the color of",
        "§7axolotl",
        "§0 ",
        "§eClick to toggle!"
    )
}