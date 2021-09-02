package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.Material
import org.bukkit.entity.EntityType

class AxolotlMobMenu : MobMenu(EntityType.AXOLOTL) {
    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
                axolotlTypes,
                "Variant",
                "Axolotl Types",
                listOf("Defines the colors of the", "axolotl mob"),
                Material.AXOLOTL_BUCKET
            )
        )
    }

    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.FROM_BUCKET)
    }

    companion object {
        private val axolotlTypes = arrayOf (
            "Pink",
            "Brown",
            "Gold",
            "Light Blue",
            "Purple"
        )
    }
}