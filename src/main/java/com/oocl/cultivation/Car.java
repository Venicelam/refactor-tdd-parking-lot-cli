package com.oocl.cultivation;

public class Car {
    private String licenseNumber;
    private ParkingTicket lotAddress = null;

    public Car(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public ParkingTicket getLotAddress() {
        return lotAddress;
    }

    public void setLotAddress(ParkingTicket lotAddress) {
        this.lotAddress = lotAddress;
    }
}

