package com.oocl.cultivation;

public class ParkingBoy {
    public String Message;

    public ParkingTicket parkCar(ParkingLots parkingLots, Car car) {
        int emptyLot = -1;
        ParkingLot parkingLot = null;
        for (ParkingLot current : parkingLots.getParkingLots()) {
            parkingLot = current;
            emptyLot = current.getEmptyLot();
            if(emptyLot != -1) {
                Message = "Not enough position.";
                return null;
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
        if (parkingLot == null){
            Message = "Unrecognized parking ticket.";
        }
        if (lotAddress == null){
            Message = "Please provide your parking ticket.";
            return null;
        }
        return parkingLot.pickCar(lotAddress.getLotNumber());
    }

}

