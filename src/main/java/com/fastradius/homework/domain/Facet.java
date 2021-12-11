package com.fastradius.homework.domain;

import javax.vecmath.Point3d;

/**
 * Represents a single facet from an STL file. Each facet has 3 vertices which define a triangle of the solid.
 */
public class Facet {
    private final Point3d a;
    private final Point3d b;
    private final Point3d c;

    public Facet(Point3d a, Point3d b, Point3d c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Calculates the surface area of this facet triangle, using the vertices it was initialized with
     * @return A Double representing the surface area for this facet.
     */
    public Double getSurfaceArea() {
        // TODO In cases with millions of triangles, look into whether the division performance boost from bit shifting worth it
        final double distanceAtoB = a.distance(b);
        final double distanceAtoC = a.distance(c);
        final double distanceBtoC = b.distance(c);
        final double surface = (distanceAtoB + distanceAtoC + distanceBtoC) / 2;
        return Math.abs(Math.sqrt(surface * (surface - distanceAtoB) * (surface - distanceAtoC) * (surface - distanceBtoC)));
    }

    public Point3d getA() {
        return this.a;
    }

    public Point3d getB() {
        return this.b;
    }

    public Point3d getC() {
        return this.c;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Facet)) return false;
        final Facet other = (Facet) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$a = this.getA();
        final Object other$a = other.getA();
        if (this$a == null ? other$a != null : !this$a.equals(other$a)) return false;
        final Object this$b = this.getB();
        final Object other$b = other.getB();
        if (this$b == null ? other$b != null : !this$b.equals(other$b)) return false;
        final Object this$c = this.getC();
        final Object other$c = other.getC();
        if (this$c == null ? other$c != null : !this$c.equals(other$c)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Facet;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $a = this.getA();
        result = result * PRIME + ($a == null ? 43 : $a.hashCode());
        final Object $b = this.getB();
        result = result * PRIME + ($b == null ? 43 : $b.hashCode());
        final Object $c = this.getC();
        result = result * PRIME + ($c == null ? 43 : $c.hashCode());
        return result;
    }

    public String toString() {
        return "Facet(a=" + this.getA() + ", b=" + this.getB() + ", c=" + this.getC() + ")";
    }
}
