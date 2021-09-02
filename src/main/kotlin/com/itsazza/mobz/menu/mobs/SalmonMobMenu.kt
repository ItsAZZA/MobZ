package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class SalmonMobMenu : MobMenu(EntityType.SALMON) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.FROM_BUCKET)
    }
}