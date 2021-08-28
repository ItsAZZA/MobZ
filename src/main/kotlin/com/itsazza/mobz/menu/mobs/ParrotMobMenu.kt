package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import org.bukkit.Material
import org.bukkit.entity.EntityType

class ParrotMobMenu : MobMenu(EntityType.PARROT) {
    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
                parrotColors,
                "Variant",
                "Parrot Color",
                listOf("Defines the colors of", "the parrot mob"),
                Material.PARROT_SPAWN_EGG
            )
        )
    }

    companion object {
        private val parrotColors = arrayOf(
            "Red",
            "Blue",
            "Green",
            "Cyan",
            "Gray"
        )
    }
}