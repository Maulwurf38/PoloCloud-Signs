package de.polocloud.plugin.signs.common;

import com.google.common.collect.Lists;
import de.polocloud.api.CloudAPI;
import de.polocloud.api.service.IService;
import de.polocloud.api.service.utils.ServiceState;
import de.polocloud.api.service.utils.ServiceVisibility;
import lombok.Getter;

import java.util.List;

public class CloudSignHandler {

    @Getter private static CloudSignHandler instance;

    private static final List<CloudSign> cloudSigns = Lists.newArrayList();

    public CloudSignHandler() {
        instance = this;

    }

    private List<IService> getPossibleServices() {
        return CloudAPI.getInstance().getServiceManager().getAllServicesByState(ServiceState.ONLINE).stream().filter(it -> it.getServiceVisibility() == ServiceVisibility.VISIBLE).toList();
    }

}
