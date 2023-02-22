package de.fabidev.booster.commands;

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


            }else {
                p.sendMessage("§4§lDazu hast du keine Rechte!");
            }
        }else {
            System.out.println("Dieser Befehl ist nicht fuer die Konsole geeignet!");
        }





        return false;
    }
}
