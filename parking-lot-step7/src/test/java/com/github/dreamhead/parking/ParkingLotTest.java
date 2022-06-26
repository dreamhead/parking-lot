package com.github.dreamhead.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkingLotTest {

    private ParkingLot lot;
    private Car car;

    @BeforeEach
    void setUp() {
        lot = new ParkingLot(2);
        car = new Car();
    }

    @Test
    public void should_park_car() {
        assertThat(lot.hasCar(car)).isFalse();
        lot.park(car);
        assertThat(lot.hasCar(car)).isTrue();
    }

    @Test
    public void should_not_park_same_car() {
        lot.park(car);

        assertThatThrownBy(() -> lot.park(car))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_unpark_parked_car() {
        lot.park(car);
        lot.unpark(car);
        assertThat(lot.hasCar(car)).isFalse();
    }

    @Test
    public void should_not_unpark_unknown_car() {
        assertThatThrownBy(() -> lot.unpark(car))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_not_park_car_while_parking_lot_is_full() {
        lot = new ParkingLot(0);
        assertThatThrownBy(() -> lot.park(car))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void should_print_status() {
        lot = new ParkingLot(1);
        assertThat(lot.getStatus()).isEqualTo("[ParkingLot: 1 total, 0 occupied, 1 available]");
        lot.park(car);
        assertThat(lot.getStatus()).isEqualTo("[ParkingLot: 1 total, 1 occupied, 0 available]");
    }
}