package com.oocl.cultivation;

public class ParkingBoy {

    public ParkingTicket parkCar(ParkingLots parkingLots, Car car) {
        int emptyLot = -1;
        ParkingLot parkingLot = null;
        for (ParkingLot current : parkingLots.getParkingLots()) {
            parkingLot = current;
            emptyLot = current.getEmptyLot();
            if(emptyLot != -1) {
                break;
            }
        }

        ParkingTicket lotAddress =  new ParkingTicket(parkingLot, emptyLot);

        if(emptyLot != -1) {
            parkingLot.parkCar(car, lotAddress);
        }

        return lotAddress;
    }

    public Car pickCar(ParkingTicket lotAddress) {
        ParkingLot parkingLot = lotAddress.getParkingLot();
        return parkingLot.pickCar(lotAddress.getLotNumber());
    }

}

