package com.itsazza.mobz.util

import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

object NBT {
    fun spawnEgg(spawnEgg: Material, json: String) : ItemStack {
        val spawnEggItem = NBTItem(spawnEgg.item)
        val tag = spawnEggItem.getOrCreateCompound("EntityTag")
        tag.mergeCompound(NBTContainer(json))
        spawnEggItem.mergeCompound(tag)
        return spawnEggItem.item
    }

    fun commandBlockWithCommand(command: String): ItemStack {
        val commandBlockItem = NBTItem(Material.COMMAND_BLOCK.item)
        val tag = commandBlockItem.getOrCreateCompound("BlockEntityTag")
        tag.setString("Command", command)
        commandBlockItem.mergeCompound(tag)
        return commandBlockItem.item
    }

    fun commandBlockWithSpawnerSetBlock(mobType: EntityType, mobData: String) : ItemStack {
        val mobDataTag = NBTContainer(mobData)
        mobDataTag.setString("id", mobType.key.toString().replace("minecraft:", ""))
        val command = "minecraft:setblock ~ ~1 ~ minecraft:spawner{SpawnData:$mobDataTag} replace"
        return this.commandBlockWithCommand(command)
    }

    fun spawner(mobType: EntityType, mobData: String): ItemStack {
        val spawnerItem = NBTItem(Material.SPAWNER.item)
        val tag = spawnerItem.getOrCreateCompound("BlockEntityTag")

        val spawnDataTag = tag.getOrCreateCompound("SpawnData")
        val mobDataTag = NBTContainer(mobData)
        spawnDataTag.setString("id", mobType.key.toString().replace("minecraft:", ""))
        spawnDataTag.mergeCompound(mobDataTag)

        spawnerItem.mergeCompound(tag)
        return spawnerItem.item
    }
}