package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class DrownedMobMenu : MobMenu(EntityType.DROWNED) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.CAN_BREAK_DOORS)
        it.add(BasicMobAttribute.LEFT_HANDED)
    }
}