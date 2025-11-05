package com.darksoldier1404.dpic.commands;

import com.darksoldier1404.dpic.functions.DPICFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.darksoldier1404.dpic.ItemCategory.plugin;

public class DPICCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(plugin.getPrefix() + "/dpic create <name> - 카테고리 생성");
            sender.sendMessage(plugin.getPrefix() + "/dpic edit <name> - 카테고리 수정");
            sender.sendMessage(plugin.getPrefix() + "/dpic maxPage <name> <maxpage> - 카테고리 최대 페이지 설정");
            sender.sendMessage(plugin.getPrefix() + "/dpic open <name> - 카테고리 오픈");
            sender.sendMessage(plugin.getPrefix() + "/dpic give <name> <page> <slot> <player> (amount) - 아이템 지급");
            return false;
        }
        if (args[0].equalsIgnoreCase("create")) {
            if (args.length < 2) {
                sender.sendMessage(plugin.getPrefix() + "/dpic create <name> - 카테고리 생성");
                return false;
            }
            DPICFunction.createCategory((Player) sender, args[1]);
            return true;
        }
        if (args[0].equalsIgnoreCase("edit")) {
            if (args.length < 2) {
                sender.sendMessage(plugin.getPrefix() + "/dpic edit <name> - 카테고리 수정");
                return false;
            }
            DPICFunction.editCategory((Player) sender, args[1]);
            return true;
        }
        if (args[0].equalsIgnoreCase("delete")) {
            if (args.length < 2) {
                sender.sendMessage(plugin.getPrefix() + "/dpic delete <name> - 카테고리 삭제");
                return false;
            }
            DPICFunction.deleteCategory((Player) sender, args[1]);
            return true;
        }
        if (args[0].equalsIgnoreCase("maxpage")) {
            if (args.length < 3) {
                sender.sendMessage(plugin.getPrefix() + "/dpic maxpage <name> <maxpage> - 카테고리 최대 페이지 설정");
                return false;
            }
            try {
                int maxPage = Integer.parseInt(args[2]);
                DPICFunction.setCategoryMaxPage((Player) sender, args[1], maxPage);
            } catch (NumberFormatException e) {
                sender.sendMessage(plugin.getPrefix() + "§cInvalid number format for max page.");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("open")) {
            if (args.length < 2) {
                sender.sendMessage(plugin.getPrefix() + "/dpic open <name> - 카테고리 오픈");
                return false;
            }
            DPICFunction.openCategory((Player) sender, args[1]);
            return true;
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length < 5) {
                sender.sendMessage(plugin.getPrefix() + "/dpic give <name> <page> <slot> <player> (amount) - 아이템 지급");
                return false;
            }
            try {
                int page = Integer.parseInt(args[2]);
                int slot = Integer.parseInt(args[3]);
                String targetName = args[4];
                int amount = 1;
                if (args.length >= 6) {
                    amount = Integer.parseInt(args[5]);
                }
                DPICFunction.giveItemFromCategory(sender, args[1], page, slot, targetName, amount);
            } catch (NumberFormatException e) {
                sender.sendMessage(plugin.getPrefix() + "§cInvalid number format for page, slot, or amount.");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("giveall")) {
            if (args.length < 5) {
                sender.sendMessage(plugin.getPrefix() + "/dpic giveall <name> <page> <slot> (amount) - 아이템 지급");
                return false;
            }
            try {
                int page = Integer.parseInt(args[2]);
                int slot = Integer.parseInt(args[3]);
                int amount = 1;
                if (args.length >= 5) {
                    amount = Integer.parseInt(args[4]);
                }
                DPICFunction.giveItemFromCategoryToAll(sender, args[1], page, slot, amount);
            } catch (NumberFormatException e) {
                sender.sendMessage(plugin.getPrefix() + "§cInvalid number format for page, slot, or amount.");
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("create", "edit", "delete", "maxpage", "open", "give", "giveall");
        }
        if (args.length == 2) {
            return DPICFunction.getCategoryList();
        }
        return Collections.emptyList();
    }
}
