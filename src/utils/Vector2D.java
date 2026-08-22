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
    public Vector2D subtract(double x, double y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D divide(double scalar) throws ArithmeticException {
        if (scalar != 0) return new Vector2D(this.x / scalar, this.y / scalar);
        throw new ArithmeticException("Division by zero is obviously impossible, dummy");
    }

    public Vector2D negate() {
        return new Vector2D(-this.x, -this.y);
    }

    public Vector2D reflect(Vector2D normal, double restitution) {
        Vector2D normalised = normal.normalise();
        double dot = this.dot(normalised);

        // Safety net
        if (dot >= 0) return this;

        return this.subtract(normalised.multiply((1 + restitution) * dot));
    }

    public Vector2D reflect(Vector2D normal) {
        return this.reflect(normal, 1f);
    }

    public Vector2D directionTo(Vector2D other) {
        return other.subtract(this).normalise();
    }

    public static Vector2D directionFromDegrees(double degrees) {
        double radians = Math.toRadians(degrees);

        return new Vector2D(Math.cos(radians), Math.sin(radians));
    }

    // Number-returning methods
    public double magnitude() {
        return Math.hypot(x, y);
    }

    public double magnitudeSquared() {
        return x * x + y * y;
    }

    public double distanceTo(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.hypot(dx, dy);
    }

    public double distanceToSquared(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return dx * dx + dy * dy;
    }

    // Important (and most used) methods
    public Vector2D normalise() {
        double magnitude = magnitude();
        return magnitude == 0 ? ZERO : new Vector2D(this.x / magnitude, this.y / magnitude);
    }

    public boolean isZero(double epsilon) {
        return Math.abs(this.x) < epsilon && Math.abs(this.y) < epsilon;
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