package com.oocl.cultivation;

public class ParkingTicket {
    private ParkingLot parkingLot;
    private int lotNumber;

    public ParkingTicket(ParkingLot parkingLot, int lotNumber) {
        this.parkingLot = parkingLot;
        this.lotNumber = lotNumber;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }



    public int getLotNumber() {
        return lotNumber;
    }

 
    @Override
    public String toString() {
        return "LotAddress{" +
                "parkingLot=" + parkingLot +
                ", lotNumber=" + lotNumber +
                '}';
    }

}

