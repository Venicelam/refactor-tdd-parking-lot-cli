package com.oocl.cultivation;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingLot {

    private int total;
    private List<Car> lots;

    public int getTotal() {
        return total;
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "total=" + total +
                ", lots=" + lots +
                '}';
    }

    public ParkingLot(int total) {
        this.total = total;
        this.lots = new ArrayList<>(Collections.nCopies(total, null));
    }



    public int getEmptyLot(){
        for (int i = 0; i < total; i++) {
            if(lots.get(i) == null) {
                return i;
            }
        }

        return -1;
    }

    public int getEmptyLotNumber(){
        int emptyNum = 0;
        for (int i = 0; i < total; i++) {
            if(lots.get(i) == null) {
                emptyNum++;
            }
        }
        return emptyNum;
    }

    public void parkCar(Car car, ParkingTicket address) {
        lots.set(address.getLotNumber(), car);
        car.setLotAddress(address);
    }

    public Car pickCar(int lotNumber) {
        Car car = lots.get(lotNumber);
        if(car != null) {
            lots.set(lotNumber, null);
            car.setLotAddress(null);
        }

        return car;
    }
}
