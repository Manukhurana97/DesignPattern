package vehicle;

public enum VehicleType {
    SMALL(1, 10.0), MEDIUM(2, 20.0), LARGE(3, 50.0);

    private final int size;
    private final double fee;

    VehicleType(int size, double fee) {
        this.size = size;
        this.fee = fee;
    }

    public int getSize() {
        return size;
    }

    public double getFee() {
        return fee;
    }

}