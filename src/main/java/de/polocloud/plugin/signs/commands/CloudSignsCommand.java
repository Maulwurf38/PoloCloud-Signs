package de.polocloud.plugin.signs.commands;

import de.polocloud.api.CloudAPI;
import de.polocloud.plugin.signs.common.CloudSignHandler;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CloudSignsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof final Player player)) {
            System.out.println("You must be a player.");
            return false;
        }

        if(args.length >= 1) {

            final var targetBlock = player.getTargetBlock(null, 5);

            if(!(targetBlock.getState() instanceof Sign)) {
                player.sendMessage("§cYou must look on a wall sign.");
                return false;
            }

            if(args[0].equalsIgnoreCase("add")) {
                CloudAPI.getInstance().getGroupManager().getServiceGroupByName(args[1]).ifPresentOrElse(it -> {
                    CloudSignHandler.getInstance().addCloudSign(targetBlock.getLocation(), it.getName());
                    player.sendMessage("§aYou set a new sign.");
                    CloudSignHandler.getInstance().confirmSigns();
                }, () -> player.sendMessage("§cNo group :("));
                return false;
            }

            if(args[0].equalsIgnoreCase("remove")) {

                return false;
            }


        }
        return false;
    }

}
