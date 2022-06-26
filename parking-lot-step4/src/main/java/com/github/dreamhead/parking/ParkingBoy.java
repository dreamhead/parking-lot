package com.github.dreamhead.parking;

import java.util.Optional;

public class ParkingBoy {
    private final ParkingStrategy strategy;
    private final ParkingLot lot1;
    private final ParkingLot lot2;

    public ParkingBoy(final ParkingLot lot1, final ParkingLot lot2, final ParkingStrategy strategy) {
        this.lot1 = lot1;
        this.lot2 = lot2;
        this.strategy = strategy;
    }

    public void park(final Car car) {
        if (lot1.hasCar(car) || lot2.hasCar(car)) {
            throw new IllegalArgumentException("Car " + car + " has been parked");
        }

        final Optional<ParkingLot> lot = findAvailableLot();
        if (!lot.isPresent()) {
            throw new RuntimeException("No lot for more cars");
        }

        lot.get().park(car);
    }

    protected Optional<ParkingLot> findAvailableLot() {
        return strategy.getAvailableLot(this.lot1, this.lot2);
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
