package de.polocloud.plugin.signs.common.events;

import de.polocloud.api.CloudAPI;
import de.polocloud.api.event.player.CloudPlayerUpdateEvent;
import de.polocloud.api.event.service.CloudServiceRemoveEvent;
import de.polocloud.api.event.service.CloudServiceUpdateEvent;
import de.polocloud.api.service.utils.ServiceState;
import de.polocloud.api.service.utils.ServiceVisibility;
import de.polocloud.plugin.signs.bootstrap.CloudSignsBootstrap;
import de.polocloud.plugin.signs.common.CloudSignHandler;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CollectiveCloudEvents implements Listener {

    public CollectiveCloudEvents() {

        var eventHandler = CloudAPI.getInstance().getEventHandler();

        eventHandler.registerEvent(CloudServiceUpdateEvent.class, event -> {
            if (CloudSignHandler.getInstance().hasCloudSign(event.getService())) {

                return;
            }
            if (event.getService().getServiceState() == ServiceState.ONLINE) {
                CloudSignHandler.getInstance().getPossibleSignByGroup(event.getService().getGroup().getName()).ifPresent(it -> it.create(event.getService()));
            }
        });

        eventHandler.registerEvent(CloudServiceRemoveEvent.class, event -> CloudSignHandler.getInstance().getSignByService(event.getService()).ifPresent(it -> it.remove()));

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
                CloudAPI.getInstance().getPlayerManager().getCloudPlayer(event.getPlayer().getUniqueId()).ifPresent(player -> player.connect(cloudService))));
        }
    }

}
