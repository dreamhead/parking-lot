package com.github.dreamhead.parking;

import java.util.Optional;

public class MostAvailabilityParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<Parkable> getAvailableLot(final Parkable lot1, final Parkable lot2) {
        final int availability1 = lot1.getAvailability();
        final int availability2 = lot2.getAvailability();

        if (availability1 <= 0 && availability2 <= 0) {
            return Optional.empty();
        }

        if (availability1 >= availability2) {
            return Optional.of(lot1);
        } else {
            return Optional.of(lot2);
        }
    }
}
