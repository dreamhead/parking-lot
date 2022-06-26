package com.github.dreamhead.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingSuperManagerTest extends BaseParkingRoleTest<ParkingSuperManager, ParkingLot, ParkingManager> {
    @Override
    protected ParkingSuperManager newParkingRole(ParkingLot parkable1, ParkingManager parkable2) {
        return new ParkingSuperManager(parkable1, parkable2, new FirstAvailabilityParkingStrategy());
    }

    @Override
    protected ParkingLot firstParkable() {
        return new ParkingLot(1);
    }

    @Override
    protected ParkingManager secondParkable() {
        final ParkingBoy boy = new ParkingBoy(new ParkingLot(1), new ParkingLot(1), new FirstAvailabilityParkingStrategy());
        return new ParkingManager(new ParkingLot(1), boy, new FirstAvailabilityParkingStrategy());
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
        assertThat(role.getAvailability()).isEqualTo(2);
    }

    @Test
    public void should_print_status() {
        assertThat(role.getStatus()).isEqualTo("[ParkingSuperManager: 4 total, 0 occupied, 4 available\n"
                + "\t[ParkingLot: 1 total, 0 occupied, 1 available],\n"
                + "\t[ParkingManager: 3 total, 0 occupied, 3 available\n"
                + "\t\t[ParkingLot: 1 total, 0 occupied, 1 available],\n"
                + "\t\t[ParkingBoy: 2 total, 0 occupied, 2 available\n"
                + "\t\t\t[ParkingLot: 1 total, 0 occupied, 1 available],\n"
                + "\t\t\t[ParkingLot: 1 total, 0 occupied, 1 available]]]]"
        );
        role.park(car);
        assertThat(role.getStatus()).isEqualTo("[ParkingSuperManager: 4 total, 1 occupied, 3 available\n"
                + "\t[ParkingLot: 1 total, 1 occupied, 0 available],\n"
                + "\t[ParkingManager: 3 total, 0 occupied, 3 available\n"
                + "\t\t[ParkingLot: 1 total, 0 occupied, 1 available],\n"
                + "\t\t[ParkingBoy: 2 total, 0 occupied, 2 available\n"
                + "\t\t\t[ParkingLot: 1 total, 0 occupied, 1 available],\n"
                + "\t\t\t[ParkingLot: 1 total, 0 occupied, 1 available]]]]");
    }
}
