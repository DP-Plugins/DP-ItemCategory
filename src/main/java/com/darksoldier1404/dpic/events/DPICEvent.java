package com.darksoldier1404.dpic.events;

import com.darksoldier1404.dpic.ItemCategory;
import com.darksoldier1404.dpic.functions.DPICFunction;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.events.dinventory.DInventoryClickEvent;
import com.darksoldier1404.dppc.events.dinventory.DInventoryCloseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class DPICEvent implements Listener {

    @EventHandler
    public void onInventoryClose(DInventoryCloseEvent e) {
        DInventory inv = e.getDInventory();
        if (inv.isValidHandler(ItemCategory.getInstance())) {
            inv.applyChanges();
            if (inv.isValidChannel(1)) {
                DPICFunction.saveCategory((Player) e.getPlayer(), inv);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(DInventoryClickEvent e) {
        DInventory inv = e.getDInventory();
        if (inv.isValidHandler(ItemCategory.getInstance())) {
            ItemStack item = e.getCurrentItem();
            if (item != null && item.getType().isAir()) {
                return;
            }
            if (inv.isValidChannel(0)) { // Category edit channel
                e.setCancelled(true);
                if (e.getCurrentItem() != null) {
                    item = e.getCurrentItem().clone();
                    if (e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT) {
                        e.setCursor(item);
                    }
                    if (e.getClick() == ClickType.CREATIVE || e.getClick() == ClickType.MIDDLE) {
                        item.setAmount(item.getMaxStackSize());
                        e.setCursor(item);
                    }
                }
            }
        }
    }
}
