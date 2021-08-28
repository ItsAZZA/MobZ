package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.Material
import org.bukkit.entity.EntityType

class TraderLlamaMobMenu : MobMenu(EntityType.TRADER_LLAMA) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.TAME)
    }

    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
                LlamaMobMenu.llamaTypes,
                "Variant",
                "Llama Type",
                listOf("Defines the color of", "the trader llama mob"),
                Material.TRADER_LLAMA_SPAWN_EGG
            )
        )
    }
}