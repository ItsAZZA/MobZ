package com.itsazza.mobz

import com.itsazza.mobz.menu.mobs.*
import de.themoep.inventorygui.InventoryGui
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.EntityType

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
        else -> AxolotlMobMenu(data).create()
    }
}