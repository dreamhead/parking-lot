package com.github.dreamhead.parking;

public class ParkingSuperManager extends ParkingRole {
    public ParkingSuperManager(final Parkable parkable1, final Parkable parkable2, final ParkingStrategy strategy) {
        super(parkable1, parkable2, strategy);
    }
}
