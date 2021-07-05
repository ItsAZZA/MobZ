package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.BasicMobAttribute
import com.itsazza.mobz.menu.MobMenu
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.entity.EntityType

class PiglinBruteMobMenu(override val data: NBTContainer) : MobMenu(EntityType.PIGLIN_BRUTE) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.CAN_BREAK_DOORS)
        it.add(BasicMobAttribute.LEFT_HANDED)
    }
}