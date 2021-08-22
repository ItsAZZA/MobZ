package com.itsazza.mobz.util

import org.bukkit.Material

enum class BasicMobAttribute (
    val nbtAttribute: String,
    val dataType: AttributeDataType,
    val icon: Material = Material.DIAMOND,
    val description: List<String>
) {
    NO_AI(
        "NoAI", AttributeDataType.INT, Material.COMMAND_BLOCK,
        listOf(
            "Sets whether the mob has no",
            "AI which means it cannot",
            "move or interact with its",
            "environment."
        )
    ),
    BABY(
        "IsBaby", AttributeDataType.INT, Material.BAT_SPAWN_EGG,
        listOf(
            "Sets if the mob will be",
            "a baby when spawned"
        )
    ),
    INVULNERABLE(
        "Invulnerable", AttributeDataType.INT, Material.IRON_INGOT,
        listOf(
            "Sets if the mob invulnerable",
            "so they do not take environmental",
            "damage such as from an attack"
        )
    ),
    PERSISTENT(
        "PersistenceRequired", AttributeDataType.INT, Material.DIAMOND,
        listOf(
            "Sets whether the mob can",
            "despawn naturally or not"
        )
    ),
    SILENT(
        "Silent", AttributeDataType.INT, Material.BOOK,
        listOf(
            "Sets whether the mob emits",
            "its natural sounds"
        )
    ),
    CAN_PICK_UP_LOOT(
        "CanPickUpLoot", AttributeDataType.BYTE, Material.DROPPER,
        listOf(
            "Sets whether the mob can",
            "pick dropped loot items",
            "such as armor and weapons"
        )
    ),
    CAN_BREAK_DOORS(
        "CanBreakDoors", AttributeDataType.BYTE, Material.OAK_DOOR,
        listOf(
            "Sets whether the mob can",
            "break wooden doors"
        )
    ),
    LEFT_HANDED(
        "LeftHanded", AttributeDataType.BYTE, Material.IRON_SWORD,
        listOf(
            "Sets whether the mob is",
            "left-handed / where it holds",
            "the main hand item"
        )
    ),
    POWERED(
        "powered", AttributeDataType.INT, Material.NETHER_STAR,
        listOf(
            "Sets if the creeper mob is",
            "powered or \"charged\""
        )
    ),
    TAME(
        "Tame", AttributeDataType.INT, Material.HAY_BLOCK,
        listOf(
            "Should the mob be tamed",
            "or not"
        )
    ),
    WITH_CHEST(
        "ChestedHorse", AttributeDataType.BYTE, Material.CHEST,
        listOf(
            "Sets whether the mob has",
            "a chest or not"
        )
    ),
    SITTING(
        "Sitting", AttributeDataType.INT, Material.COBBLESTONE_STAIRS,
        listOf(
            "Sets whether the mob is",
            "sitting or not"
        )
    ),
    GLOWING(
        "Glowing", AttributeDataType.BYTE, Material.GLOWSTONE,
        listOf(
            "Gives the mob the glowing",
            "effect just like spectral",
            "arrows do"
        )
    ),
    HAS_NECTAR(
        "HasNectar", AttributeDataType.INT, Material.GLASS_BOTTLE,
        listOf(
            "Sets whether the bee is",
            "holding nectar or not"
        )
    ),
    HAS_STUNG(
        "HasStung", AttributeDataType.INT, Material.STICK,
        listOf(
            "Sets whether the bee has",
            "stung and has lost its stinger"
        )
    )
}