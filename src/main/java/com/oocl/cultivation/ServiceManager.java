package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoys;

    public ServiceManager(ParkingLots parkingLots) {
        super(parkingLots);
        parkingBoys = new ArrayList<>();
    }
    public void addParkingBoy(ParkingBoy parkingBoy){
        parkingBoys.add(parkingBoy);
    }

    public ParkingTicket askToParkCar(ParkingBoy parkingBoy, Car car){
        if(!parkingBoys.contains(parkingBoy)){
            System.out.println("Parking boy does not belongs to me.");
            return null;
        }
        ParkingTicket parkingTicket = parkingBoy.parkCar(parkingLots, car);
        System.out.println(parkingBoy.Message);
        return parkingTicket;
    }
    public Car askToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket){
        Car car = parkingBoy.pickCar(parkingTicket);
        System.out.println(parkingBoy.Message);
        return car;
    }
}
}
