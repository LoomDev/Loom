package org.loomdev.loom.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.GlowItemFrame;

public class GlowItemFrameImpl extends ItemFrameImpl implements GlowItemFrame {

    public GlowItemFrameImpl(net.minecraft.world.entity.decoration.GlowItemFrame entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<GlowItemFrame> getType() {
        return EntityType.GLOW_ITEM_FRAME;
    }
}
