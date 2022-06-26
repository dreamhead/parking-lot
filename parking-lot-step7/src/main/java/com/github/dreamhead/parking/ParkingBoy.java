package com.github.dreamhead.parking;

public class ParkingBoy extends ParkingRole {
    public ParkingBoy(final ParkingLot lot1, final ParkingLot lot2, final ParkingStrategy strategy) {
        super(lot1, lot2, strategy);
    }

    @Override
    protected String name() {
        return "ParkingBoy";
    }
}
