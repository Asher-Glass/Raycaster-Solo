package utils;

public record Vector2D(double x, double y) {

    // Point of origin on the x and y axes
    public static final Vector2D ZERO = new Vector2D(0, 0);

    // Vector-returning methods
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D add(double x, double y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // Number-returning methods
    public double magnitude() {
        return Math.hypot(x, y);
    }

    // Important (and most used) methods
    public Vector2D normalise() {
        double magnitude = magnitude();
        return magnitude == 0 ? ZERO : new Vector2D(this.x / magnitude, this.y / magnitude);
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
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