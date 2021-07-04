package com.itsazza.mobz

import com.itsazza.mobz.menu.mobs.AxolotlMenu
import de.themoep.inventorygui.InventoryGui
import org.bukkit.Material
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
    GLOWING("Glowing", AttributeDataType.BYTE, Material.GLOWSTONE)
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

val EntityType.menu: InventoryGui
    get() = when (this.name) {
        "AXOLOTL" -> AxolotlMenu().create()
        else -> AxolotlMenu().create()
    }