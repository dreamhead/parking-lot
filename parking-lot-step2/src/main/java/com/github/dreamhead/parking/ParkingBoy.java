package com.github.dreamhead.parking;

public class ParkingBoy {
    private final ParkingLot lot1;
    private final ParkingLot lot2;

    public ParkingBoy(final ParkingLot lot1, final ParkingLot lot2) {
        this.lot1 = lot1;
        this.lot2 = lot2;
    }


    public void park(final Car car) {
        if (lot1.hasCar(car) || lot2.hasCar(car)) {
            throw new IllegalArgumentException("Car " + car + " has been parked");
        }

        if (!lot1.isFull()) {
            lot1.park(car);
            return;
        }

        if (!lot2.isFull()) {
            lot2.park(car);
            return;
        }

        throw new RuntimeException("No lot for more cars");
    }

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
