package com.github.dreamhead.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstAvailabilityParkingBoyTest extends BaseParkingBoyTest {
    @Override
    protected ParkingBoy newParkingRole(ParkingLot lot1, ParkingLot lot2) {
        return new ParkingBoy(lot1, lot2, new FirstAvailabilityParkingStrategy());
    }

    @Test
    public void should_park_first_lot_first() {
        role.park(car);
        assertThat(parkable1.hasCar(car)).isTrue();
        assertThat(parkable2.hasCar(car)).isFalse();
    }

    @Test
    public void should_park_second_lot_while_first_lot_is_full() {
        role.park(car);
        final Car secondCar = new Car();
        role.park(secondCar);
        assertThat(parkable1.hasCar(secondCar)).isFalse();
        assertThat(parkable2.hasCar(secondCar)).isTrue();
    }
}
