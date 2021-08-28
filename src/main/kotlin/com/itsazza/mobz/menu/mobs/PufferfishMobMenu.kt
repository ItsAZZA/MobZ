package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import org.bukkit.Material
import org.bukkit.entity.EntityType

class PufferfishMobMenu : MobMenu(EntityType.PUFFERFISH) {
    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
               pufferFishStates,
               "PuffState",
                "Puff State",
                listOf("Defines the puff state of", "the pufferfish mob"),
                Material.PUFFERFISH,
                "Normal",
                1
            )
        )
    }

    companion object {
        private val pufferFishStates = arrayOf(
            "Partial Buff",
            "Full Buff"
        )
    }
}