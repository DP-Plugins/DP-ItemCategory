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
            p.sendMessage(plugin.getPrefix() + "§aCategory created successfully!");
        } else {
            p.sendMessage(plugin.getPrefix() + "§cCategory already exists!");
        }
    }

    public static void deleteCategory(Player p, String name) {
        if (isExistCategory(name)) {
            new File(plugin.getDataFolder(), "categories/" + name + ".yml").delete();
            plugin.categories.remove(name);
            p.sendMessage(plugin.getPrefix() + "§aCategory deleted successfully!");
        } else {
            p.sendMessage(plugin.getPrefix() + "§cCategory not found!");
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
            p.sendMessage(plugin.getPrefix() + "§cCategory not found!");
        }
    }

    public static void saveCategory(Player p, DInventory inv) {
        Category c = (Category) inv.getObj();
        if (c == null) {
            p.sendMessage(plugin.getPrefix() + "§cFailed to save category!");
            return;
        }
        String name = c.getName();
        if (!isExistCategory(name)) {
            p.sendMessage(plugin.getPrefix() + "§cCategory not found!");
            return;
        }
        c = plugin.categories.get(name);
        c.setInventory(inv);
        plugin.categories.put(name, c);
        plugin.categories.save(name);
        p.sendMessage(plugin.getPrefix() + "§aCategory saved successfully!");
    }

    public static void openCategory(Player p, String name) {
        if (isExistCategory(name)) {
            Category c = plugin.categories.get(name);
            DInventory inv = c.getInventory();
            inv.setChannel(0);
            inv.setObj(c);
            inv.openInventory(p);
        } else {
            p.sendMessage(plugin.getPrefix() + "§cCategory not found!");
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
            p.sendMessage(plugin.getPrefix() + "§cCategory not found!");
            return;
        }
        if (maxPage < 1) {
            p.sendMessage(plugin.getPrefix() + "§cMax page must be greater than 0!");
            return;
        }
        Category c = plugin.categories.get(arg);
        DInventory inv = c.getInventory();
        inv.setPages(maxPage);
        c.setInventory(inv);
        plugin.categories.put(arg, c);
        plugin.categories.save(arg);
        p.sendMessage(plugin.getPrefix() + "§aMax page set to " + maxPage + " for category " + arg + ".");
    }

    public static void giveItemFromCategory(CommandSender sender, @NotNull String name, int page, int slot, String targetName, int amount) {
        if (!isExistCategory(name)) {
            sender.sendMessage(plugin.getPrefix() + "§cCategory not found!");
            return;
        }
        DInventory inv = getCategoryInventory(name);
        if (inv == null) {
            sender.sendMessage(plugin.getPrefix() + "§cFailed to load category inventory!");
            return;
        }
        ItemStack item = inv.getPageItems().get(page)[slot];
        if (item == null) {
            sender.sendMessage(plugin.getPrefix() + "§cNo item found in the specified slot on this page!");
            return;
        }
        Player target = Bukkit.getPlayerExact(targetName);
        if (target == null) {
            sender.sendMessage(plugin.getPrefix() + "§cTarget player not found or not online!");
            return;
        }
        item.setAmount(amount);
        target.getInventory().addItem(item);
    }

    public static void giveItemFromCategoryToAll(@NotNull CommandSender sender, @NotNull String name, int page, int slot, int amount) {
        if (!isExistCategory(name)) {
            sender.sendMessage(plugin.getPrefix() + "§cCategory not found!");
            return;
        }
        DInventory inv = getCategoryInventory(name);
        if (inv == null) {
            sender.sendMessage(plugin.getPrefix() + "§cFailed to load category inventory!");
            return;
        }
        ItemStack item = inv.getPageItems().get(page)[slot];
        if (item == null) {
            sender.sendMessage(plugin.getPrefix() + "§cNo item found in the specified slot on this page!");
            return;
        }
        item.setAmount(amount);
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getInventory().addItem(item);
        }
    }
}
