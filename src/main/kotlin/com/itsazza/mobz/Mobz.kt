package com.itsazza.mobz

import com.itsazza.mobz.command.MobZCommand
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

class Mobz : JavaPlugin() {
    companion object {
        lateinit var instance: Mobz
            private set
    }

    override fun onEnable() {
        instance = this
        Metrics(this, 12545)
        getCommand("mobz")?.setExecutor(MobZCommand)
    }
}