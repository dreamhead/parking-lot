package com.github.dreamhead.parking;

import java.util.Optional;

public abstract class ParkingRole implements Parkable {
    private final ParkingStrategy strategy;
    private final Parkable parkable1;
    private final Parkable parkable2;

    protected abstract String name();

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

    public String getStatus() {
        return getStatus(0);
    }

    @Override
    public String getStatus(final int level) {
        return "[" + summary() + "\n" + detail(level + 1) + "]";
    }

    private String detail(final int level) {
        return prefix(level)
                + this.parkable1.getStatus(level) + ","
                + "\n"
                + prefix(level)
                + this.parkable2.getStatus(level);
    }

    private String prefix(final int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    private String summary() {
        return this.name() + ": "
                + this.getCapacity() + " total, "
                + getOccupied() + " occupied, "
                + getAvailability() + " available";
    }

    private int getOccupied() {
        return this.getCapacity() - this.getAvailability();
    }

    @Override
    public int getCapacity() {
        return this.parkable1.getCapacity() + this.parkable2.getCapacity();
    }
}
