package de.polocloud.plugin.signs.commands;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CloudSignsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) {
            System.out.println("You must be a player.");
            return false;
        }

        Player player = (Player) sender;

        /**
         *
         * cloudsigns add (groups)
         *
         */

        if(args.length >= 1) {

            Block targetBlock = player.getTargetBlock(null, 5);

            if(!(targetBlock.getState().getData() instanceof WallSign)) {
                player.sendMessage("§cYou must look on a wall sign.");
                return false;
            }

            if(args[0].equalsIgnoreCase("add")) {

                //TODO SEND PACKET

                return false;
            }

            if(args[0].equalsIgnoreCase("remove")) {

                return false;
            }


        }
        return false;
    }

}
