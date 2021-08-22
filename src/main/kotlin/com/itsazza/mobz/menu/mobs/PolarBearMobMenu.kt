package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class PolarBearMobMenu : MobMenu(EntityType.POLAR_BEAR) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
    }
}