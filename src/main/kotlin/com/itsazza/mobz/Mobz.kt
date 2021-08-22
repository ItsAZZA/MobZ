package com.itsazza.mobz

import com.itsazza.mobz.command.MobZCommand
import org.bukkit.plugin.java.JavaPlugin

class Mobz : JavaPlugin() {
    companion object {
        lateinit var instance: Mobz
            private set
    }

    override fun onEnable() {
        instance = this
        getCommand("mobz")?.setExecutor(MobZCommand)
    }
}