package com.fastradius.homework.domain;

import java.util.List;

public class Solid {
    private List<Facet> facets;

    public Solid() {
    }

    public List<Facet> getFacets() {
        return this.facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Solid)) return false;
        final Solid other = (Solid) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$facets = this.getFacets();
        final Object other$facets = other.getFacets();
        if (this$facets == null ? other$facets != null : !this$facets.equals(other$facets)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Solid;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $facets = this.getFacets();
        result = result * PRIME + ($facets == null ? 43 : $facets.hashCode());
        return result;
    }

    public String toString() {
        return "Solid(facets=" + this.getFacets() + ")";
    }
}
