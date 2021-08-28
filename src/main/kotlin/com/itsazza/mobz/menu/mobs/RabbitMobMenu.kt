package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import com.itsazza.mobz.util.item
import de.themoep.inventorygui.GuiStateElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

class RabbitMobMenu : MobMenu(EntityType.RABBIT) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
    }

    override val buttons = super.buttons.also {
        it.add(rabbitTypeSelector())
    }

    private fun rabbitTypeSelector() : GuiStateElement {
        val states = arrayListOf<GuiStateElement.State>()
        val typeAmount = rabbitTypes.size

        rabbitTypes.forEach { rabbit ->
            states.add(
                GuiStateElement.State(
                    {
                        data.setInteger("RabbitType", rabbit.value)
                    },
                    "${rabbit.value}",
                    Material.RABBIT_HIDE.item,
                    "§6§lRabbit Type",
                    "§7Defines the type of the",
                    "§7rabbit mob",
                    "§0 ",
                    "§7Current: §e${rabbit.key} §8(${if (rabbit.value == 99) "$typeAmount" else "${rabbit.value + 1}"}/${typeAmount})",
                    "§0 ",
                    "§eClick to toggle!"
                )
            )
        }

        return GuiStateElement(
            '!',
            GuiStateElement.State(
                {
                    data.removeKey("RabbitType")
                },
                "0",
                Material.RABBIT_HIDE.item,
                "§6§lRabbit Type",
                "§7Defines the type of the",
                "§7rabbit mob",
                "§0 ",
                "§7Current: §eRandom §8(0/${typeAmount})",
                "§0 ",
                "§eClick to toggle!"
            ),
            *states.toTypedArray()
        )
    }

    companion object {
        private val rabbitTypes = mapOf(
            Pair("Brown", 0),
            Pair("White", 1),
            Pair("Black", 2),
            Pair("Black & White", 3),
            Pair("Gold", 4),
            Pair("Salt & Pepper", 5),
            Pair("Killer Bunny", 99)
        )
    }
}