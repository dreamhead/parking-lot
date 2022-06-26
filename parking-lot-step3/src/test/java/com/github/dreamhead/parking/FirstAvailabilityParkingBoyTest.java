package com.github.dreamhead.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FirstAvailabilityParkingBoyTest extends BaseParkingBoyTest<FirstAvailabilityParkingBoy> {
    @Override
    protected FirstAvailabilityParkingBoy newParkingBoy(ParkingLot lot1, ParkingLot lot2) {
        return new FirstAvailabilityParkingBoy(lot1, lot2);
    }

    @Test
    public void should_park_first_lot_first() {
        boy.park(car);
        assertThat(lot1.hasCar(car)).isTrue();
        assertThat(lot2.hasCar(car)).isFalse();
    }

    @Test
    public void should_park_second_lot_while_first_lot_is_full() {
        boy.park(car);
        final Car secondCar = new Car();
        boy.park(secondCar);
        assertThat(lot1.hasCar(secondCar)).isFalse();
        assertThat(lot2.hasCar(secondCar)).isTrue();
    }
}
