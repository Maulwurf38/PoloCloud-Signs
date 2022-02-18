package de.polocloud.plugin.signs.config;

import com.google.common.collect.Maps;
import de.polocloud.plugin.signs.common.CloudSignState;
import de.polocloud.plugin.signs.common.layout.CloudSignLayout;
import lombok.Getter;

import java.util.Map;

@Getter
public class SignConfig {

    private Map<CloudSignState, CloudSignLayout> layouts;

    public SignConfig(){
        layouts = Maps.newConcurrentMap();

        layouts.put(CloudSignState.SEARCHING, new CloudSignLayout(CloudSignState.SEARCHING, new String[]{"","Searching","service",""}));
        layouts.put(CloudSignState.ONLINE, new CloudSignLayout(CloudSignState.ONLINE, new String[]{"%server%","%motd%","%players%/%max_players%","§a§lOnline"}));
    }

}
