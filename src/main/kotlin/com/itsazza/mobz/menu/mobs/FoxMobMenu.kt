package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import com.itsazza.mobz.util.item
import de.themoep.inventorygui.GuiStateElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

class FoxMobMenu : MobMenu(EntityType.FOX) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
        it.add(BasicMobAttribute.CROUCHING)
        it.add(BasicMobAttribute.SLEEPING)
        it.add(BasicMobAttribute.SITTING)
    }

    override val buttons = super.buttons.also {
        it.add(foxToggleButton().also { element ->
            if (data.hasKey("Type")) element.setState(data.getString("Type")) else element.setState("random")
        })
    }

    private fun foxToggleButton() : GuiStateElement {
        return GuiStateElement(
            '!',
            GuiStateElement.State(
                {
                    data.setString("Type", "red")
                },
                "red",
                Material.FOX_SPAWN_EGG.item,
                "§6§lFox Type",
                "§7Sets the fox type",
                "§0 ",
                "§7Current: §eRed §8(1/2)",
                "§0 ",
                "§eClick to toggle!"
            ),
            GuiStateElement.State(
                {
                    data.setString("Type", "snow")
                },
                "snow",
                Material.FOX_SPAWN_EGG.item,
                "§6§lFox Type",
                "§7Sets the fox type",
                "§0 ",
                "§7Current: §eSnow §8(2/2)",
                "§0 ",
                "§eClick to toggle!"
            ),
            GuiStateElement.State(
                {
                    data.removeKey("Type")
                },
                "random",
                Material.FOX_SPAWN_EGG.item,
                "§6§lFox Type",
                "§7Sets the fox type",
                "§0 ",
                "§7Current: §eRandom §8(0/2)",
                "§0 ",
                "§eClick to toggle!"
            )
        )
    }
}