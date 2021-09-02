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
        "Tame", AttributeDataType.INT, Material.WHEAT,
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
    ),
    SNOWMAN_PUMPKIN(
        "Pumpkin", AttributeDataType.BYTE_ZERO, Material.PUMPKIN,
        listOf(
            "Sets whether the snowman has",
            "a pumpkin on its head or not"
        )
    ),
    SHEARED(
        "Sheared", AttributeDataType.INT, Material.SHEARS,
        listOf(
            "Sets whether the sheep is",
            "sheared or not"
        )
    ),
    NO_GRAVITY(
        "NoGravity", AttributeDataType.BYTE, Material.GRAVEL,
        listOf(
            "Sets whether the mob will",
            "have gravity or not. If set to",
            "true the mob will not fall if",
            "in the air"
        )
    ),
    VISUAL_FIRE(
        "HasVisualFire", AttributeDataType.BYTE, Material.CAMPFIRE,
        listOf(
            "Sets whether the mob has",
            "visual fire effect or not, which",
            "will not physically damage it"
        )
    ),
    FROM_BUCKET(
        "FromBucket", AttributeDataType.INT, Material.BUCKET,
        listOf(
            "Sets whether the mob was",
            "spawned from a bucket, which",
            "means they won't despawn naturally"
        )
    ),
    EATING_HAYSTACK(
        "EatingHaystack", AttributeDataType.INT, Material.HAY_BLOCK,
        listOf(
            "Sets whether the mob is",
            "eating a hay stack or not"
        )
    ),
    PLAYER_SPAWNED(
        "PlayerSpawned", AttributeDataType.INT, Material.ENDERMITE_SPAWN_EGG,
        listOf(
            "Sets whether the mob was",
            "spawned in by a player"
        )
    ),
    SLEEPING(
        "Sleeping", AttributeDataType.INT, Material.RED_BED,
        listOf(
            "Sets whether the mob is",
            "sleeping or not"
        )
    ),
    CROUCHING(
        "Crouching", AttributeDataType.INT, Material.REDSTONE,
        listOf(
            "Sets whether the mob is",
            "crouching or not"
        )
    ),
    SCREAMING_GOAT(
        "IsScreamingGoat", AttributeDataType.INT, Material.NOTE_BLOCK,
        listOf(
            "Sets whether the goat mob is",
            "a screaming goat or not"
        )
    ),
    JOHNNY(
        "Johnny", AttributeDataType.INT, Material.IRON_AXE,
        listOf(
            "Sets whether the vindicator",
            "is Johnny or not"
        )
    ),
    HAS_EGG(
        "HasEgg", AttributeDataType.INT, Material.TURTLE_EGG,
        listOf(
            "Sets whether the turtle",
            "has an egg or not"
        )
    ),
    TRUSTING(
        "Trusting", AttributeDataType.INT, Material.LEAD,
        listOf(
            "Sets whether the ocelot mob",
            "trusts the player or not"
        )
    ),
    PLAYER_CREATED(
        "PlayerCreated", AttributeDataType.INT, Material.IRON_BLOCK,
        listOf(
            "Sets whether the iron golem",
            "mob was spawned by a player,",
            "which makes it never attack the",
            "player"
        )
    ),
    BAT_FLAGS(
        "BatFlags", AttributeDataType.INT, Material.BAT_SPAWN_EGG,
        listOf(
            "Sets whether the bat",
            "appears to be hanging",
            "from a block"
        )
    ),
    IGNITED(
        "Ignited", AttributeDataType.INT, Material.FLINT_AND_STEEL,
        listOf(
            "Sets whether the creeper",
            "is ignited or not"
        )
    )
}