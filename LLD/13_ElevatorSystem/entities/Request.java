package entities;

import enums.Direction;
import enums.RequestSource;

public class Request {
    public final Direction direction;
    private final RequestSource source;
    public int targetFloor;

    public Request(int targetFloor, Direction direction, RequestSource source) {
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.source = source;
    }


    public int getTargetFloor() {
        return targetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestSource getSource() {
        return source;
    }

    public String toString() {
        return source + " Request to floor " + targetFloor + ((source == RequestSource.EXTERNAL) ? " going " + direction : " ");
    }
}