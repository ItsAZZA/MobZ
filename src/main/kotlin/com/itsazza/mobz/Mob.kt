package com.itsazza.mobz

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.menu.MobZMainMenu.create
import com.itsazza.mobz.menu.mobs.*
import de.themoep.inventorygui.InventoryGui
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.EntityType
import java.lang.IllegalArgumentException

val EntityType.spawnEgg: Material
    get() = when (this.name) {
        "PIG_ZOMBIE" -> Material.valueOf("ZOMBIE_PIGMAN_SPAWN_EGG")
        "MUSHROOM_COW" -> Material.MOOSHROOM_SPAWN_EGG
        else -> Material.values().firstOrNull { it.name == "${this.name}_SPAWN_EGG" } ?: Material.SHEEP_SPAWN_EGG
    }

fun mobMenu(name: String) : MobMenu? {
    return when(name.toUpperCase()) {
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
        "STRIDER" -> StriderMobMenu()
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
        else -> return null
    }
}

val EntityType.menu: MobMenu?
    get() = mobMenu(this.name)