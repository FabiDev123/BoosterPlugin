package de.fabidev.booster.utils;

import de.fabidev.booster.Booster;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.*;

public class Boosters {

    private static Connection con;

    public static void connectToDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+ Booster.address + "/" + Booster.dbname, Booster.username, Booster.password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void boosterZuenden(Player p, String which) {
        int i = getBoosters(p);
        if ( i != 0){

            if (which.equalsIgnoreCase("fliegen")){
                handleFly(p, i);
            }if (which.equalsIgnoreCase("break")){
                handleBreak(p, i);
            }if (which.equalsIgnoreCase("mob")){

            }if (which.equalsIgnoreCase("drop")){

            }if (which.equalsIgnoreCase("xp")){

            }
        }else {
            p.sendMessage("§cDu hast keine Booster mehr übrig! Erwerbe welche unter kingdomblocks.net oder ziehe diese in unseren Kisten!");
            p.sendMessage("§aBenutzung:");
            p.sendMessage("§a/booster §a§lfür Informationen rund um deine Booster");
            p.sendMessage("§a/booster <fliegen/break/mob/drop/xp> §a§lum deine Booster einzulösen");
            return;
        }
    }

    public static int getBoosters(Player p)  {
        try {
            String exec = "SELECT boosters FROM " + Booster.tablename + " WHERE uuid='" + p.getUniqueId() + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(exec);

            while (rs.next()) {
                return rs.getInt(1);
            }
            exec = "INSERT INTO " + Booster.tablename + " VALUES ('" + p.getUniqueId() + "', 0, 0)";
            Statement st1 = con.createStatement();
            st1.executeUpdate(exec);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public static void setBoosters(Player p, Player target, int i){
        getBoosters(target);
        try {
            String exec = "UPDATE " + Booster.tablename + " SET boosters="+i+" WHERE uuid='" + target.getUniqueId() + "'";
            Statement st = con.createStatement();
            st.executeUpdate(exec);
        }catch (Exception ex){
            return;
        }

        p.sendMessage("Booster des Spielers " + target.getName() + " erfolgreich auf " + i + " Booster gesetzt!");
    }

    public static void deleteOneBooster(Player p, int i){
        try {
            String exec = "UPDATE " + Booster.tablename + " SET boosters="+ (i-1) +" WHERE uuid='" + p.getUniqueId() + "'";
            Statement st = con.createStatement();
            st.executeUpdate(exec);
        }catch (Exception ex){
            return;
        }
    }

    public static void handleBreak(Player p, int i){
        deleteOneBooster(p, i);
        if (!Booster.breakbool){
            Booster.breaktimer = 1800;
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("§f§l" + p.getName() + " §ahat einen §d§lBreak-Booster §afür §d§l30 Minuten §agezündet!");
                pl.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 9999999, 5));
            }
            Booster.breakbool = true;
        }else{
            Booster.breaktimer = Booster.breaktimer + 1800;
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("§f§l" + p.getName() + " §ahat einen §d§lBreak-Booster §afür §d§l30 Minuten §agezündet!");
                pl.sendMessage("§aDer §d§lBreak-Booster §aläuft nun insgesamt §d§l" + Booster.breaktimer/60 + " Minuten!");
            }
        }

    }

    public static void handleFly(Player p, int i){
       deleteOneBooster(p, i);
        if (Booster.fly){
            Booster.flytimer = Booster.flytimer + 1800;
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("§f§l" + p.getName() + " §ahat einen §d§lFly-Booster §afür §d§l30 Minuten §agezündet!");
                pl.sendMessage("§aDer §d§lFly-Booster §aläuft nun insgesamt §d§l" + Booster.flytimer/60 + " Minuten!");
            }
        }else {
            Booster.fly = true;
            Booster.flytimer = 1800;
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.setAllowFlight(true);
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("  ");
                pl.sendMessage("§f§l" + p.getName() + " §ahat einen §d§lFly-Booster §afür §d§l30 Minuten §agezündet!");
            }
        }
    }
    //Database aufbau: uuid, boosters, exboosters
    //                 String, int, int
}
