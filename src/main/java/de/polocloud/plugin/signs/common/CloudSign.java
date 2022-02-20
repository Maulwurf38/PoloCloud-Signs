package de.polocloud.plugin.signs.common;

import de.polocloud.api.CloudAPI;
import de.polocloud.api.service.CloudService;
import de.polocloud.plugin.signs.bootstrap.CloudSignsBootstrap;
import de.polocloud.plugin.signs.common.layout.CloudSignLayout;
import de.polocloud.plugin.signs.config.SignConverter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

@Setter
@Getter
public class CloudSign extends CloudSignInfo {

    private String service = null;
    private CloudSignState cloudSignState = CloudSignState.SEARCHING;

    public CloudSign(Location location, String possibleGroup) {
        super(location, possibleGroup);
        update();
    }

    public void create(CloudService cloudService) {
        this.service = cloudService.getName();
        cloudSignState = CloudSignState.ONLINE;
        update();
    }

    public void remove() {
        this.service = null;
        cloudSignState = CloudSignState.SEARCHING;
        update();
    }

    public void display(CloudSignLayout cloudSignLayout) {
        Bukkit.getScheduler().callSyncMethod(CloudSignsBootstrap.getPlugin(CloudSignsBootstrap.class), () -> {
            Sign sign = (Sign) getLocation().getBlock().getState();
            for (int i = 0; i < cloudSignLayout.getLines().length; i++) {
                sign.setLine(i, (cloudSignState == CloudSignState.ONLINE ? SignConverter.convertLine(cloudSignLayout.getLines()[i],
                    CloudAPI.getInstance().getServiceManager().getServiceByNameOrNull(service)) : cloudSignLayout.getLines()[i]));
            }
            sign.update(true, false);
            return null;
        });
    }

    public void update() {
        display(CloudSignHandler.getInstance().getConfig().getLayouts().get(cloudSignState));
    }
}
