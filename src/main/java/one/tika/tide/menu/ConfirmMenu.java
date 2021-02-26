package one.tika.tide.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

import static one.tika.tide.utils.ItemUtil.createItem;

public class ConfirmMenu extends Menu {
    private final Consumer<Player> onCancel;
    private final Consumer<Player> onConfirm;

    public ConfirmMenu(Player user, Consumer<Player> onCancel, Consumer<Player> onConfirm) {
        super(user);
        this.onCancel = onCancel;
        this.onConfirm = onConfirm;
    }

    @Override
    public String getMenuName() {
        return "&8&mConfirm Change";
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public void setMenuItems() {
        setFillerGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));

        inventory.setItem(3, createItem(Material.GREEN_CONCRETE, "&aConfirm Action"));
        inventory.setItem(5, createItem(Material.RED_CONCRETE, "&cCancel Action"));
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();

        if (item.getType() != Material.GREEN_CONCRETE && item.getType() != Material.RED_CONCRETE) return;
        if (!item.hasItemMeta()) return;

        if (item.getItemMeta().getDisplayName().contains("Confirm Action")) {
            onConfirm.accept(user);
        } else if (item.getItemMeta().getDisplayName().contains("Cancel Action"))
            onCancel.accept(user);
    }

    @Override
    public void handleClose(InventoryCloseEvent event) { }

    @Override
    public boolean isStealable() {
        return false;
    }
}
