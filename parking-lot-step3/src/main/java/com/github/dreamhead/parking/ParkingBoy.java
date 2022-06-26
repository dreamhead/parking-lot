package com.github.dreamhead.parking;

public abstract class ParkingBoy {
    protected final ParkingLot lot1;
    protected final ParkingLot lot2;

    public ParkingBoy(final ParkingLot lot1, final ParkingLot lot2) {
        this.lot1 = lot1;
        this.lot2 = lot2;
    }

    public abstract void park(Car car);

    public void unpark(final Car car) {
        if (lot1.hasCar(car)) {
            lot1.unpark(car);
            return;
        }

        if (lot2.hasCar(car)) {
            lot2.unpark(car);
            return;
        }

        throw new IllegalArgumentException("Fail to unpark car " + car);
    }
}
