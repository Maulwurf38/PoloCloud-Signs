package de.polocloud.plugin.signs.config;

import de.polocloud.api.service.CloudService;

public class SignConverter {

    public static String convertLine(String line, CloudService cloudService) {

        return line.replaceAll("%server%", cloudService.getName())
                .replaceAll("%motd%", cloudService.getMotd())
                .replaceAll("%group%", cloudService.getGroup().getName())
                .replaceAll("%players%", String.valueOf(cloudService.getOnlineCount()))
            .replaceAll("%max_players%", String.valueOf(cloudService.getMaxPlayers()));
    }

}
