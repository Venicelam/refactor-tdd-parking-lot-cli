package com.oocl.cultivation;
import java.util.List;

public class ParkingLots {
    private List<ParkingLot> parkingLots;

    public ParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
