package de.fabidev.booster.listeners;

import de.fabidev.booster.Booster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 9999999, 5));
        }else {
            e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
        }



    }


}
