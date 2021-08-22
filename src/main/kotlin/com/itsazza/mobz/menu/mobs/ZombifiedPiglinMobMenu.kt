package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class ZombifiedPiglinMobMenu : MobMenu(EntityType.ZOMBIFIED_PIGLIN) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
        it.add(BasicMobAttribute.CAN_BREAK_DOORS)
        it.add(BasicMobAttribute.LEFT_HANDED)
    }
}