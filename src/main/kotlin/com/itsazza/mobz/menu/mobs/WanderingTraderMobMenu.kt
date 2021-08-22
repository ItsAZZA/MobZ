package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class WanderingTraderMobMenu : MobMenu(EntityType.WANDERING_TRADER) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.LEFT_HANDED)
    }
}