package com.itsazza.mobz.menu.mobs

import com.itsazza.mobz.menu.MobMenu
import com.itsazza.mobz.util.BasicMobAttribute
import com.itsazza.mobz.util.item
import com.itsazza.mobz.util.menu.Buttons
import com.itsazza.mobz.util.menu.MenuTemplate
import de.themoep.inventorygui.*
import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.NBTContainer
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Villager

class VillagerMobMenu : MobMenu(EntityType.VILLAGER) {
    private val villagerData = NBTContainer("{profession:farmer,level:0,type:plains}")

    override val basicMobAttributes = super.basicMobAttributes.also {
        it.add(BasicMobAttribute.LEFT_HANDED)
    }

    override val buttons = super.buttons.also {
        it.add(villagerTypeButton)
    }

    private val villagerTypeButton : StaticGuiElement
    get() = StaticGuiElement(
        '!',
        Material.VILLAGER_SPAWN_EGG.item,
        {
            val player = it.event.whoClicked as Player
            createVillagerTypeMenu().show(player)
            return@StaticGuiElement true
        },
        "§6§lVillager Type",
        "§7Change the different settings",
        "§7that define the type and style",
        "§7of the villager mob",
        "§0 ",
        "§eClick to open!"
    )

    private fun createVillagerTypeMenu() : InventoryGui {
        val gui = MenuTemplate.create(
            "Villager Type",
            arrayOf(
                "         ",
                "  123 4  ",
                "         ",
                "    @    "
            )
        )

        gui.setCloseAction { false }
        gui.addElement('1', Material.COMPOSTER.item,
            {
                val player = it.event.whoClicked as Player
                createVillagerProfessionMenu().show(player)
                return@addElement true
            },
            "§6§lVillager Profession",
            "§7Change the villager's",
            "§7profession",
            "§0 ",
            "§7Current: §e${villagerData.getString("profession").capitalize()}",
            "§0",
            "§eClick to open menu!"
        )

        gui.addElement('4',
            Material.GREEN_DYE.item,
            {
                val player = it.event.whoClicked as Player
                val finalVillagerData = NBTContainer("{VillagerData:$villagerData}")
                data.mergeCompound(finalVillagerData)
                menuInstance.show(player)
                return@addElement true
            },
            "§2§lSave Data",
            "§7Save changes and go back",
            "§7to the main villager menu",
            "§0 ",
            "§eClick to save!"
        )

        gui.addElements(
            Buttons.close,
            createVillagerBiomeToggle().also {
                it.setState(villagerData.getString("type"))
            },
            createVillagerLevelToggle().also {
                it.setState(villagerData.getInteger("level").toString())
            }
        )
        gui.setFiller(Material.BLACK_STAINED_GLASS_PANE.item)
        return gui
    }

    private fun createVillagerProfessionMenu() : InventoryGui {
        val gui = MenuTemplate.create(
            "Villager Profession",
            arrayOf(
                "         ",
                " 0000000 ",
                " 0000000 ",
                "         ",
                "   =@    "
            )
        )

        val group = GuiElementGroup('0')
        for (profession in villagerProfessions.sortedBy { it.type }) {
            group.addElement(createVillagerProfessionButton(profession))
        }

        gui.addElements(
            group,
            Buttons.close,
            Buttons.createBackButton(createVillagerTypeMenu())
        )

        gui.setCloseAction { false }
        gui.setFiller(Material.BLACK_STAINED_GLASS_PANE.item)
        return gui
    }

    private fun createVillagerProfessionButton(villagerProfession: VillagerProfession) : StaticGuiElement {
        return StaticGuiElement(
            '!',
            villagerProfession.icon.item,
            {
                val player = it.event.whoClicked as Player
                villagerData.setString("profession", villagerProfession.type)
                createVillagerTypeMenu().show(player)
                return@StaticGuiElement true
            },
            "§6§l${villagerProfession.type.capitalize()}",
            "§7Sets the villager's profession",
            "§7to ${villagerProfession.type}",
            "§0 ",
            "§eClick to set!"
        )
    }

    private fun createVillagerBiomeToggle() : GuiStateElement {
        val states = arrayListOf<GuiStateElement.State>()
        val amount = villagerBiomes.size

        villagerBiomes.forEachIndexed{ index, biome ->
            states.add(
                GuiStateElement.State(
                    {
                        villagerData.setString("type", biome)
                    },
                    biome,
                    Material.PODZOL.item,
                    "§6§lVillager Biome",
                    "§7Sets the villager's biome",
                    "§7and therefore defines its outwear",
                    "§0 ",
                    "§7Current: §e${biome.capitalize()} §8(${index + 1}/${amount})",
                    "§0 ",
                    "§eClick to toggle!"
                )
            )
        }

        return GuiStateElement(
            '3',
            *states.toTypedArray()
        )
    }

    private fun createVillagerLevelToggle() : GuiStateElement {
        val states = arrayListOf<GuiStateElement.State>()
        val amount = villagerLevels.size

        villagerLevels.forEach { villagerLevel ->
            states.add(
                GuiStateElement.State(
                    {
                        villagerData.setInteger("level", villagerLevel.level)
                    },
                    villagerLevel.level.toString(),
                    Material.GOLD_NUGGET.item,
                    "§6§lVillager Level",
                    "§7Sets the villager's level",
                    "§7therefore defining its level",
                    "§7of unlocked trades",
                    "§0 ",
                    "§7Current: §e${villagerLevel.name} §8(${if (villagerLevel.level != 99) villagerLevel.level + 1 else amount}/$amount)",
                    "§0 ",
                    "§eClick to toggle!"
                )
            )
        }

        return GuiStateElement(
            '2',
            *states.toTypedArray()
        )
    }

    companion object {
        private class VillagerProfession(val type: String, val icon: Material)
        private class VillagerLevel(val level: Int, val name: String)

        private val villagerProfessions = listOf(
            VillagerProfession("farmer", Material.COMPOSTER),
            VillagerProfession("fisherman", Material.BARREL),
            VillagerProfession("shepherd", Material.LOOM),
            VillagerProfession("fletcher", Material.FLETCHING_TABLE),
            VillagerProfession("librarian", Material.LECTERN),
            VillagerProfession("cartographer", Material.CARTOGRAPHY_TABLE),
            VillagerProfession("cleric", Material.BREWING_STAND),
            VillagerProfession("armorer", Material.BLAST_FURNACE),
            VillagerProfession("weaponsmith", Material.GRINDSTONE),
            VillagerProfession("toolsmith", Material.SMITHING_TABLE),
            VillagerProfession("butcher", Material.SMOKER),
            VillagerProfession("leatherworker", Material.CAULDRON),
            VillagerProfession("mason", Material.STONECUTTER),
            VillagerProfession("nitwit", Material.GLASS)
        )

        private val villagerLevels = listOf(
            VillagerLevel(0, "Novice"),
            VillagerLevel(1, "Apprentice"),
            VillagerLevel(2, "Journeyman"),
            VillagerLevel(3, "Expert"),
            VillagerLevel(4, "Master"),
            VillagerLevel(99, "No Default Trades")
        )

        private val villagerBiomes = listOf(
            "plains",
            "taiga",
            "savanna",
            "jungle",
            "desert",
            "snow",
            "swamp"
        )
    }
}