package net.karen.mccoursemod.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum RhinoVariant {
    DEFAULT(0),
    WHITE(1); // RHINO id custom entity variants

    private static final RhinoVariant[] BY_ID =
            Arrays.stream(values()).sorted(Comparator.comparingInt(RhinoVariant::getId)).toArray(RhinoVariant[]::new);

    private final int id;

    RhinoVariant(int id) { this.id = id; }

    public int getId() { return this.id; }

    public static RhinoVariant byId(int id) { return BY_ID[id % BY_ID.length]; }
}