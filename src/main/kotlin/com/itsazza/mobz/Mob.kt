package com.itsazza.mobz

import org.bukkit.Material
import org.bukkit.entity.EntityType

enum class BasicMobAttribute(val attribute: String) {
    NO_AI("NoAI"),
    BABY("IsBaby"),
    INVULNERABLE("Invulnerable"),
    PERSISTENT("PersistenceRequired"),
    SILENT("Silent"),
    CAN_PICK_UP_LOOT("CanPickUpLoot"),
    CAN_BREAK_DOORS("CanBreakDoors"),
    LEFT_HANDED("LeftHanded"),
    POWERED("powered"),
    TAME("Tame"),
    WITH_CHEST("ChestedHorse"),
}

fun EntityType.mobAttributes(): List<BasicMobAttribute> {
    return when (this.name) {
        "ZOMBIE",
        "ZOMBIFIED_PIGLIN",
        "DROWNED",
        "HUSK" -> listOf(
            BasicMobAttribute.NO_AI,
            BasicMobAttribute.INVULNERABLE,
            BasicMobAttribute.PERSISTENT,
            BasicMobAttribute.SILENT,
            BasicMobAttribute.CAN_PICK_UP_LOOT,
            BasicMobAttribute.CAN_BREAK_DOORS,
            BasicMobAttribute.LEFT_HANDED
        )
        "SKELETON",
        "ENDERMAN",
        "EVOKER",
        "GIANT",
        "ILLUSIONER" -> listOf(
            BasicMobAttribute.NO_AI,
            BasicMobAttribute.INVULNERABLE,
            BasicMobAttribute.PERSISTENT,
            BasicMobAttribute.SILENT,
            BasicMobAttribute.CAN_PICK_UP_LOOT,
            BasicMobAttribute.LEFT_HANDED
        )
        "AXOLOTL",
        "BEE",
        "CAT",
        "CHICKEN",
        "COW",
        "FOX",
        "GOAT",
        "HOGLIN" -> listOf(
            BasicMobAttribute.BABY,
            BasicMobAttribute.NO_AI,
            BasicMobAttribute.INVULNERABLE,
            BasicMobAttribute.PERSISTENT,
            BasicMobAttribute.SILENT,
            BasicMobAttribute.CAN_PICK_UP_LOOT
        )
        "BAT",
        "BLAZE",
        "CAVE_SPIDER",
        "COD",
        "DOLPHIN",
        "ELDER_GUARDIAN",
        "ENDER_DRAGON",
        "ENDERMITE",
        "GHAST",
        "GLOW_SQUID",
        "GUARDIAN" -> listOf(
            BasicMobAttribute.NO_AI,
            BasicMobAttribute.INVULNERABLE,
            BasicMobAttribute.PERSISTENT,
            BasicMobAttribute.SILENT,
            BasicMobAttribute.CAN_PICK_UP_LOOT
        )
        "CREEPER" -> listOf(
            BasicMobAttribute.NO_AI,
            BasicMobAttribute.INVULNERABLE,
            BasicMobAttribute.PERSISTENT,
            BasicMobAttribute.SILENT,
            BasicMobAttribute.CAN_PICK_UP_LOOT,
            BasicMobAttribute.POWERED
        )
        "DONKEY",
        "HORSE" -> listOf(
            BasicMobAttribute.NO_AI,
            BasicMobAttribute.INVULNERABLE,
            BasicMobAttribute.PERSISTENT,
            BasicMobAttribute.SILENT,
            BasicMobAttribute.CAN_PICK_UP_LOOT,
            BasicMobAttribute.TAME,
            BasicMobAttribute.WITH_CHEST
        )
        else -> emptyList()
    }
}

fun EntityType.spawnEgg() : Material {
    return when(this.name) {
        "PIG_ZOMBIE" -> Material.ZOMBIFIED_PIGLIN_SPAWN_EGG
        "MUSHROOM_COW" -> Material.MOOSHROOM_SPAWN_EGG
        else -> Material.values().firstOrNull { it.name == "${this.name}_SPAWN_EGG" } ?: Material.SHEEP_SPAWN_EGG
    }
}