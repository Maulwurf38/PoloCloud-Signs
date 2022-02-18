package de.polocloud.plugin.signs.bootstrap;

import de.polocloud.plugin.signs.commands.CloudSignsCommand;
import de.polocloud.plugin.signs.common.CloudSignHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudSignsBootstrap extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        new CloudSignHandler();

        getCommand("cloudsigns").setExecutor(new CloudSignsCommand());
    }

    @Override
    public void onDisable() {

    }

}
