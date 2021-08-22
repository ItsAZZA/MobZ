package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import org.bukkit.entity.EntityType

class TraderLlamaMobMenu : MobMenu(EntityType.TRADER_LLAMA) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.TAME)
    }
}