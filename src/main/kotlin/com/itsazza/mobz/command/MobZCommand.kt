package com.itsazza.mobz.command

import com.itsazza.mobz.menu.MobZMainMenu
import com.itsazza.mobz.mobMenu
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.*

object MobZCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return true
        if (!sender.hasPermission("mobz.menu")) return true

        if (args.isEmpty()) {
            MobZMainMenu.open(sender)
        } else {
            mobMenu(args[0])?.create()?.show(sender) ?: run {
                sender.sendMessage("§cNo mob found with that name!")
                sender.playSound(sender.location, Sound.ENTITY_VILLAGER_NO, 1f, 1f)
            }
        }
        return true
    }
}