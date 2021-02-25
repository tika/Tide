package one.tika.tide.listeners;

import one.tika.tide.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        //If the holder extends Menu (is a custom menu)
        if (holder instanceof Menu) {
            Menu menu = (Menu) holder; // certain that holder is now a Menu

            event.setCancelled(!menu.isStealable());

            if (event.getCurrentItem() == null) return; // prevent NPE
            menu.handleMenu(event);
        }
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;
            menu.handleClose(event);
        }
    }

}
