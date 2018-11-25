package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingLots;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SmartParkingBoy;
import com.oocl.cultivation.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    /**
     * Given I have a parking lot and a car
     * When I park the car to the parking lot
     * Then I will be able to pick the car from the parking lot
     */
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(9));
        parkingLotList.add(new ParkingLot(7));

        ParkingLots parkingLots = new ParkingLots(parkingLotList);

        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("1234567");

        ParkingTicket lotAddress = parkingBoy.parkCar(parkingLots, car);
        System.out.println("normal_pick_car_from_lot: " + lotAddress);

        Car returnedCar = parkingBoy.pickCar(lotAddress);
        Assertions.assertEquals(car, returnedCar);
    }

    /**
     * Given the parking lot is full
     * When I park a car to the parking lot
     * Then I will fail to park the car
     */
    @Test
    public void park_when_full() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(5));
        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();

        for (int i = 0; i < 7; i++) {
            Car car = new Car(String.valueOf(i));
            parkingBoy.parkCar(parkingLots, car);
        }

        Car shdFail = new Car("fail");
        ParkingTicket lotAddress = parkingBoy.parkCar(parkingLots, shdFail);

        Assertions.assertEquals(lotAddress.getLotNumber(), -1);
    }


    /**
     * Given the parking lot is full
     * When I pick a car from the parking lot
     * Then I will be able to park a car to the parking lot
     */

    @Test
    public void park_after_pick_from_full() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(5));
        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();

        for (int i = 0; i < 7; i++) {
            Car car = new Car(String.valueOf(i));
            ParkingTicket lotAddress = parkingBoy.parkCar(parkingLots, car);

            if (i == 6) {
                parkingBoy.pickCar(lotAddress);
            }
        }

        Car shdOk = new Car("ok");
        ParkingTicket lotAddress = parkingBoy.parkCar(parkingLots, shdOk);
        System.out.println("park_after_pick_from_full: " + lotAddress);

        Assertions.assertNotEquals(lotAddress.getLotNumber(), -1);
    }

    @Test
    public void smart_pick_car_from_lot() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(9));
        parkingLotList.add(new ParkingLot(7));

        ParkingLots parkingLots = new ParkingLots(parkingLotList);

        SmartParkingBoy parkingBoy = new SmartParkingBoy();
        Car car = new Car("1234567");

        ParkingTicket lotAddress = parkingBoy.parkCar(parkingLots, car);
        System.out.println("smart_pick_car_from_lot: " + lotAddress);

        Car returnedCar = parkingBoy.pickCar(lotAddress);
        Assertions.assertEquals(car, returnedCar);
    }

    @Test
    public void super_pick_car_from_lot() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2)); /* 1/2 = 50% */
        parkingLotList.add(new ParkingLot(9)); /* 6/9 = 66% */
        parkingLotList.add(new ParkingLot(7)); /* 2/7 = 29% */

        ParkingLots parkingLots = new ParkingLots(parkingLotList);

        Car car = new Car("1st");
        parkingLots.getParkingLots().get(0).parkCar(car, new ParkingTicket(parkingLots.getParkingLots().get(0), 0));

        for (int i = 0; i < 3; i++) {
            Car car1 = new Car(String.valueOf(i));
            parkingLots.getParkingLots().get(1).parkCar(car1, new ParkingTicket(parkingLots.getParkingLots().get(1), i));
        }

        for (int i = 0; i < 5; i++) {
            Car car1 = new Car(String.valueOf(i));
            parkingLots.getParkingLots().get(2).parkCar(car1, new ParkingTicket(parkingLots.getParkingLots().get(2), i));
        }

        Car superCar = new Car("super");
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy();
        ParkingTicket lotAddress = superParkingBoy.parkCar(parkingLots, superCar);

        Assertions.assertEquals(9, lotAddress.getParkingLot().getTotal());
    }


    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(9));
        parkingLotList.add(new ParkingLot(7));

        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car firstCar = new Car("123");
        Car secondCar = new Car("456");

        ParkingTicket lotAddress = parkingBoy.parkCar(parkingLots, firstCar);
        ParkingTicket lotAddress2 = parkingBoy.parkCar(parkingLots, secondCar);

        Car fetchedByFirstTicket = parkingBoy.pickCar(lotAddress);
        Car fetchedBySecondTicket = parkingBoy.pickCar(lotAddress2);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }
}


   /* @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("888888");
        ParkingTicket wrongTicket = new ParkingTicket(parkingLots.getParkingLots().get(2),1);

        ParkingTicket ticket = parkingBoy.parkCar(parkingLots, car);

        assertNull(parkingBoy.pickCar(wrongTicket));
        assertSame(car, parkingBoy.pickCar(ticket));
    }


    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket wrongTicket = new ParkingTicket(parkingLots.getParkingLots().get(2), 1);

        parkingBoy.pickCar(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }


    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.pickCar(wrongTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.parkCar(new Car("880663"));
        assertNotNull(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("333333");

        ParkingTicket ticket = parkingBoy.parkCar(car);

        assertNull(parkingBoy.pickCar(null));
        assertSame(car, parkingBoy.pickCar(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();

        parkingBoy.pickCar(null);

        assertEquals(
            "Please provide your parking ticket.",
            parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(3));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("666666");

        ParkingTicket ticket = parkingBoy.parkCar(car);
        parkingBoy.pickCar(ticket);

        assertNull(parkingBoy.pickCar(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(3));


        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("12345678");

        ParkingTicket ticket = parkingBoy.parkCar(car);
        parkingBoy.pickCar(ticket);
        parkingBoy.pickCar(ticket);

        assertEquals(
            "Unrecognized parking ticket.",
            parkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(5));
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car ("456789");

        parkingBoy.parkCar(parkingLots, car);

        assertNull(parkingBoy.parkCar(parkingLots, car));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(5));
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car carA = new Car ("456789");
        Car carB = new Car ("123852");
        parkingBoy.parkCar(parkingLots, carA);
        parkingBoy.parkCar(parkingLots, carB);

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }
}
*/

