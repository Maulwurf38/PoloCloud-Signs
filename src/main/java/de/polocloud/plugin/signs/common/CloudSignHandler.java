package de.polocloud.plugin.signs.common;

import de.polocloud.api.CloudAPI;
import de.polocloud.api.service.CloudService;
import de.polocloud.api.service.utils.ServiceState;
import de.polocloud.api.service.utils.ServiceVisibility;
import de.polocloud.plugin.signs.common.events.CollectiveCloudEvents;
import de.polocloud.plugin.signs.config.SignConfig;
import lombok.Getter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class CloudSignHandler {

    @Getter private static CloudSignHandler instance;

    private static final List<CloudSign> cloudSigns = new ArrayList<>();
    private SignConfig config = new SignConfig();

    public CloudSignHandler() {
        instance = this;

        new CollectiveCloudEvents();
        confirmSigns();
    }

    public void confirmSigns() {
        getPossibleServices().forEach(it -> getPossibleSignByGroup(it.getGroup().getName()).ifPresent(cloudSign -> cloudSign.create(it)));
    }

    public Optional<CloudSign> getCloudSign(Location location) {
        return cloudSigns.stream().filter(it -> it.getLocation().equals(location)).findAny();
    }

    private List<CloudService> getPossibleServices() {
        return CloudAPI.getInstance().getServiceManager().getAllServicesByState(ServiceState.ONLINE).stream().filter(it -> it.getServiceVisibility() == ServiceVisibility.VISIBLE && !hasCloudSign(it)).toList();
    }

    public void addCloudSign(@NotNull Location location, @NotNull String group) {
        cloudSigns.add(new CloudSign(location, group));
    }

    public Optional<CloudSign> getPossibleSignByGroup(String groupName) {
        return cloudSigns.stream().filter(it -> it.getPossibleGroup().equalsIgnoreCase(groupName) && it.getService() == null).findAny();
    }

    public boolean hasCloudSign(CloudService cloudService) {
        return cloudSigns.stream().anyMatch(it -> it.getService() != null && it.getService().equalsIgnoreCase(cloudService.getName()));
    }

}
