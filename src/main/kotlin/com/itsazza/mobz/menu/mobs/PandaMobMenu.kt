package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class PandaMobMenu : MobMenu(EntityType.PANDA) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
        it.add(BasicMobAttribute.LEFT_HANDED)
    }
}