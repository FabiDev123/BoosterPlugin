package de.fabidev.booster;

import de.fabidev.booster.commands.BoosterCommand;
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
    }

    public void registerListeners(){
        //getServer().getPluginManager().registerEvents();
    }





}
