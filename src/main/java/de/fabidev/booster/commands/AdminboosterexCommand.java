package de.fabidev.booster.commands;

import de.fabidev.booster.utils.Boosters;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminboosterexCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("booster.adminbooster")){
                if (args.length == 3){
                    String arg = args[0];
                    Player target = Bukkit.getPlayer(args[1]);
                    int i = Integer.parseInt(args[2]);
                    if (arg.equalsIgnoreCase("set")){
                        Boosters.setBoosters(p, target, i, "extreme");
                        return false;
                    }else if (arg.equalsIgnoreCase("give")){
                        Boosters.setBoosters(p, target, Boosters.getBoosters(target, "extreme") + i, "extreme");
                        return false;
                    }else if (arg.equalsIgnoreCase("remove")){
                        Boosters.setBoosters(p, target, Boosters.getBoosters(target, "extreme") - i, "extreme");
                        return false;
                    }


                }else if (args.length == 2){
                    String arg = args[0];
                    Player target = Bukkit.getPlayer(args[1]);
                    if (arg.equalsIgnoreCase("info")){
                        p.sendMessage("§aDer Spieler " + args [1] + " hat noch " +Boosters.getBoosters(target, "extreme") + " Extreme-Booster!");
                    }
                    return false;
                }
                p.sendMessage("§cBenutzung: /adminboosterex <set/give/remove/info>");
            }
        }



        return false;
    }
}
