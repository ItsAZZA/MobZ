package com.itsazza.mobz

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.menu.mobs.*
import de.themoep.inventorygui.InventoryGui
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.Dolphin
import org.bukkit.entity.EntityType
import java.lang.IllegalArgumentException

enum class BasicMobAttribute(val nbtAttribute: String, val dataType: AttributeDataType, val icon: Material = Material.DIAMOND) {
    NO_AI("NoAI", AttributeDataType.INT, Material.PHANTOM_MEMBRANE),
    BABY("IsBaby", AttributeDataType.INT, Material.BAT_SPAWN_EGG),
    INVULNERABLE("Invulnerable", AttributeDataType.INT, Material.NETHERITE_INGOT),
    PERSISTENT("PersistenceRequired", AttributeDataType.INT),
    SILENT("Silent", AttributeDataType.INT),
    CAN_PICK_UP_LOOT("CanPickUpLoot", AttributeDataType.BYTE, Material.DROPPER),
    CAN_BREAK_DOORS("CanBreakDoors", AttributeDataType.BYTE, Material.OAK_DOOR),
    LEFT_HANDED("LeftHanded", AttributeDataType.BYTE),
    POWERED("powered", AttributeDataType.INT, Material.NETHER_STAR),
    TAME("Tame", AttributeDataType.INT, Material.HAY_BLOCK),
    WITH_CHEST("ChestedHorse", AttributeDataType.BYTE, Material.CHEST),
    SITTING("Sitting", AttributeDataType.INT),
    GLOWING("Glowing", AttributeDataType.BYTE, Material.GLOWSTONE),
    HAS_NECTAR("HasNectar", AttributeDataType.INT, Material.HONEY_BOTTLE),
    HAS_STUNG("HasStung", AttributeDataType.INT, Material.POINTED_DRIPSTONE)
}

enum class AttributeDataType {
    INT,
    BYTE
}

val EntityType.spawnEgg: Material
    get() = when (this.name) {
        "PIG_ZOMBIE" -> Material.ZOMBIFIED_PIGLIN_SPAWN_EGG
        "MUSHROOM_COW" -> Material.MOOSHROOM_SPAWN_EGG
        else -> Material.values().firstOrNull { it.name == "${this.name}_SPAWN_EGG" } ?: Material.SHEEP_SPAWN_EGG
    }

fun EntityType.menu(data: NBTContainer = NBTContainer()): InventoryGui {
    return when (this.name) {
        "AXOLOTL" -> AxolotlMobMenu(data).create()
        "BAT" -> BatMobMenu(data).create()
        "BEE" -> BeeMobMenu(data).create()
        "BLAZE" -> BlazeMobMenu(data).create()
        "CAT" -> CatMobMenu(data).create()
        "CAVE_SPIDER" -> CaveSpiderMobMenu(data).create()
        "CHICKEN" -> ChickenMobMenu(data).create()
        "COD" -> CodMobMenu(data).create()
        "COW" -> CowMobMenu(data).create()
        "CREEPER" -> CreeperMobMenu(data).create()
        "DOLPHIN" -> DolphinMobMenu(data).create()
        "DONKEY" -> DonkeyMobMenu(data).create()
        "DROWNED" -> DrownedMobMenu(data).create()
        "ELDER_GUARDIAN" -> ElderGuardianMobMenu(data).create()
        "ENDER_DRAGON" -> EnderdragonMobMenu(data).create()
        "ENDERMAN" -> EndermanMobMenu(data).create()
        "EVOKER" -> EvokerMobMenu(data).create()
        "FOX" -> FoxMobMenu(data).create()
        "GHAST" -> GhastMobMenu(data).create()
        "GIANT" -> GiantMobMenu(data).create()
        "GLOW_SQUID" -> GlowSquidMobMenu(data).create()
        "GOAT" -> GoatMobMenu(data).create()
        "GUARDIAN" -> GuardianMobMenu(data).create()
        "HOGLIN" -> HoglinMobMenu(data).create()
        "HUSK" -> HuskMobMenu(data).create()
        "HORSE" -> HorseMobMenu(data).create()
        "ILLUSIONER" -> IllusionerMobMenu(data).create()
        "IRON_GOLEM" -> IronGolemMobMenu(data).create()
        "LLAMA" -> LlamaMobMenu(data).create()
        "MAGMA_CUBE" -> MagmaCubeMobMenu(data).create()
        "MUSHROOM_COW" -> MooshroomMobMenu(data).create()
        "MULE" -> MuleMobMenu(data).create()
        "OCELOT" -> OcelotMobMenu(data).create()
        "PANDA" -> PandaMobMenu(data).create()
        "PARROT" -> ParrotMobMenu(data).create()
        "PHANTOM" -> PhantomMobMenu(data).create()
        "PIGLIN_BRUTE" -> PiglinBruteMobMenu(data).create()
        "PIGLIN" -> PiglinMobMenu(data).create()
        "PIG" -> PigMobMenu(data).create()
        "PILLAGER" -> PillagerMobMenu(data).create()
        "POLAR_BEAR" -> PolarBearMobMenu(data).create()
        "PUFFERFISH" -> PufferfishMobMenu(data).create()
        "RABBIT" -> RabbitMobMenu(data).create()
        "RAVAGER" -> RavagerMobMenu(data).create()
        "SALMON" -> SalmonMobMenu(data).create()
        "SHEEP" -> SheepMobMenu(data).create()
        "SHULKER" -> ShulkerMobMenu(data).create()
        "SILVERFISH" -> SilverfishMobMenu(data).create()
        "SKELETON_HORSE" -> SkeletonMobMenu(data).create()
        "SLIME" -> SlimeMobMenu(data).create()
        "SNOWMAN" -> SnowmanMobMenu(data).create()
        "SPIDER" -> SpiderMobMenu(data).create()
        "STRAY" -> StrayMobMenu(data).create()
        "TRADER_LLAMA" -> TraderLlamaMobMenu(data).create()
        "TROPICAL_FISH" -> TropicalFishMobMenu(data).create()
        "TURTLE" -> TurtleMobMenu(data).create()
        "VEX" -> VexMobMenu(data).create()
        "VILLAGER" -> VillagerMobMenu(data).create()
        "VINDICATOR" -> VindicatorMobMenu(data).create()
        "WANDERING_TRADER" -> WanderingTraderMobMenu(data).create()
        "WITCH" -> WitchMobMenu(data).create()
        "WITHER" -> WitherMobMenu(data).create()
        "WITHER_SKELETON" -> WitherSkeletonMobMenu(data).create()
        "WOLF" -> WolfMobMenu(data).create()
        "ZOGLIN" -> ZoglinMobMenu(data).create()
        "ZOMBIE_HORSE" -> HorseMobMenu(data).create()
        "ZOMBIE" -> ZombieMobMenu(data).create()
        "ZOMBIE_VILLAGER" -> ZombieVillagerMobMenu(data).create()
        "ZOMBIFIED_PIGLIN" -> ZombifiedPiglinMobMenu(data).create()
        else -> throw IllegalArgumentException("Could not find mob menu for ${this.name}")
    }
}