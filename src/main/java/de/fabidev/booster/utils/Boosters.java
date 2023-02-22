package de.fabidev.booster.utils;

import org.bukkit.entity.Player;

public class Boosters {



    public static void boosterZuenden(Player p, String which){
        if (getBoosters(p) != 0){
            //TODO connect with DB and power booster
        }else {
            p.sendMessage("§cDu hast keine Booster mehr übrig! Erwerbe welche unter kingdomblocks.net oder ziehe diese in unseren Kisten!");
            p.sendMessage("§aBenutzung:");
            p.sendMessage("§a/booster §a§lfür Informationen rund um deine Booster");
            p.sendMessage("§a/booster <fliegen/break/mob/drop/xp> §a§lum deine Booster einzulösen");
            return;
        }
    }

    public static int getBoosters(Player p){
        //TODO connect with DB to check Booster
        return 0;
    }





}
