package com.itsazza.mobz.util

import de.tr7zw.changeme.nbtapi.NBTContainer
import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object NBT {
    fun spawnEgg(spawnEgg: Material, json: String) : ItemStack {
        val spawnEggItem = NBTItem(spawnEgg.item)
        val tag = spawnEggItem.getOrCreateCompound("EntityTag")
        tag.mergeCompound(NBTContainer(json))
        spawnEggItem.mergeCompound(tag)
        return spawnEggItem.item
    }
}