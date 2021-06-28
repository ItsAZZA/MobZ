package com.itsazza.mobz.util

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta


fun tippedArrow(color: Color) : ItemStack {
    val arrow = Material.TIPPED_ARROW.item
    val arrowMeta = arrow.itemMeta as PotionMeta
    arrowMeta.color = color
    arrowMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
    arrow.itemMeta = arrowMeta
    return arrow
}