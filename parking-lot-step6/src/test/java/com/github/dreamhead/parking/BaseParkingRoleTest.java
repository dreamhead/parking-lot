package com.github.dreamhead.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class BaseParkingRoleTest<T extends ParkingRole, U extends Parkable, V extends Parkable> {
    protected abstract T newParkingRole(U parkable1, V parkable2);
    protected abstract U firstParkable();
    protected abstract V secondParkable();

    protected U parkable1;
    protected V parkable2;
    protected T role;
    protected Car car;

    @BeforeEach
    void setUp() {
        parkable1 = firstParkable();
        parkable2 = secondParkable();
        role = newParkingRole(parkable1, parkable2);
        car = new Car();
    }

    @Test
    public void should_not_park_lot_while_role_are_full() {
        while (!role.isFull()) {
            role.park(new Car());
        }

        final Car thirdCar = new Car();
        assertThatThrownBy(() -> role.park(thirdCar))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void should_not_park_same_car() {
        role.park(car);
        assertThatThrownBy(() -> role.park(car))
                .isInstanceOf(IllegalArgumentException.class);

        final Car secondCar = new Car();
        role.park(secondCar);
        assertThatThrownBy(() -> role.park(secondCar))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_unpark_first_lot_car() {
        role.park(car);
        role.unpark(car);
        assertThat(parkable1.hasCar(car)).isFalse();
        assertThat(parkable2.hasCar(car)).isFalse();
    }

    @Test
    public void should_unpark_second_lot_car() {
        role.park(car);
        final Car secondCar = new Car();
        role.park(secondCar);
        role.unpark(secondCar);
        assertThat(parkable1.hasCar(secondCar)).isFalse();
        assertThat(parkable2.hasCar(secondCar)).isFalse();
    }

    @Test
    public void should_unpark_unknown_car() {
        assertThatThrownBy(() -> role.unpark(car))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
