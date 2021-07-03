package com.itsazza.mobz.command

import com.itsazza.mobz.menu.MobZMainMenu
import com.itsazza.mobz.menu.mobs.AxolotlMenu
import com.itsazza.mobz.util.NBT
import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTEntity
import de.tr7zw.changeme.nbtapi.NBTType
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.*

object TestCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return true
        MobZMainMenu.open(sender)
        return true
    }
}