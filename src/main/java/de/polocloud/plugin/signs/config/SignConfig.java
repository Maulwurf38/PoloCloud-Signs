package de.polocloud.plugin.signs.config;

import com.google.common.collect.Maps;
import de.polocloud.plugin.signs.common.CloudSignInfo;
import de.polocloud.plugin.signs.common.CloudSignState;
import de.polocloud.plugin.signs.common.layout.CloudSignLayout;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class SignConfig {

    private final Map<CloudSignState, CloudSignLayout> layouts;
    private final List<CloudSignInfo> cloudsigns;

    public SignConfig(){
        layouts = Maps.newConcurrentMap();

        layouts.put(CloudSignState.SEARCHING, new CloudSignLayout(CloudSignState.SEARCHING, new String[]{"","Searching","service",""}));
        layouts.put(CloudSignState.ONLINE, new CloudSignLayout(CloudSignState.ONLINE, new String[]{"§l%group%","§a✔ §8┃ §a§lOnline", "%motd%","%players%/%max_players%"}));

        cloudsigns = new ArrayList();
        cloudsigns.add(new CloudSignInfo(new Location(Bukkit.getWorld("world"), 26, 70, -92), "Lobby"));
        cloudsigns.add(new CloudSignInfo(new Location(Bukkit.getWorld("world"), 28, 70, -92), "Lobby"));
    }

}
