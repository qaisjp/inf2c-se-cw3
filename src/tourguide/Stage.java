package tourguide;


import java.util.ArrayList;

import static tourguide.Stage.StageType.*;

public class Stage {

    private StageType type;
    public Waypoint waypoint;
    public Leg leg;

    public StageType getType() {
        return type;
    }

    public enum StageType {
        FIRST, INTERMEDIATE, FINAL;
    }

    Stage(StageType type) {
        this.type = type;
    }

    boolean setWaypoint(Waypoint w) {
        // Cannot set the waypoint if already exists
        if (waypoint != null) {
            return false;
        }

        // Cannot set the waypoint if first
        if (type == FIRST) {
            return false;
        }

        waypoint = w;

        return true;
    }

    boolean setLeg(Leg l) {
        // Cannot set the leg if already exists
        if (leg != null) {
            return false;
        }

        // Cannot set the leg if final
        if (type == FINAL) {
            return false;
        }

        // Cannot set leg without waypoint, when in intermediate
        if (type == INTERMEDIATE && waypoint == null) {
            return false;
        }

        leg = l;
        return true;
    }

    boolean setFinal() {
        // Can only be final if is an intermediate stage
        if (type != INTERMEDIATE) {
            return false;
        }

        // Cannot be final if has a (trailing) leg
        if (leg != null) {
            return false;
        }

        // Cannot be final if empty stage
        if (waypoint == null) {
            return false;
        }

        type = FINAL;
        return true;
    }

    public boolean isFinal() {
        return type == FINAL;
    }

    public static int countWaypoints(ArrayList<Stage> stages) {
        int count = 0;
        for (Stage s : stages) {
            if (s.waypoint != null) {
                count += 1;
            }
        }
        return count;
    }


    public static int countLegs(ArrayList<Stage> stages) {
        int count = 0;
        for (Stage s : stages) {
            if (s.leg != null) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "type=" + type +
                ", waypoint=" + waypoint +
                ", leg=" + leg +
                '}';
    }
}
