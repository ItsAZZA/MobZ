package com.itsazza.mobz.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun Material.item(amount: Int = 1) = ItemStack(this, amount)

val Material.item: ItemStack
    get() = this.item()