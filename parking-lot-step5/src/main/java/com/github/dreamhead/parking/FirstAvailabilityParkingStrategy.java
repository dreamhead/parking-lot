package com.github.dreamhead.parking;

import java.util.Optional;

public class FirstAvailabilityParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<Parkable> getAvailableLot(final Parkable lot1, final Parkable lot2) {
        if (!lot1.isFull()) {
            return Optional.of(lot1);
        }

        if (!lot2.isFull()) {
            return Optional.of(lot2);
        }

        return Optional.empty();
    }
}
