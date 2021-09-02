package com.itsazza.mobz.util

import org.bukkit.potion.PotionEffectType

fun PotionEffectType.minecraftID() : String {
    return when (this.name) {
        "CONFUSION" -> "minecraft:nausea"
        "DAMAGE_RESISTANCE" -> "minecraft:resistance"
        "FAST_DIGGING" -> "minecraft:haste"
        "HARM" -> "minecraft:instant_damage"
        "INCREASE_DAMAGE" -> "minecraft:strength"
        "JUMP" -> "minecraft:jump_boost"
        "SLOW" -> "minecraft:slowness"
        "SLOW_DIGGING" -> "minecraft:mining_fatigue"
        else -> "minecraft:${this.name.toLowerCase()}"
    }
}