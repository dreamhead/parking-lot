package com.github.dreamhead.parking;

import java.util.Optional;

public class LeastAvailabilityParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> getAvailableLot(final ParkingLot lot1, final ParkingLot lot2) {
        final int availability1 = lot1.getAvailability();
        final int availability2 = lot2.getAvailability();

        if (availability1 <= 0 && availability2 <= 0) {
            return Optional.empty();
        }

        if (availability1 <= availability2 && availability1 > 0) {
            return Optional.of(lot1);
        } else {
            return Optional.of(lot2);
        }
    }
}
