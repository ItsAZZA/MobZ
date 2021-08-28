package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import de.themoep.inventorygui.GuiElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

class CatMobMenu : MobMenu(EntityType.CAT) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
    }

    override val buttons: MutableList<GuiElement> = super.buttons.also {
        it.add(
            typeSelector(
                catTypes,
                "CatType",
                "Cat Type",
                listOf("Defines the color of the", "cat mob"),
                Material.CAT_SPAWN_EGG
            )
        )
    }

    companion object {
        private val catTypes = arrayOf (
            "Tabby",
            "Tuxedo",
            "Red Tabby",
            "Siamese",
            "British Shorthaird",
            "Calico",
            "Persian",
            "Ragdoll",
            "White",
            "Jellie",
            "Black"
        )
    }
}