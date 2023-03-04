package de.fabidev.booster.commands;

import de.fabidev.booster.utils.Boosters;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoosterExtremeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("§a§lDu hast noch " + Boosters.getBoosters(p, "extreme") + " Extreme-Booster!");
                p.sendMessage("§a§lWenn du diese einlösen willst, gib /boosterextreme <zuenden> ein!");
                p.sendMessage("§a§lDer Extreme-Booster zündet die Mob/Drop/XP-Booster auf höchste Stufe, den Break-Booster " +
                        "für 30 Minuten und den Fliegen-Booster für 2 Stunden!");

            }else if (args.length == 1){
                Boosters.boosterExtremeZuenden(p);
            }else {
                p.sendMessage("§a§lDu hast noch " + Boosters.getBoosters(p, "extreme") + " Extreme-Booster!");
                p.sendMessage("§a§lWenn du diese einlösen willst, gib /boosterextreme <zuenden> ein!");
                p.sendMessage("§a§lDer Extreme-Booster zündet die Mob/Drop/XP-Booster auf höchste Stufe, den Break-Booster " +
                        "für 30 Minuten und den Fliegen-Booster für 2 Stunden!");
            }



        }






        return false;
    }
}
