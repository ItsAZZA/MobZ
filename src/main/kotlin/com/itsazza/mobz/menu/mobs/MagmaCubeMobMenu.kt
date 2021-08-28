package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import org.bukkit.Material
import org.bukkit.entity.EntityType

class MagmaCubeMobMenu : MobMenu(EntityType.MAGMA_CUBE) {
    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
                SlimeMobMenu.slimeSizes,
                "Size",
                "Magma Cube Size",
                listOf("Defines the size of the", "magma cream mob"),
                Material.MAGMA_CREAM
            )
        )
    }
}