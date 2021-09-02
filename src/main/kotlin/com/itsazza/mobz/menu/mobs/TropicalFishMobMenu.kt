package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class TropicalFishMobMenu : MobMenu(EntityType.TROPICAL_FISH) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.FROM_BUCKET)
    }
}