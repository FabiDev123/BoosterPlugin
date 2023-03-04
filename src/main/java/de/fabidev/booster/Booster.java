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

import java.sql.SQLException;

public class Booster extends JavaPlugin {


    public static Booster main;

    public static String address;
    public static String username;
    public static String password;
    public static String dbname;
    public static String tablename;


    public static boolean fly;
    public static int flytimer = -1;

    public static boolean breakbool;

    public static int breaktimer = -1;

    public static int mob = 0;
    public static int[] mobtimer = new int[3];

    public static int drop = 0;

    public static int[] droptimer = new int[3];


    public static int xp = 0;
    public static int[] xptimer = new int[4];




    @Override
    public void onEnable(){
        main = this;
        registerCommands();
        registerListeners();
        myLoadConfig();
        Boosters.connectToDB();
        runAllTimers();
        initAllArrays();

    }



    @Override
    public void onDisable(){
        try {
            Boosters.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initAllArrays(){
        for (int i = 0; i<mobtimer.length; i++){
            mobtimer[i] = -1;
        }
        for (int i = 0; i<droptimer.length; i++){
            droptimer[i] = -1;
        }
        for (int i = 0; i<xptimer.length; i++){
            xptimer[i] = -1;
        }
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

        //mob

        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<mobtimer.length; i++){
                    switch (mobtimer[i]){
                        case 0:
                            Bukkit.getServer().getScheduler().runTask(Booster.main, new Runnable() {
                                @Override
                                public void run() {
                                    handleDeactivateMob();
                                }
                            });

                            mobtimer[i] = -1; break;
                        case 30, 15, 3, 2, 1: sendMessageMob(mobtimer[i]); mobtimer[i]--; break;
                        case -1: break;
                        default: mobtimer[i]--; break;
                    }
                }
            }
        }, 0, 20);

        //drop
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<droptimer.length; i++){
                    switch (droptimer[i]){
                        case 0:
                            Bukkit.getServer().getScheduler().runTask(Booster.main, new Runnable() {
                                @Override
                                public void run() {
                                    handleDeactivateDrop();
                                }
                            });

                            droptimer[i] = -1; break;
                        case 30, 15, 3, 2, 1: sendMessageDrop(droptimer[i]); droptimer[i]--; break;
                        case -1: break;
                        default: droptimer[i]--; break;
                    }
                }
            }
        }, 0, 20);


        //xp

        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<xptimer.length; i++){
                    switch (xptimer[i]){
                        case 0:
                            Bukkit.getServer().getScheduler().runTask(Booster.main, new Runnable() {
                                @Override
                                public void run() {
                                    handleDeactivateXP();
                                }
                            });

                            xptimer[i] = -1; break;
                        case 30, 15, 3, 2, 1: sendMessageXP(xptimer[i]); xptimer[i]--; break;
                        case -1: break;
                        default: xptimer[i]--; break;
                    }
                }
            }
        }, 0, 20);




    }

    public static void sendMessageXP(int i){
        if (i == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lXP-Booster §c§lläuft in §a§l" + i + " §c§lSekunde ab!");
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("§c§lDer §d§lXP-Booster §c§lläuft in §a§l" + i + " §c§lSekunden ab!");
        }
    }

    public static void handleDeactivateXP(){
        if (xp == 4){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lXP-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lXP-Booster: §a§l3");
            }
            xp--;
        }else if (xp == 3){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lXP-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lXP-Booster: §a§l2");
            }
            xp--;
        }else if (xp == 2){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lXP-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lXP-Booster: §a§l1");
            }
            xp--;
        }else if (xp == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lXP-Booster §c§list nun komplett deaktiviert!");
            }
            xp--;
        }
    }




    public static void sendMessageDrop(int i){
        if (i == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lDrop-Booster §c§lläuft in §a§l" + i + " §c§lSekunde ab!");
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("   ");
            p.sendMessage("§c§lDer §d§lDrop-Booster §c§lläuft in §a§l" + i + " §c§lSekunden ab!");
        }
    }

    public static void handleDeactivateDrop(){
        if (drop == 3){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lDrop-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lDrop-Booster: §a§l2");
            }
            drop--;
        }else if (drop == 2){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lDrop-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lDrop-Booster: §a§l1");
            }
            drop--;
        }else if (drop == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lDrop-Booster §c§list nun komplett deaktiviert!");
            }
            drop--;
        }
    }


    public static void sendMessageMob(int i){
            if (i == 1){
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.sendMessage("   ");
                    p.sendMessage("   ");
                    p.sendMessage("   ");
                    p.sendMessage("   ");
                    p.sendMessage("   ");
                    p.sendMessage("§c§lDer §d§lMob-Booster §c§lläuft in §a§l" + i + " §c§lSekunde ab!");
                }
            }
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lMob-Booster §c§lläuft in §a§l" + i + " §c§lSekunden ab!");
            }
    }

    public static void handleDeactivateMob(){
        if (mob == 3){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lMob-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lMob-Booster: §a§l2");
            }
            mob--;
        }else if (mob == 2){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lEin §d§lMob-Booster §c§list nun deaktiviert!");
                p.sendMessage("§a§lVerbleibende §d§lMob-Booster: §a§l1");
            }
            mob--;
        }else if (mob == 1){
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("   ");
                p.sendMessage("§c§lDer §d§lMob-Booster §c§list nun komplett deaktiviert!");
            }
            mob--;
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
