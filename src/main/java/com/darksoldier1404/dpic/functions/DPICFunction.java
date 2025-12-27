package com.darksoldier1404.dpic.functions;

import com.darksoldier1404.dpic.obj.Category;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.darksoldier1404.dpic.ItemCategory.plugin;

public class DPICFunction {

    public static void createCategory(Player p, String name) {
        if (!isExistCategory(name)) {
            Category c = new Category(name);
            plugin.categories.put(name, c);
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_created"));
        } else {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_exists"));
        }
    }

    public static void deleteCategory(Player p, String name) {
        if (isExistCategory(name)) {
            new File(plugin.getDataFolder(), "categories/" + name + ".yml").delete();
            plugin.categories.remove(name);
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_deleted"));
        } else {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
        }
    }

    public static void editCategory(Player p, String name) {
        if (isExistCategory(name)) {
            Category c = plugin.categories.get(name);
            DInventory inv = c.getInventory();
            inv.setChannel(1);
            inv.setObj(c);
            inv.openInventory(p);
        } else {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
        }
    }

    public static void saveCategory(Player p, DInventory inv) {
        Category c = (Category) inv.getObj();
        if (c == null) {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("save_failed"));
            return;
        }
        String name = c.getName();
        if (!isExistCategory(name)) {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
            return;
        }
        c = plugin.categories.get(name);
        c.setInventory(inv);
        plugin.categories.put(name, c);
        plugin.categories.save(name);
        p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_saved"));
    }

    public static void openCategory(Player p, String name) {
        if (isExistCategory(name)) {
            Category c = plugin.categories.get(name);
            DInventory inv = c.getInventory();
            inv.setChannel(0);
            inv.setObj(c);
            inv.openInventory(p);
        } else {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
        }
    }

    public static DInventory getCategoryInventory(String name) {
        if (isExistCategory(name)) {
            Category c = plugin.categories.get(name);
            return c.getInventory();
        } else {
            return null;
        }
    }

    public static boolean isExistCategory(String name) {
        return plugin.categories.containsKey(name);
    }

    public static List<String> getCategoryList() {
        return new ArrayList<>(plugin.categories.keySet());
    }

    public static void setCategoryMaxPage(Player p, @NotNull String arg, int maxPage) {
        if (!isExistCategory(arg)) {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
            return;
        }
        if (maxPage < 1) {
            p.sendMessage(plugin.getPrefix() + plugin.getLang().get("maxpage_invalid"));
            return;
        }
        Category c = plugin.categories.get(arg);
        DInventory inv = c.getInventory();
        inv.setPages(maxPage);
        c.setInventory(inv);
        plugin.categories.put(arg, c);
        plugin.categories.save(arg);
        p.sendMessage(plugin.getPrefix() + plugin.getLang().getWithArgs("maxpage_set", arg, String.valueOf(maxPage)));
    }

    public static void giveItemFromCategory(CommandSender sender, @NotNull String name, int page, int slot, String targetName, int amount) {
        if (!isExistCategory(name)) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
            return;
        }
        DInventory inv = getCategoryInventory(name);
        if (inv == null) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("load_inventory_failed"));
            return;
        }
        ItemStack item = inv.getPageItems().get(page)[slot];
        if (item == null) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("no_item_in_slot"));
            return;
        }
        Player target = Bukkit.getPlayerExact(targetName);
        if (target == null) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("player_not_found"));
            return;
        }
        item.setAmount(amount);
        target.getInventory().addItem(item);
    }

    public static void giveItemFromCategoryToAll(@NotNull CommandSender sender, @NotNull String name, int page, int slot, int amount) {
        if (!isExistCategory(name)) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("category_not_found"));
            return;
        }
        DInventory inv = getCategoryInventory(name);
        if (inv == null) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("load_inventory_failed"));
            return;
        }
        ItemStack item = inv.getPageItems().get(page)[slot];
        if (item == null) {
            sender.sendMessage(plugin.getPrefix() + plugin.getLang().get("no_item_in_slot"));
            return;
        }
        item.setAmount(amount);
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getInventory().addItem(item);
        }
    }
}
