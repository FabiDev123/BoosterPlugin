package de.fabidev.booster;

import de.fabidev.booster.commands.AdminboosterCommand;
import de.fabidev.booster.commands.AdminboosterexCommand;
import de.fabidev.booster.commands.BoosterCommand;
import de.fabidev.booster.commands.BoosterExtremeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Booster extends JavaPlugin {

    @Override
    public void onEnable(){
        registerCommands();
        registerListeners();
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
    }





}
