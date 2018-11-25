package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public ParkingTicket parkCar(ParkingLots parkingLots, Car car) {
        ParkingLot parkingLot = parkingLots.getMostAvailableParkLot();
        int emptyLot = parkingLot.getEmptyLot();

        ParkingTicket lotAddress =  new ParkingTicket(parkingLot, emptyLot);

        if(emptyLot != -1) {
            parkingLot.parkCar(car, lotAddress);
        }

        return lotAddress;
    }
}
