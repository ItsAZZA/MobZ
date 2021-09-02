package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import com.itsazza.mobz.util.item
import de.themoep.inventorygui.GuiStateElement
import org.bukkit.Material
import org.bukkit.entity.EntityType

class HorseMobMenu : MobMenu(EntityType.HORSE) {
    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.BABY)
        it.add(BasicMobAttribute.TAME)
        it.add(BasicMobAttribute.EATING_HAYSTACK)
    }
}