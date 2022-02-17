package de.polocloud.plugin.signs.common.events;

import de.polocloud.api.CloudAPI;
import de.polocloud.api.event.group.CloudServiceGroupUpdateEvent;
import de.polocloud.api.event.player.CloudPlayerUpdateEvent;
import de.polocloud.api.event.service.CloudServiceRemoveEvent;
import de.polocloud.api.event.service.CloudServiceUpdateEvent;

public class CollectiveCloudEvents {

    public CollectiveCloudEvents() {

        var eventHandler = CloudAPI.getInstance().getEventHandler();

        eventHandler.registerEvent(CloudServiceUpdateEvent.class, event -> {

        });

        eventHandler.registerEvent(CloudServiceRemoveEvent.class, event -> {

        });

        eventHandler.registerEvent(CloudServiceGroupUpdateEvent.class, event -> {

        });

        eventHandler.registerEvent(CloudPlayerUpdateEvent.class, event -> {

        });

    }
}
