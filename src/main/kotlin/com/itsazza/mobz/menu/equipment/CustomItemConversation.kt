package com.itsazza.mobz.menu.equipment

import com.google.common.util.concurrent.Futures.withTimeout
import com.itsazza.mobz.Mobz
import com.itsazza.mobz.menu.MobMenu
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.ConversationFactory
import org.bukkit.conversations.Prompt
import org.bukkit.conversations.StringPrompt
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

class CustomItemConversation {
    fun start(player: Player, data: NBTContainer, equipmentData: MutableMap<EquipmentSlot, ItemStack>, equipmentSlot: EquipmentSlot, mobMenu: MobMenu) {
        val factory = ConversationFactory(Mobz.instance)
            .withModality(true)
            .withLocalEcho(false)
            .withEscapeSequence("cancel")
            .withFirstPrompt(BlockPrompt(player, data, equipmentData, equipmentSlot, mobMenu))
            .withTimeout(60)

        factory.buildConversation(player).begin()
    }

    private class BlockPrompt(val player: Player, val data: NBTContainer, val equipmentData: MutableMap<EquipmentSlot, ItemStack>, val equipmentSlot: EquipmentSlot, val mobMenu: MobMenu) : StringPrompt() {
        override fun getPromptText(context: ConversationContext): String {
            return "\n§6§lWhat item would you want to use?\n§7Write the item search in chat. Don't worry, it won't show to others!"
        }

        override fun acceptInput(context: ConversationContext, input: String?): Prompt? {
            input ?: return null

            object : BukkitRunnable() {
                override fun run() {
                    lateinit var materials: List<Material>
                    object : BukkitRunnable() {
                        override fun run() {
                            materials = Material.values()
                                .filter { it.name.contains(input, ignoreCase = true) && it.isItem && !it.isAir}
                            if (materials.isNotEmpty()) {
                                EquipmentSelectorMenu().open(player, equipmentData, equipmentSlot, data, mobMenu, materials)
                            } else {
                                MobEquipmentMenu(data, equipmentData, mobMenu).create().show(player)
                                player.sendMessage("§cNo items found with your query!")
                                player.playSound(player.location, Sound.ENTITY_VILLAGER_NO, 1f, 1f)
                            }
                        }
                    }.runTask(Mobz.instance)
                }
            }.runTaskAsynchronously(Mobz.instance)

            return END_OF_CONVERSATION
        }
    }
}