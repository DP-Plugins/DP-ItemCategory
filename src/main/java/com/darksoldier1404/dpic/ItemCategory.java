package com.darksoldier1404.dpic;

import com.darksoldier1404.dpic.commands.DPICCommand;
import com.darksoldier1404.dpic.events.DPICEvent;
import com.darksoldier1404.dpic.obj.Category;
import com.darksoldier1404.dppc.annotation.DPPCoreVersion;
import com.darksoldier1404.dppc.data.DPlugin;
import com.darksoldier1404.dppc.data.DataContainer;
import com.darksoldier1404.dppc.data.DataType;
import com.darksoldier1404.dppc.utils.PluginUtil;

@DPPCoreVersion(since = "5.3.3")
public class ItemCategory extends DPlugin {
    public static ItemCategory plugin;
    public static DataContainer<String, Category> categories;

    public static ItemCategory getInstance() {
        return plugin;
    }

    public ItemCategory() {
        super(true);
        plugin = this;
        init();
        categories = loadDataContainer(new DataContainer<>(plugin, DataType.CUSTOM, "categories"), Category.class);
    }

    @Override
    public void onLoad() {
        PluginUtil.addPlugin(plugin, 26503);
    }

    @Override
    public void onEnable() {
        plugin.getServer().getPluginManager().registerEvents(new DPICEvent(), plugin);
        getCommand("dic").setExecutor(new DPICCommand());
    }

    @Override
    public void onDisable() {
        saveAllData();
    }
}
