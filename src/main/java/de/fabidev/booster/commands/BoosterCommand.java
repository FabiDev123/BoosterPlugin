package de.fabidev.booster.commands;

import de.fabidev.booster.utils.Boosters;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BoosterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("booster.command")){
                if (args.length == 0){
                    int i = Boosters.getBoosters(p);

                    if (i == 0){
                        p.sendMessage("§cDu hast keine Booster mehr übrig! Erwerbe welche unter kingdomblocks.net oder ziehe diese in unseren Kisten!");
                        p.sendMessage("§aBenutzung:");
                        p.sendMessage("§a/booster §a§lfür Informationen rund um deine Booster");
                        p.sendMessage("§a/booster <fliegen/break/mob/drop/xp> §a§lum deine Booster einzulösen");
                        return false;
                    }

                    p.sendMessage("§aDu hast noch §d§l" + Boosters.getBoosters(p) + " Booster §aübrig!");
                    p.sendMessage("§aBenutzung:");
                    p.sendMessage("§a/booster §a§lfür Informationen rund um deine Booster");
                    p.sendMessage("§a/booster <fliegen/break/mob/drop/xp> §a§lum deine Booster einzulösen");


                }else if(args.length == 1){
                    String arg = args[0];

                    if (arg.equalsIgnoreCase("fliegen") ||
                            arg.equalsIgnoreCase("mob") ||
                            arg.equalsIgnoreCase("drop") ||
                            arg.equalsIgnoreCase("break") ||
                            arg.equalsIgnoreCase("xp") ){
                        Boosters.boosterZuenden(p, arg);
                    }else{
                        p.sendMessage("§4§lFalsche Benutzung! Nutze:");
                        p.sendMessage("§c/booster §4§lfür Informationen rund um deine Booster");
                        p.sendMessage("§c/booster <fliegen/break/mob/drop/xp> §4§lum deine Booster einzulösen");
                    }

                }else{
                    p.sendMessage("§4§lFalsche Benutzung! Nutze:");
                    p.sendMessage("§c/booster §4§lfür Informationen rund um deine Booster");
                    p.sendMessage("§c/booster <fliegen/break/mob/drop/xp> §4§lum deine Booster einzulösen");
                }

            }else {
                p.sendMessage("§4§lDazu hast du keine Rechte!");
            }
        }else {
            System.out.println("Dieser Befehl ist nicht fuer die Konsole geeignet!");
        }





        return false;
    }
}
