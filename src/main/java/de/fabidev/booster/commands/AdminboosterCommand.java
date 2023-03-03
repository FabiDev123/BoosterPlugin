package de.fabidev.booster.commands;

import de.fabidev.booster.utils.Boosters;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminboosterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("booster.adminbooster")){
                Player target = Bukkit.getPlayer(args[0]);
                int number = Integer.parseInt(args[1]);

                Boosters.setBoosters(p, target, number);

            }
        }




        return false;
    }
}
