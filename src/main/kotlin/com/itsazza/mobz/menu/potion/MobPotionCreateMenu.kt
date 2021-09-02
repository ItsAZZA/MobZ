package com.itsazza.mobz.menu.potion

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.StringUtil
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import com.itsazza.mobz.util.tippedArrow
import de.themoep.inventorygui.DynamicGuiElement
import de.themoep.inventorygui.GuiStateElement
import de.themoep.inventorygui.InventoryGui
import de.themoep.inventorygui.StaticGuiElement
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

@Suppress("DEPRECATION")
class MobPotionCreateMenu(private var potionEffect: PotionEffect, val menu: MobMenu, val data: NBTContainer, private val potions: HashSet<PotionEffect>) {
    fun open(player: Player) {
        create().show(player)
    }

    private fun create(): InventoryGui {
        val gui = MenuTemplate.create(
            StringUtil.beautifyCapitalized(potionEffect.type.name),
            arrayOf(
                "         ",
                " abcAdef ",
                "ghijBklmn",
                "    o    ",
                "         ",
                "   =@1   "
            )
        )

        gui.addElements(
            amplifierButton,
            durationButton,
            createRemoveAmplifierButton('a', 50),
            createRemoveAmplifierButton('b', 10),
            createRemoveAmplifierButton('c', 1),
            createAddAmplifierButton('d', 1),
            createAddAmplifierButton('e', 10),
            createAddAmplifierButton('f', 50),
            createRemoveDurationButton('g', 36000),
            createRemoveDurationButton('h', 6000),
            createRemoveDurationButton('i', 600),
            createRemoveDurationButton('j', 20),
            createAddDurationButton('k', 20),
            createAddDurationButton('l', 600),
            createAddDurationButton('m', 6000),
            createAddDurationButton('n', 36000),
            saveButton,
            Buttons.close,
            Buttons.backInHistory,
            toggleParticlesButton.also {
                val ambient = potionEffect.isAmbient
                val particles = potionEffect.hasParticles()
                if (particles) {
                    if (ambient) {
                        it.setState("ambient")
                    } else {
                        it.setState("true")
                    }
                } else {
                    it.setState("false")
                }
            }
        )

        gui.setFiller(Material.BLACK_STAINED_GLASS_PANE.item)
        return gui
    }

    private val amplifierButton: DynamicGuiElement
        get() = DynamicGuiElement('A') { _: HumanEntity? ->
            StaticGuiElement(
                'A',
                Material.GLOWSTONE_DUST.item,
                "§6§lAmplifier: §7${potionEffect.amplifier + 1}"
            )
        }

    private val durationButton: DynamicGuiElement
        get() = DynamicGuiElement('B') { _: HumanEntity? ->
            StaticGuiElement(
                'B',
                Material.REDSTONE.item,
                "§6§lDuration: §7${getPotionDuration(potionEffect.duration / 20)}",
            )

        }

    private val saveButton: StaticGuiElement
    get() = StaticGuiElement(
            '1',
            Material.GREEN_DYE.item,
            {
                val player = it.event.whoClicked as Player
                potions.removeIf { existing -> existing.type == potionEffect.type }
                potions.add(potionEffect)
                MobPotionMenu(data, menu, potions).open(player)
                return@StaticGuiElement true
            },
            "§2§lSave Effect",
            "§7Save this effect and prepare",
            "§7settings up the next potion",
            "§0 ",
            "§eClick to save!"
        )

    private fun createAddAmplifierButton(char: Char, amount: Int): StaticGuiElement {
        return StaticGuiElement(
            char,
            tippedArrow(Color.LIME),
            {
                val player = it.event.whoClicked as Player
                val current = potionEffect.amplifier
                potionEffect =
                    PotionEffect(potionEffect.type, potionEffect.duration, (current + amount).coerceAtMost(127))
                player.playSound(player.location, Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, 1f, 1f)
                it.gui.draw()
                return@StaticGuiElement true
            },
            "§a§lADD +$amount"
        )
    }

    private fun createRemoveAmplifierButton(char: Char, amount: Int): StaticGuiElement {
        return StaticGuiElement(
            char,
            tippedArrow(Color.RED),
            {
                val current = potionEffect.amplifier
                val player = it.event.whoClicked as Player
                potionEffect =
                    PotionEffect(potionEffect.type, potionEffect.duration, (current - amount).coerceAtLeast(0))
                player.playSound(player.location, Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, 1f, 1f)
                it.gui.draw()
                return@StaticGuiElement true
            },
            "§c§lREMOVE -$amount"
        )
    }

    private fun createAddDurationButton(char: Char, amount: Int): StaticGuiElement {
        return StaticGuiElement(
            char,
            tippedArrow(Color.LIME),
            {
                val current = potionEffect.duration
                val player = it.event.whoClicked as Player
                potionEffect =
                    PotionEffect(potionEffect.type, (current + amount).coerceAtMost(999999), potionEffect.amplifier)
                player.playSound(player.location, Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, 1f, 1f)
                it.gui.draw()
                return@StaticGuiElement true
            },
            "§a§lADD ${getPotionDuration(amount / 20)}"
        )
    }

    private fun createRemoveDurationButton(char: Char, amount: Int): StaticGuiElement {
        return StaticGuiElement(
            char,
            tippedArrow(Color.RED),
            {
                val current = potionEffect.duration
                val player = it.event.whoClicked as Player
                potionEffect =
                    PotionEffect(potionEffect.type, (current - amount).coerceAtLeast(20), potionEffect.amplifier)
                player.playSound(player.location, Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, 1f, 1f)
                it.gui.draw()
                return@StaticGuiElement true
            },
            "§c§lREMOVE ${getPotionDuration(amount / 20)}"
        )
    }

    private val toggleParticlesButton: GuiStateElement
        get() = GuiStateElement(
            'o',
            GuiStateElement.State(
                {
                    potionEffect =
                        PotionEffect(potionEffect.type, potionEffect.duration, potionEffect.amplifier, false, true)
                    it.gui.draw()
                },
                "true",
                Material.LIME_DYE.item,
                "§6§lParticles",
                "§7Should the applied effect give",
                "§7the player a potion particle effect",
                "§0 ",
                "§a▶ Full",
                "§8Ambient",
                "§8None",
                "§0 ",
                "§eClick to toggle!"
            ),
            GuiStateElement.State(
                {
                    potionEffect =
                        PotionEffect(potionEffect.type, potionEffect.duration, potionEffect.amplifier, true, true)
                    it.gui.draw()
                },
                "ambient",
                Material.MAGENTA_DYE.item,
                "§6§lParticles",
                "§7Should the applied effect give",
                "§7the player a potion particle effect",
                "§0 ",
                "§8Full",
                "§d▶ Ambient",
                "§8None",
                "§0 ",
                "§eClick to toggle!"
            ),
            GuiStateElement.State(
                {
                    potionEffect =
                        PotionEffect(potionEffect.type, potionEffect.duration, potionEffect.amplifier, false, false)
                    it.gui.draw()
                },
                "false",
                Material.GRAY_DYE.item,
                "§6§lParticles",
                "§7Should the applied effect give",
                "§7the player a potion particle effect",
                "§0 ",
                "§8Full",
                "§8Ambient",
                "§c▶ None",
                "§0 ",
                "§eClick to toggle!"
            )
        )
}