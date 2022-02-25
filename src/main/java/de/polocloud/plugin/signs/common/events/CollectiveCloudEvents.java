package de.polocloud.plugin.signs.common.events;

import de.polocloud.api.CloudAPI;
import de.polocloud.api.event.group.CloudServiceGroupUpdateEvent;
import de.polocloud.api.event.player.CloudPlayerUpdateEvent;
import de.polocloud.api.event.service.CloudServiceRemoveEvent;
import de.polocloud.api.event.service.CloudServiceUpdateEvent;
import de.polocloud.api.service.ServiceState;
import de.polocloud.plugin.signs.bootstrap.CloudSignsBootstrap;
import de.polocloud.plugin.signs.common.CloudSign;
import de.polocloud.plugin.signs.common.CloudSignHandler;
import de.polocloud.plugin.signs.common.CloudSignState;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CollectiveCloudEvents implements Listener {

    public CollectiveCloudEvents() {

        var eventHandler = CloudAPI.getInstance().getEventHandler();

        eventHandler.registerEvent(CloudServiceUpdateEvent.class, event -> {

            CloudSignHandler.getInstance().getSignByService(event.getService().getName()).ifPresentOrElse(cloudSign -> {
                if(event.getService().getState().equalsIgnoreCase("INGAME")) {
                    cloudSign.remove();
                    CloudSignHandler.getInstance().confirmSigns();
                }
            }, () -> {
                if (event.getService().getState().equalsIgnoreCase(ServiceState.ONLINE)) {
                    CloudSignHandler.getInstance().getPossibleSignByGroup(event.getService().getGroup().getName()).ifPresent(it -> it.create(event.getService()));
                }
            });
        });

        eventHandler.registerEvent(CloudServiceGroupUpdateEvent.class, event -> {
            List<CloudSign> signs = CloudSignHandler.getInstance().getCloudSigns().stream().filter(it -> it.getPossibleGroup().equalsIgnoreCase(event.getServiceGroup().getName())).collect(Collectors.toList());
            for (CloudSign sign : signs) {
                if (event.getServiceGroup().isMaintenance() && sign.getCloudSignState() != CloudSignState.MAINTENANCE) {
                    sign.setService(null);
                    sign.setCloudSignState(CloudSignState.MAINTENANCE);
                } else if (sign.getCloudSignState() == CloudSignState.MAINTENANCE) {
                    sign.setCloudSignState(CloudSignState.SEARCHING);
                }
                sign.update();
            }
            if(!event.getServiceGroup().isMaintenance()) {
                CloudSignHandler.getInstance().confirmSigns();
            }

        });

        eventHandler.registerEvent(CloudServiceRemoveEvent.class, event -> {
            System.out.println(event.getService());
            CloudSignHandler.getInstance().getSignByService(event.getService()).ifPresent(it -> it.remove());
        });

        eventHandler.registerEvent(CloudPlayerUpdateEvent.class, event -> {
            if (event.getUpdateReason() == CloudPlayerUpdateEvent.UpdateReason.SERVER_SWITCH)
                CloudSignHandler.getInstance().updateAllSigns();
        });

        Bukkit.getPluginManager().registerEvents(this, CloudSignsBootstrap.getPlugin(CloudSignsBootstrap.class));
    }

    @EventHandler
    public void handle(PlayerInteractEvent event) {

        if (event.getClickedBlock() == null) return;

        if (event.getClickedBlock().getState() instanceof Sign sign) {
            CloudSignHandler.getInstance().getCloudSign(sign.getLocation()).ifPresent(it ->
                CloudAPI.getInstance().getServiceManager().getService(it.getService()).ifPresent(cloudService ->
                CloudAPI.getInstance().getPlayerManager().getCloudPlayer(event.getPlayer().getUniqueId()).ifPresent(player -> {

                    if(cloudService.isFull()) {
                        player.sendMessage("§cDieser Service ist bereits schon voll.");
                    }else {
                        player.connect(cloudService);
                    }
                })));
        }
    }

}
