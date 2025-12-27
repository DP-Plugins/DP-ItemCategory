package com.darksoldier1404.dpic.obj;

import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.data.DataCargo;
import org.bukkit.configuration.file.YamlConfiguration;

import static com.darksoldier1404.dpic.ItemCategory.plugin;

public class Category implements DataCargo {
    private String name;
    private int maxPage;
    private DInventory inventory;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.maxPage = 1;
        this.inventory = new DInventory(plugin.getLang().getWithArgs("category_title", name), 54, true, true, plugin);
    }

    public Category(String name, int maxPage, DInventory inventory) {
        this.name = name;
        this.maxPage = maxPage;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public DInventory getInventory() {
        return inventory;
    }

    public void setInventory(DInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public YamlConfiguration serialize() {
        YamlConfiguration data = new YamlConfiguration();
        data.set("name", name);
        data.set("maxPage", maxPage);
        data = inventory.serialize(data);
        return data;
    }

    @Override
    public Category deserialize(YamlConfiguration data) {
        String name = data.getString("name");
        int maxPage = data.getInt("maxPage");
        DInventory inventory = new DInventory(plugin.getLang().getWithArgs("category_title", name), 54, true, true, plugin);
        inventory.deserialize(data);
        return new Category(name, maxPage, inventory);
    }
}
