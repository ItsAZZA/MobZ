package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class DonkeyMobMenu : MobMenu(EntityType.DONKEY) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
        it.add(BasicMobAttribute.TAME)
        it.add(BasicMobAttribute.WITH_CHEST)
        it.add(BasicMobAttribute.EATING_HAYSTACK)
    }
}