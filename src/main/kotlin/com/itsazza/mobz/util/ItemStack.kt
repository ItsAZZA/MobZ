package com.itsazza.mobz.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun Material.item(amount: Int = 1) = ItemStack(this, amount)

val Material.item: ItemStack
    get() = this.item()

inline fun <reified T> ItemStack.mutateMeta(mutator: (T) -> Unit): ItemStack {
    this.itemMeta = this.itemMeta.also { mutator(it as T) }
    return this
}