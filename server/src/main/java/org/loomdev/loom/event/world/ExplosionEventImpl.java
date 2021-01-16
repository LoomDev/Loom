package org.loomdev.loom.event.world;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.world.ExplosionEvent;
import org.loomdev.api.world.Explosion;
import org.loomdev.loom.event.EventImpl;
import org.loomdev.loom.world.ExplosionImpl;

import java.util.Iterator;

public class ExplosionEventImpl extends EventImpl implements ExplosionEvent {

    private final net.minecraft.world.level.Explosion explosion;

    public ExplosionEventImpl(net.minecraft.world.level.Explosion explosion) {
        this.explosion = explosion;
    }

    @NotNull
    @Override
    public Explosion getExplosion() {
        return new ExplosionImpl(explosion);
    }

    public net.minecraft.world.level.Explosion getMinecraftExplosion() {
        return explosion;
    }

    public static class PreImpl extends ExplosionEventImpl implements ExplosionEvent.Pre {

        private boolean canceled;

        public PreImpl(net.minecraft.world.level.Explosion explosion) {
            super(explosion);
        }

        @Override
        public boolean isCanceled() {
            return canceled;
        }

        @Override
        public void setCanceled(boolean canceled) {
            this.canceled = canceled;
        }
    }

    public static class DetonateImpl extends ExplosionEventImpl implements ExplosionEvent.Detonate {

        private boolean canceled;

        public DetonateImpl(net.minecraft.world.level.Explosion explosion) {
            super(explosion);
        }

        @Override
        public boolean isCanceled() {
            return canceled;
        }

        @Override
        public void setCanceled(boolean canceled) {
            this.canceled = canceled;
        }

        public Iterator<BlockPos> getMinecraftAffectedBlocks() {
            return getExplosion().getAffectedBlocks().stream()
                    .map(org.loomdev.api.block.BlockPointer::getLocation)
                    .map(location -> new BlockPos(location.getX(), location.getY(), location.getZ()))
                    .iterator();
        }
    }

    public static class PostImpl extends ExplosionEventImpl implements ExplosionEvent.Post {

        public PostImpl(net.minecraft.world.level.Explosion explosion) {
            super(explosion);
        }
    }
}
