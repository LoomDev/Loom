package org.loomdev.loom.util.transformer;

import net.minecraft.network.protocol.game.ServerboundResourcePackPacket;
import org.loomdev.api.util.ResourcePackStatus;

public final class ResourcePackActionTransformer {

    private ResourcePackActionTransformer()
    {}

    public static ResourcePackStatus toLoom(ServerboundResourcePackPacket.Action action) {
        switch (action) {
            case SUCCESSFULLY_LOADED:
                return ResourcePackStatus.SUCCESSFULLY_LOADED;
            case DECLINED:
                return ResourcePackStatus.DECLINED;
            case FAILED_DOWNLOAD:
                return ResourcePackStatus.FAILED_DOWNLOAD;
            case ACCEPTED:
                return ResourcePackStatus.ACCEPTED;
        }
        return ResourcePackStatus.UNKNOWN;
    }

}
