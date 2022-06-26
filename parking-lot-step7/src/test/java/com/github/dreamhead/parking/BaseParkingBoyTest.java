package com.github.dreamhead.parking;

public abstract class BaseParkingBoyTest extends BaseParkingRoleTest<ParkingBoy, ParkingLot, ParkingLot> {
    @Override
    protected ParkingLot firstParkable() {
        return new ParkingLot(1);
    }

    @Override
    protected ParkingLot secondParkable() {
        return new ParkingLot(1);
    }
}
