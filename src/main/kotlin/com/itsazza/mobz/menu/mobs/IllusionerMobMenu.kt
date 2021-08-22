package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.BasicMobAttribute
import com.itsazza.mobz.menu.MobMenu
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.entity.EntityType

class IllusionerMobMenu : MobMenu(EntityType.ILLUSIONER) {
    override val data: NBTContainer = NBTContainer("{EntityTag:{id:\"${mobType.key.toString().replace("minecraft:", "")}\"}}")
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.LEFT_HANDED)
    }
}