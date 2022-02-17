package de.polocloud.plugin.signs.common;

import de.polocloud.api.service.IService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CloudSign extends CloudSignInfo {

    private IService service;
    private CloudSignState cloudSignState;

}
