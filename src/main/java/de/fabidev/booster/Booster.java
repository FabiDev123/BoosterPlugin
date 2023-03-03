package de.fabidev.booster;

import de.fabidev.booster.commands.AdminboosterCommand;
import de.fabidev.booster.commands.AdminboosterexCommand;
import de.fabidev.booster.commands.BoosterCommand;
import de.fabidev.booster.commands.BoosterExtremeCommand;
import de.fabidev.booster.listeners.JoinListener;
import de.fabidev.booster.utils.Boosters;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class Booster extends JavaPlugin {


    public static Booster main;

    public static String address;
    public static String username;
    public static String password;
    public static String dbname;
    public static String tablename;


    public static boolean fly;
    public static int flytimer = 0;

    public static boolean breakbool;

    public static int breaktimer = 0;




    @Override
    public void onEnable(){
        main = this;
        registerCommands();
        registerListeners();
        myLoadConfig();
        Boosters.connectToDB();
        runAllTimers();
    }



    @Override
    public void onDisable(){
    }


    public void registerCommands(){
        this.getCommand("booster").setExecutor(new BoosterCommand());
        this.getCommand("boosterextreme").setExecutor(new BoosterExtremeCommand());
        this.getCommand("adminbooster").setExecutor(new AdminboosterCommand());
        this.getCommand("adminboosterex").setExecutor(new AdminboosterexCommand());
    }

    public void registerListeners(){
        //getServer().getPluginManager().registerEvents();
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    public void myLoadConfig(){
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        address = getConfig().getString("address");
        username = getConfig().getString("username");
        password = getConfig().getString("password");
        dbname = getConfig().getString("dbname");
        tablename = getConfig().getString("tablename");
    }

    public void runAllTimers(){
        try {
            //fliegen:
            Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    switch (flytimer){
                        case 0:
                            Bukkit.getServer().getScheduler().runTask(Booster.main, new Runnable() {
                                @Override
                                public void run() {
                                    handleDeactivateFly();
                                }
                            });

                            flytimer = -1; break;
                        case 30, 15, 3, 2, 1: sendMessageFliegen(flytimer); flytimer--; break;
                        case -1: break;
                        default: flytimer--; break;
                    }
                }
            }, 0, 20);

            //break:

            Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    switch (breaktimer){
                        case 0:
                            Bukkit.getServer().getScheduler().runTask(Booster.main, new Runnable() {
                                @Override
                                public void run() {
                                    handleDeactivateBreak();
                                }
                            });

                            breaktimer = -1; break;
                        case 30, 15, 3, 2, 1: sendMessageBreak(breaktimer); breaktimer--; break;
                        case -1: break;
                        default: breaktimer--; break;
                    }
                }
            }, 0, 20);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void handleDeactivateBreak(){
        breakbool= false;
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("§c§lDer §d§lBreak-Booster §c§list nun deaktiviert!");
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
        }
    }

    public static void handleDeactivateFly(){
        fly = false;
        for (Player p : Bukkit.getOnlinePlayers()){
            if (!p.hasPermission("booster.fly.bypass")){
                p.setFlying(false);
                p.setAllowFlight(false);
            }
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("§c§lDer §d§lFliegen-Booster §c§list nun deaktiviert!");
        }
    }

    public static void sendMessageBreak(int i){
        if (i == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lBreak-Booster §c§lläuft in §a§l" + i + " §c§lSekunde ab!");
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("§c§lDer §d§lBreak-Booster §c§lläuft in §a§l" + i + " §c§lSekunden ab!");
        }
    }

    public static void sendMessageFliegen(int i){
        if (i == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lFliegen-Booster §c§lläuft in §a§l" + i + " §c§lSekunde ab!");
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("§c§lDer §d§lFliegen-Booster §c§lläuft in §a§l" + i + " §c§lSekunden ab!");
        }
    }





}
