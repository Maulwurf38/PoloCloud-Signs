package de.polocloud.plugin.signs.config;

import com.google.common.collect.Lists;
import de.polocloud.plugin.signs.common.CloudSignInfo;
import de.polocloud.plugin.signs.common.CloudSignState;
import de.polocloud.plugin.signs.common.layout.CloudSignLayout;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SignConfig {

    private Map<CloudSignState, CloudSignLayout> layouts = new HashMap<>();
    private List<CloudSignInfo> cloudsigns = Lists.newArrayList();

    public SignConfig(){
        layouts.put(CloudSignState.SEARCHING, new CloudSignLayout(CloudSignState.SEARCHING, new String[]{"","Searching for","service",""}));
        layouts.put(CloudSignState.MAINTENANCE, new CloudSignLayout(CloudSignState.MAINTENANCE, new String[]{"","This group is in","maintenance",""}));
        layouts.put(CloudSignState.ONLINE, new CloudSignLayout(CloudSignState.ONLINE, new String[]{"§l%group%","§a✔ §8┃ §a§lOnline", "%motd%","%players%/%max_players%"}));
    }
}
