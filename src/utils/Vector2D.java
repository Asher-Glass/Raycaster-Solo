package utils;

public record Vector2D(double x, double y) {

    // Vector-returning methods
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D add(double dx, double dy) {
        return new Vector2D(this.x + dx, this.y + dy);
    }
    public Vector2D subtract(double dx, double dy) {
        return new  Vector2D(this.x - dx, this.y - dy);
    }

    // Object-override methods
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector2D(double x1, double y1))) return false;
        return Double.compare(x1, this.x) == 0 && Double.compare(y1, this.y) == 0;
    }
}