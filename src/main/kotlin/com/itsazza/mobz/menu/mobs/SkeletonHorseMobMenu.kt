package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class SkeletonHorseMobMenu : MobMenu(EntityType.SKELETON_HORSE) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
        it.add(BasicMobAttribute.TAME)
        it.add(BasicMobAttribute.EATING_HAYSTACK)
    }
}