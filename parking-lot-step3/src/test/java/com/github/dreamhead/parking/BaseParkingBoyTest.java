package com.github.dreamhead.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class BaseParkingBoyTest<T extends ParkingBoy> {
    protected abstract T newParkingBoy(ParkingLot lot1, ParkingLot lot2);
    protected ParkingLot lot1;
    protected ParkingLot lot2;
    protected T boy;
    protected Car car;

    @BeforeEach
    void setUp() {
        lot1 = new ParkingLot(1);
        lot2 = new ParkingLot(1);
        boy = newParkingBoy(lot1, lot2);
        car = new Car();
    }

    @Test
    public void should_not_park_lot_while_two_lots_are_full() {
        boy.park(car);
        final Car secondCar = new Car();
        boy.park(secondCar);

        final Car thirdCar = new Car();
        assertThatThrownBy(() -> boy.park(thirdCar))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void should_not_park_same_car() {
        boy.park(car);
        assertThatThrownBy(() -> boy.park(car))
                .isInstanceOf(IllegalArgumentException.class);

        final Car secondCar = new Car();
        boy.park(secondCar);
        assertThatThrownBy(() -> boy.park(secondCar))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_unpark_first_lot_car() {
        boy.park(car);
        boy.unpark(car);
        assertThat(lot1.hasCar(car)).isFalse();
        assertThat(lot2.hasCar(car)).isFalse();
    }

    @Test
    public void should_unpark_second_lot_car() {
        boy.park(car);
        final Car secondCar = new Car();
        boy.park(secondCar);
        boy.unpark(secondCar);
        assertThat(lot1.hasCar(secondCar)).isFalse();
        assertThat(lot2.hasCar(secondCar)).isFalse();
    }

    @Test
    public void should_unpark_unknown_car() {
        assertThatThrownBy(() -> boy.unpark(car))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
