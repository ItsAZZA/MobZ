package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.Material
import org.bukkit.entity.EntityType

class LlamaMobMenu: MobMenu(EntityType.LLAMA) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.TAME)
        it.add(BasicMobAttribute.WITH_CHEST)
        it.add(BasicMobAttribute.BABY)
    }

    override val buttons = super.buttons.also {
        it.add(
            typeSelector(
                llamaTypes,
                "Variant",
                "Llama Type",
                listOf("Defines the color of the", "llama mob"),
                Material.LLAMA_SPAWN_EGG
            )
        )
        it.add(
            typeSelector(
                (1..5).map {int -> int.toString() }.toList().toTypedArray(),
                "Strength",
                "Llama Strength",
                listOf("Defines the strength", "of the llama mob"),
                Material.IRON_BLOCK
            )
        )
    }

    companion object {
        val llamaTypes = arrayOf (
            "Cream",
            "White",
            "Brown",
            "Gray"
        )
    }
}