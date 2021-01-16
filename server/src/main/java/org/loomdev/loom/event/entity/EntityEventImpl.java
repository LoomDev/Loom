package org.loomdev.loom.event.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.entity.EntityEvent;
import org.loomdev.loom.block.BlockPointerImpl;
import org.loomdev.loom.event.EventImpl;

public class EntityEventImpl extends EventImpl implements EntityEvent, Cancelable {

    private final Entity entity;
    private boolean canceled;

    public EntityEventImpl(Entity entity) {
        this.entity = entity;
    }

    @Override
    @NotNull
    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public static class MobGriefImpl extends EntityEventImpl implements EntityEvent.MobGrief {

        private final BlockPointer pointer;

        public MobGriefImpl(Entity entity, Level level, BlockPos pos) {
            super(entity);
            this.pointer = new BlockPointerImpl(level, pos);
        }

        @Override
        @NotNull
        public BlockPointer getModifiedPointer() {
            return pointer;
        }
    }
}
