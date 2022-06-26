package com.github.dreamhead.parking;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot implements Parkable {
    private final Set<Car> lots;
    private final int capacity;

    public ParkingLot(final int capacity) {
        this.capacity = capacity;
        this.lots = new HashSet<>();
    }

    @Override
    public void park(final Car car) {
        if (hasCar(car)) {
            throw new IllegalArgumentException("Car " + car + " has been parked");
        }

        if (this.lots.size() + 1 > capacity) {
            throw new RuntimeException("No lot for more car");
        }

        lots.add(car);
    }

    public boolean hasCar(final Car car) {
        return lots.contains(car);
    }

    @Override
    public void unpark(final Car car) {
        if (!hasCar(car)) {
            throw new IllegalArgumentException("Fail to unpark car " + car);
        }

        lots.remove(car);
    }

    @Override
    public boolean isFull() {
        return lots.size() == capacity;
    }

    @Override
    public int getAvailability() {
        return capacity - lots.size();
    }
}
