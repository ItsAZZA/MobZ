package com.itsazza.mobz.util

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta


fun tippedArrow(color: Color) : ItemStack {
    return Material.TIPPED_ARROW.item.mutateMeta<PotionMeta> {
        it.color = color
        it.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
    }
}