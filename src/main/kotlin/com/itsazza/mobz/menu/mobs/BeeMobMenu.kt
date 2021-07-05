package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.BasicMobAttribute
import com.itsazza.mobz.menu.MobMenu
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.entity.EntityType

class BeeMobMenu(override val data: NBTContainer) : MobMenu(EntityType.BEE) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.HAS_NECTAR)
        it.add(BasicMobAttribute.HAS_STUNG)
    }
}