package com.github.dreamhead.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LeastAvailabilityParkingBoyTest extends BaseParkingBoyTest {
    @Override
    protected ParkingBoy newParkingRole(ParkingLot lot1, ParkingLot lot2) {
        return new ParkingBoy(lot1, lot2, new LeastAvailabilityParkingStrategy());
    }

    @Test
    public void should_park_first_while_first_lot_has_least_availability() {
        parkable1 = new ParkingLot(1);
        parkable2 = new ParkingLot(2);
        role = newParkingRole(parkable1, parkable2);
        role.park(car);
        assertThat(parkable1.hasCar(car)).isTrue();
        assertThat(parkable2.hasCar(car)).isFalse();
    }

    @Test
    public void should_park_second_while_second_lot_has_least_availability() {
        parkable1 = new ParkingLot(2);
        parkable2 = new ParkingLot(1);
        role = newParkingRole(parkable1, parkable2);
        role.park(car);
        assertThat(parkable1.hasCar(car)).isFalse();
        assertThat(parkable2.hasCar(car)).isTrue();
    }

    @Test
    public void should_park_first_while_two_lots_have_same_availability() {
        parkable1 = new ParkingLot(2);
        parkable2 = new ParkingLot(2);
        role = newParkingRole(parkable1, parkable2);
        role.park(car);
        assertThat(parkable1.hasCar(car)).isTrue();
        assertThat(parkable2.hasCar(car)).isFalse();
    }
}
