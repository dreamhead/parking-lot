package com.github.dreamhead.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingManagerTest extends BaseParkingRoleTest<ParkingManager, ParkingLot, ParkingBoy> {
    @Override
    protected ParkingManager newParkingRole(ParkingLot parkable1, ParkingBoy parkable2) {
        return new ParkingManager(parkable1, parkable2, new FirstAvailabilityParkingStrategy());
    }

    @Override
    protected ParkingLot firstParkable() {
        return new ParkingLot(1);
    }

    @Override
    protected ParkingBoy secondParkable() {
        return new ParkingBoy(new ParkingLot(1), new ParkingLot(1), new FirstAvailabilityParkingStrategy());
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

        assertThat(role.hasCar(car)).isTrue();
        assertThat(role.hasCar(secondCar)).isTrue();
        assertThat(role.getAvailability()).isEqualTo(1);
    }
}
