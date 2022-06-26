package com.github.dreamhead.parking;

public class MostAvailabilityParkingBoy extends ParkingBoy {
    public MostAvailabilityParkingBoy(final ParkingLot lot1, final ParkingLot lot2) {
        super(lot1, lot2);
    }

    @Override
    public void park(final Car car) {
        if (lot1.hasCar(car) || lot2.hasCar(car)) {
            throw new IllegalArgumentException("Car " + car + " has been parked");
        }

        final int availability1 = lot1.getAvailability();
        final int availability2 = lot2.getAvailability();

        if (availability1 <= 0 && availability2 <= 0) {
            throw new RuntimeException("No lot for more car");
        }

        if (availability1 >= availability2) {
            this.lot1.park(car);
            return;
        }

        this.lot2.park(car);
    }
}
