package com.github.dreamhead.parking;

import java.util.Optional;

public interface ParkingStrategy {
    Optional<Parkable> getAvailableLot(Parkable lot1, Parkable lot2);
}
