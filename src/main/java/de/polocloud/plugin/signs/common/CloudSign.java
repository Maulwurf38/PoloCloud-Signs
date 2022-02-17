package de.polocloud.plugin.signs.common;

import de.polocloud.api.service.IService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;

@Setter
@Getter
@RequiredArgsConstructor
public class CloudSign {

    private final Location location;
    private final String[] possibleGroups;

    private IService service;


}
