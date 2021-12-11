package com.fastradius.homework.domain;

public class ShapeRepresentation {

    private Double surfaceArea;
    private Long numberOfTriangles;

    public ShapeRepresentation(Double surfaceArea, Long numberOfTriangles) {
        this.surfaceArea = surfaceArea;
        this.numberOfTriangles = numberOfTriangles;
    }

    public ShapeRepresentation() {
    }

    public static ShapeRepresentationBuilder builder() {
        return new ShapeRepresentationBuilder();
    }

    public Double getSurfaceArea() {
        return this.surfaceArea;
    }

    public Long getNumberOfTriangles() {
        return this.numberOfTriangles;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void setNumberOfTriangles(Long numberOfTriangles) {
        this.numberOfTriangles = numberOfTriangles;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ShapeRepresentation)) return false;
        final ShapeRepresentation other = (ShapeRepresentation) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$surfaceArea = this.getSurfaceArea();
        final Object other$surfaceArea = other.getSurfaceArea();
        if (this$surfaceArea == null ? other$surfaceArea != null : !this$surfaceArea.equals(other$surfaceArea)) return false;
        final Object this$numberOfTriangles = this.getNumberOfTriangles();
        final Object other$numberOfTriangles = other.getNumberOfTriangles();
        if (this$numberOfTriangles == null ? other$numberOfTriangles != null : !this$numberOfTriangles.equals(other$numberOfTriangles)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ShapeRepresentation;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $surfaceArea = this.getSurfaceArea();
        result = result * PRIME + ($surfaceArea == null ? 43 : $surfaceArea.hashCode());
        final Object $numberOfTriangles = this.getNumberOfTriangles();
        result = result * PRIME + ($numberOfTriangles == null ? 43 : $numberOfTriangles.hashCode());
        return result;
    }

    public String toString() {
        return "ShapeRepresentation(surfaceArea=" + this.getSurfaceArea() + ", numberOfTriangles=" + this.getNumberOfTriangles() + ")";
    }

    public static class ShapeRepresentationBuilder {
        private Double surfaceArea;
        private Long numberOfTriangles;

        ShapeRepresentationBuilder() {
        }

        public ShapeRepresentation.ShapeRepresentationBuilder surfaceArea(Double surfaceArea) {
            this.surfaceArea = surfaceArea;
            return this;
        }

        public ShapeRepresentation.ShapeRepresentationBuilder numberOfTriangles(Long numberOfTriangles) {
            this.numberOfTriangles = numberOfTriangles;
            return this;
        }

        public ShapeRepresentation build() {
            return new ShapeRepresentation(surfaceArea, numberOfTriangles);
        }

        public String toString() {
            return "ShapeRepresentation.ShapeRepresentationBuilder(surfaceArea=" + this.surfaceArea + ", numberOfTriangles=" + this.numberOfTriangles + ")";
        }
    }
}
