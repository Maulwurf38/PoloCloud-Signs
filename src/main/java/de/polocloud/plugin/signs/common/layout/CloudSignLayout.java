package de.polocloud.plugin.signs.common.layout;

import de.polocloud.plugin.signs.common.CloudSignState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CloudSignLayout {

    private CloudSignState cloudSignState;
    private String[] lines;

}
