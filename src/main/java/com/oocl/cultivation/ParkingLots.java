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

    public ParkingLot getMostAvailableParkLot(){
        int emptySpace = Integer.MIN_VALUE;
        ParkingLot result = null;
        for (ParkingLot parkingLot : parkingLots) {
            if(emptySpace < parkingLot.getEmptyLotNumber()) {
                emptySpace = parkingLot.getEmptyLotNumber();
                result = parkingLot;
            }
        }

        return result;
    }
    @Override
    public String toString() {
        return "ParkingLots{" +
                "parkingLots=" + parkingLots +
                '}';
    }

}
