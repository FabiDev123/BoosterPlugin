package de.fabidev.booster.listeners;

import de.fabidev.booster.Booster;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        //fliegen:
        if (!e.getPlayer().hasPermission("booster.fly.bypass")){
            if (Booster.fly){
                e.getPlayer().setAllowFlight(true);
                e.getPlayer().setFlying(true);
            }else{
                e.getPlayer().setAllowFlight(false);
                e.getPlayer().setFlying(false);
            }
        }
        //break:
        if (Booster.breakbool){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Booster.breaktimer, 5));
        }else {
            e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
        }
    }

    @EventHandler
    public void onMob(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player) &&
                e.getEntity().getKiller() instanceof Player &&
                Booster.mob != 0)
            if (e.getEntity().getType() != EntityType.HORSE) {
                Integer Multiplikator = Integer.valueOf(Booster.mob + 1);
                List<ItemStack> items = e.getDrops();
                for (Integer i = Integer.valueOf(0); i.intValue() < Multiplikator.intValue(); i = Integer.valueOf(i.intValue() + 1)) {
                    for (ItemStack newitems : items)
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems);
                }
                e.getDrops().clear();
            } else {
                Integer Multiplikator = Integer.valueOf(Booster.mob + 1);
                List<ItemStack> items = e.getDrops();
                for (Integer i = Integer.valueOf(0); i.intValue() < Multiplikator.intValue(); i = Integer.valueOf(i.intValue() + 1)) {
                    for (ItemStack newitems : items) {
                        if (newitems.getType() == Material.LEATHER)
                            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems);
                    }
                }
                for (ItemStack newitems2 : items) {
                    if (newitems2.getType() != Material.LEATHER)
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems2);
                }
                e.getDrops().clear();
            }
    }

    @EventHandler
    public void onXP(PlayerExpChangeEvent e){
        if (Booster.xp != 0) {
            Integer Multiplikator = Integer.valueOf(Booster.xp + 1);
            e.setAmount(e.getAmount() * Multiplikator.intValue());
        }
    }

    @EventHandler
    public void onDrop(BlockBreakEvent e){
        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL &&
                Booster.drop != 0 &&
                !e.getPlayer().getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH))
            if (e.getBlock().getType() == Material.IRON_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.IRON_INGOT, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.GOLD_INGOT, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.COAL_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.COAL, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.DIAMOND, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.EMERALD, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.LAPIS_LAZULI, 4 + Multiplikator.intValue(), (short)4);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.REDSTONE, 4 + Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.NETHER_QUARTZ_ORE) {
                Integer Multiplikator = Integer.valueOf(Booster.drop + 1);
                ItemStack i = new ItemStack(Material.QUARTZ, 4 + Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
    }




}
