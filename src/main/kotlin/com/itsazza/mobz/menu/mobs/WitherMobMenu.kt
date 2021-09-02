package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.entity.EntityType

class WitherMobMenu : MobMenu(EntityType.WITHER) {
    override var data: NBTContainer = NBTContainer("{EntityTag:{id:\"${mobType.key.toString().replace("minecraft:", "")}\"}}")
}