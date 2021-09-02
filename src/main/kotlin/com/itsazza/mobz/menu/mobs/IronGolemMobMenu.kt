package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.entity.EntityType

class IronGolemMobMenu : MobMenu(EntityType.IRON_GOLEM) {
    override var data: NBTContainer = NBTContainer("{EntityTag:{id:\"${mobType.key.toString().replace("minecraft:", "")}\"}}")
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.PLAYER_CREATED)
    }
}