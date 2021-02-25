package one.tika.tide.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemUtil {
    public static ItemStack addGlow(ItemStack item) {
        if (item.hasItemMeta() && item.getItemMeta().getEnchants().size() > 0) return item;

        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
        return item;
    }

    public static void give(Player player, ItemStack item) {
        if (player.getInventory().getItemInMainHand() == null) {
            player.getInventory().setItemInMainHand(item);
        } else if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(item);
        } else {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        }
    }

    public static void remove(Player player, ItemStack item) {
        if (item.getAmount() == 1) {
            player.getInventory().remove(item);
        } else {
            item.setAmount(item.getAmount() - 1);
        }
    }

    public static boolean isNotFull(Player player, Material material) {
        return Arrays.stream(player.getInventory().getContents())
                .filter(item -> item != null && item.getType() == material)
                .anyMatch(item -> item.getAmount() < item.getMaxStackSize());
    }

    // Used to create ItemStacks with names & lores
    public static ItemStack createItem(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Hue.colorize(displayName));

        itemMeta.setLore(Arrays.stream(lore).map(Hue::colorize).collect(Collectors.toList()));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack editItem(ItemStack itemStack, String displayName, String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Hue.colorize(displayName));

        itemMeta.setLore(Arrays.stream(lore).map(Hue::colorize).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack editItem(ItemStack itemStack, String displayName) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Hue.colorize(displayName));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack editItemLore(ItemStack itemStack, String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.stream(lore).map(Hue::colorize).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack editItemAmount(ItemStack itemStack, int amount) {
        ItemStack newItemStack = itemStack;
        newItemStack.setAmount(amount);
        return newItemStack;
    }
}
