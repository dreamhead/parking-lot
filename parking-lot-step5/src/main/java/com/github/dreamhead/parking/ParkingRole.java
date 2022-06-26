package com.github.dreamhead.parking;

import java.util.Optional;

public abstract class ParkingRole implements Parkable {
    private final ParkingStrategy strategy;
    private final Parkable parkable1;
    private final Parkable parkable2;

    public ParkingRole(final Parkable parkable1, final Parkable parkable2, final ParkingStrategy strategy) {
        this.parkable1 = parkable1;
        this.parkable2 = parkable2;
        this.strategy = strategy;
    }

    public void park(final Car car) {
        if (parkable1.hasCar(car) || parkable2.hasCar(car)) {
            throw new IllegalArgumentException("Car " + car + " has been parked");
        }

        final Optional<Parkable> lot = findAvailableLot();
        if (!lot.isPresent()) {
            throw new RuntimeException("No lot for more cars");
        }

        lot.get().park(car);
    }

    private Optional<Parkable> findAvailableLot() {
        return strategy.getAvailableLot(this.parkable1, this.parkable2);
    }

    public void unpark(final Car car) {
        if (parkable1.hasCar(car)) {
            parkable1.unpark(car);
            return;
        }

        if (parkable2.hasCar(car)) {
            parkable2.unpark(car);
            return;
        }

        throw new IllegalArgumentException("Fail to unpark car " + car);
    }

    @Override
    public boolean hasCar(final Car car) {
        return this.parkable1.hasCar(car) || this.parkable2.hasCar(car);
    }

    @Override
    public boolean isFull() {
        return this.parkable1.isFull() && this.parkable2.isFull();
    }

    @Override
    public int getAvailability() {
        return this.parkable1.getAvailability() + this.parkable2.getAvailability();
    }
}
