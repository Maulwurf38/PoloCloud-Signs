package de.polocloud.plugin.signs.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;

@Getter
@AllArgsConstructor
public class CloudSignInfo {

    private Location location;
    private String possibleGroup;

}
