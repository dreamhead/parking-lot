package com.github.dreamhead.parking;

public class FirstAvailabilityParkingBoy extends ParkingBoy {
    public FirstAvailabilityParkingBoy(final ParkingLot lot1, final ParkingLot lot2) {
        super(lot1, lot2);
    }


    @Override
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


}
