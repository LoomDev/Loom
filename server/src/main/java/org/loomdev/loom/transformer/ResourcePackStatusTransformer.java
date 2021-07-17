package org.loomdev.loom.transformer;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.ResourcePackStatus;

import static net.minecraft.network.protocol.game.ServerboundResourcePackPacket.Action;

public final class ResourcePackStatusTransformer implements Transformer<Action, ResourcePackStatus> {

    protected ResourcePackStatusTransformer() {
    }

    @Override
    @NotNull
    public Action toMinecraft(@NotNull ResourcePackStatus status) {
        return switch (status) {
            case SUCCESSFULLY_LOADED -> Action.SUCCESSFULLY_LOADED;
            case DECLINED -> Action.DECLINED;
            case FAILED_DOWNLOAD -> Action.FAILED_DOWNLOAD;
            case ACCEPTED -> Action.ACCEPTED;
            case UNKNOWN -> throw new IllegalStateException();
        };
    }

    @Override
    @NotNull
    public ResourcePackStatus toLoom(@NotNull Action action) {
        return switch (action) {
            case SUCCESSFULLY_LOADED -> ResourcePackStatus.SUCCESSFULLY_LOADED;
            case DECLINED -> ResourcePackStatus.DECLINED;
            case FAILED_DOWNLOAD -> ResourcePackStatus.FAILED_DOWNLOAD;
            case ACCEPTED -> ResourcePackStatus.ACCEPTED;
        };
    }
}
