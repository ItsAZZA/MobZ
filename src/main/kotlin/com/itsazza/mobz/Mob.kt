package com.itsazza.mobz

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.menu.MobZMainMenu.create
import com.itsazza.mobz.menu.mobs.*
import de.themoep.inventorygui.InventoryGui
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.EntityType
import java.lang.IllegalArgumentException

enum class BasicMobAttribute(
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

enum class AttributeDataType {
    INT,
    BYTE
}

val EntityType.spawnEgg: Material
    get() = when (this.name) {
        "PIG_ZOMBIE" -> Material.valueOf("ZOMBIE_PIGMAN_SPAWN_EGG")
        "MUSHROOM_COW" -> Material.MOOSHROOM_SPAWN_EGG
        else -> Material.values().firstOrNull { it.name == "${this.name}_SPAWN_EGG" } ?: Material.SHEEP_SPAWN_EGG
    }

fun EntityType.menu(): MobMenu {
    return when (this.name) {
        "ARMOR_STAND" -> ArmorStandMobMenu()
        "AXOLOTL" -> AxolotlMobMenu()
        "BAT" -> BatMobMenu()
        "BEE" -> BeeMobMenu()
        "BLAZE" -> BlazeMobMenu()
        "CAT" -> CatMobMenu()
        "CAVE_SPIDER" -> CaveSpiderMobMenu()
        "CHICKEN" -> ChickenMobMenu()
        "COD" -> CodMobMenu()
        "COW" -> CowMobMenu()
        "CREEPER" -> CreeperMobMenu()
        "DOLPHIN" -> DolphinMobMenu()
        "DONKEY" -> DonkeyMobMenu()
        "DROWNED" -> DrownedMobMenu()
        "ELDER_GUARDIAN" -> ElderGuardianMobMenu()
        "ENDER_DRAGON" -> EnderdragonMobMenu()
        "ENDERMAN" -> EndermanMobMenu()
        "ENDERMITE" -> EndermiteMobMenu()
        "EVOKER" -> EvokerMobMenu()
        "FOX" -> FoxMobMenu()
        "GHAST" -> GhastMobMenu()
        "GIANT" -> GiantMobMenu()
        "GLOW_SQUID" -> GlowSquidMobMenu()
        "GOAT" -> GoatMobMenu()
        "GUARDIAN" -> GuardianMobMenu()
        "HOGLIN" -> HoglinMobMenu()
        "HUSK" -> HuskMobMenu()
        "HORSE" -> HorseMobMenu()
        "ILLUSIONER" -> IllusionerMobMenu()
        "IRON_GOLEM" -> IronGolemMobMenu()
        "LLAMA" -> LlamaMobMenu()
        "MAGMA_CUBE" -> MagmaCubeMobMenu()
        "MUSHROOM_COW" -> MooshroomMobMenu()
        "MULE" -> MuleMobMenu()
        "OCELOT" -> OcelotMobMenu()
        "PANDA" -> PandaMobMenu()
        "PARROT" -> ParrotMobMenu()
        "PHANTOM" -> PhantomMobMenu()
        "PIGLIN_BRUTE" -> PiglinBruteMobMenu()
        "PIGLIN" -> PiglinMobMenu()
        "PIG" -> PigMobMenu()
        "PILLAGER" -> PillagerMobMenu()
        "POLAR_BEAR" -> PolarBearMobMenu()
        "PUFFERFISH" -> PufferfishMobMenu()
        "RABBIT" -> RabbitMobMenu()
        "RAVAGER" -> RavagerMobMenu()
        "SALMON" -> SalmonMobMenu()
        "SHEEP" -> SheepMobMenu()
        "SHULKER" -> ShulkerMobMenu()
        "SILVERFISH" -> SilverfishMobMenu()
        "SKELETON_HORSE" -> SkeletonHorseMobMenu()
        "SKELETON" -> SkeletonMobMenu()
        "SLIME" -> SlimeMobMenu()
        "SNOWMAN" -> SnowmanMobMenu()
        "SPIDER" -> SpiderMobMenu()
        "STRAY" -> StrayMobMenu()
        "SQUID" -> SquidMobMenu()
        "TRADER_LLAMA" -> TraderLlamaMobMenu()
        "TROPICAL_FISH" -> TropicalFishMobMenu()
        "TURTLE" -> TurtleMobMenu()
        "VEX" -> VexMobMenu()
        "VILLAGER" -> VillagerMobMenu()
        "VINDICATOR" -> VindicatorMobMenu()
        "WANDERING_TRADER" -> WanderingTraderMobMenu()
        "WITCH" -> WitchMobMenu()
        "WITHER" -> WitherMobMenu()
        "WITHER_SKELETON" -> WitherSkeletonMobMenu()
        "WOLF" -> WolfMobMenu()
        "ZOGLIN" -> ZoglinMobMenu()
        "ZOMBIE_HORSE" -> ZombieHorseMobMenu()
        "ZOMBIE" -> ZombieMobMenu()
        "ZOMBIE_VILLAGER" -> ZombieVillagerMobMenu()
        "ZOMBIFIED_PIGLIN" -> ZombifiedPiglinMobMenu()
        else -> throw IllegalArgumentException("Could not find mob menu for ${this.name}")
    }
}