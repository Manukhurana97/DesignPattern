package entity;

public enum VehicleType {
	BIKE(1, 10),
	CAR(2, 40),
	TRUCK(3, 100);

	private final int size;
	private final int fee;


	VehicleType(int size, int fee) {
		this.size = size;
		this.fee = fee;
	}

	public int getSize() { return size; }
	public int getFee() {return fee;}
}