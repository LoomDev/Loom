package org.loomdev.api.util;

import org.loomdev.api.math.vector.Vector3i;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Direction {

    DOWN(0, 1, Direction.AxisDirection.NEGATIVE, Direction.Axis.Y, new Vector3i(0, -1, 0)),
    UP(1, 0, Direction.AxisDirection.POSITIVE, Direction.Axis.Y, new Vector3i(0, 1, 0)),
    NORTH(2, 3, Direction.AxisDirection.NEGATIVE, Direction.Axis.Z, new Vector3i(0, 0, -1)),
    SOUTH(3, 2, Direction.AxisDirection.POSITIVE, Direction.Axis.Z, new Vector3i(0, 0, 1)),
    WEST(4, 5, Direction.AxisDirection.NEGATIVE, Direction.Axis.X, new Vector3i(-1, 0, 0)),
    EAST(5, 4, Direction.AxisDirection.POSITIVE, Direction.Axis.X, new Vector3i(1, 0, 0));

    private static final Map<Integer, Direction> ID_DIRECTION_MAP = Arrays.stream(values()).collect(Collectors.toMap(Direction::getId, d -> d));
    private final int id;
    private final int opposite;
    private final AxisDirection direction;
    private final Axis axis;
    private final Vector3i offset;

    Direction(int id, int opposite, AxisDirection direction, Axis axis, Vector3i offset) {
        this.id = id;
        this.opposite = opposite;
        this.direction = direction;
        this.axis = axis;
        this.offset = offset;
    }

    public int getId() {
        return id;
    }

    public AxisDirection getDirection() {
        return direction;
    }

    public Axis getAxis() {
        return axis;
    }

    public Direction getOpposite() {
        return ID_DIRECTION_MAP.get(this.opposite);
    }

    public Vector3i getOffset() {
        return offset;
    }

    public int getOffsetX() {
        return offset.getX();
    }

    public int getOffsetY() {
        return offset.getY();
    }

    public int getOffsetZ() {
        return offset.getZ();
    }

    public Direction rotateYClockwise() {
        switch (this) {
            case NORTH:
                return Direction.EAST;
            case SOUTH:
                return Direction.WEST;
            case WEST:
                return Direction.NORTH;
            case EAST:
                return Direction.SOUTH;
            default:
                throw new IllegalStateException("Unable to get clockwise Y rotation of " + this);
        }
    }

    public Direction rotateYCounterClockwise() {
        switch (this) {
            case NORTH:
                return Direction.WEST;
            case SOUTH:
                return Direction.EAST;
            case WEST:
                return Direction.SOUTH;
            case EAST:
                return Direction.NORTH;
            default:
                throw new IllegalStateException("Unable to get counter clockwise Y rotation of " + this);
        }
    }

    public Direction rotateXClockwise() {
        switch (this) {
            case UP:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.DOWN;
            case DOWN:
                return Direction.NORTH;
            case NORTH:
                return Direction.UP;
            default:
                throw new IllegalStateException("Unable to get clockwise X rotation of " + this);
        }
    }

    public Direction rotateXCounterclockwise() {
        switch (this) {
            case UP:
                return Direction.NORTH;
            case NORTH:
                return Direction.DOWN;
            case DOWN:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.UP;
            default:
                throw new IllegalStateException("Unable to get counter clockwise X rotation of " + this);
        }
    }

    public Direction rotateZClockwise() {
        switch (this) {
            case UP:
                return Direction.WEST;
            case WEST:
                return Direction.DOWN;
            case DOWN:
                return Direction.EAST;
            case EAST:
                return Direction.UP;
            default:
                throw new IllegalStateException("Unable to get clockwise Z rotation of " + this);
        }
    }

    public Direction rotateZCounterclockwise() {
        switch (this) {
            case UP:
                return Direction.EAST;
            case EAST:
                return Direction.DOWN;
            case DOWN:
                return Direction.WEST;
            case WEST:
                return Direction.UP;
            default:
                throw new IllegalStateException("Unable to get counter clockwise Z rotation of " + this);
        }
    }

    public static Direction getById(int id) {
        return ID_DIRECTION_MAP.get(id);
    }

    public static Direction getByName(String name) {
        return Direction.valueOf(name.toUpperCase());
    }

    public enum Type {
        HORIZONTAL,
        VERTICAL;
    }

    public enum Axis {
        X, Y, Z;

        public boolean isVertical() {
            return this == Y;
        }

        public boolean isHorizontal() {
            return this != Y;
        }

        public Type getType() {
            return this == Y ? Type.VERTICAL : Type.HORIZONTAL;
        }
    }

    public enum AxisDirection {
        POSITIVE(1),
        NEGATIVE(-1);

        private final int offset;

        AxisDirection(int offset) {
            this.offset = offset;
        }

        public int offset() {
            return this.offset;
        }

        public Direction.AxisDirection getOpposite() {
            return this == Direction.AxisDirection.POSITIVE ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE;
        }
    }
}
