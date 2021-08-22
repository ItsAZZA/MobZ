package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class BeeMobMenu : MobMenu(EntityType.BEE) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.HAS_NECTAR)
        it.add(BasicMobAttribute.HAS_STUNG)
    }
}