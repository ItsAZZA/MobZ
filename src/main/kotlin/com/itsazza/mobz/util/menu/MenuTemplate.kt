package com.itsazza.mobz.util.menu

import com.itsazza.mobz.Mobz
import de.themoep.inventorygui.InventoryGui

object MenuTemplate {
    fun create(name: String, template: Array<String>) : InventoryGui {
        return InventoryGui(
            Mobz.instance,
            null,
            name,
            template
        ).also {
            it.setCloseAction { false }
        }
    }

    fun create(name: String, template: Type) : InventoryGui {
        return create(name, template.template)
    }

    enum class Type(val template: Array<String>) {
        ONE_ITEM_MENU(arrayOf(
            "         ",
            "    0    ",
            "         "
        )),
        LIST_MENU(arrayOf(
            "000000000",
            "000000000",
            "000000000",
            "000000000",
            "000000000",
            "<  =@a  >"
        ))
    }
}