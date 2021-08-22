package com.itsazza.mobz.command

import com.itsazza.mobz.menu.MobZMainMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.*

object MobZCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return true
        if (!sender.hasPermission("mobz.menu")) return true
        MobZMainMenu.open(sender)
        return true
    }
}