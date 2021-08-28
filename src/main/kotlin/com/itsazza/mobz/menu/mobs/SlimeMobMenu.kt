package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import de.themoep.inventorygui.GuiElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

class SlimeMobMenu : MobMenu(EntityType.SLIME) {
    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
                slimeSizes,
                "Size",
                "Slime Size",
                listOf("Defines the size of the", "slime mob"),
                Material.SLIME_BALL
            )
        )
    }

    companion object {
        val slimeSizes = arrayOf(
            "Baby",
            "Medium",
            "Large",
            "Giant"
        )
    }
}