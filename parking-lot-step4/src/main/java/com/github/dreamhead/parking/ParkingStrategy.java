package com.github.dreamhead.parking;

import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingLot> getAvailableLot(ParkingLot lot1, ParkingLot lot2);
}
