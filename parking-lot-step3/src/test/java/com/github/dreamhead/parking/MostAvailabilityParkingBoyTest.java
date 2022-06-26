package com.github.dreamhead.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MostAvailabilityParkingBoyTest extends BaseParkingBoyTest<MostAvailabilityParkingBoy> {
    @Override
    protected MostAvailabilityParkingBoy newParkingBoy(final ParkingLot lot1, final ParkingLot lot2) {
        return new MostAvailabilityParkingBoy(lot1, lot2);
    }

    @Test
    public void should_park_first_while_first_lot_has_more_availability() {
        lot1 = new ParkingLot(2);
        lot2 = new ParkingLot(1);
        boy = new MostAvailabilityParkingBoy(lot1, lot2);
        boy.park(car);
        assertThat(lot1.hasCar(car)).isTrue();
        assertThat(lot2.hasCar(car)).isFalse();
    }

    @Test
    public void should_park_second_while_second_lot_has_more_availability() {
        lot1 = new ParkingLot(1);
        lot2 = new ParkingLot(2);
        boy = new MostAvailabilityParkingBoy(lot1, lot2);
        boy.park(car);
        assertThat(lot1.hasCar(car)).isFalse();
        assertThat(lot2.hasCar(car)).isTrue();
    }

    @Test
    public void should_park_first_while_two_lots_have_same_availability() {
        lot1 = new ParkingLot(2);
        lot2 = new ParkingLot(2);
        boy = new MostAvailabilityParkingBoy(lot1, lot2);
        boy.park(car);
        assertThat(lot1.hasCar(car)).isTrue();
        assertThat(lot2.hasCar(car)).isFalse();
    }
}
