package com.github.dreamhead.parking;

public interface Parkable {
    void park(Car car);

    void unpark(Car car);

    boolean hasCar(Car car);

    boolean isFull();

    int getAvailability();
}
