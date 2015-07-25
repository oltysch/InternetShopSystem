package com.epam.ot.products;

public class EntertainmentDevice extends ElectricDevice {
    private String screenResolution;

    public EntertainmentDevice(String name, int power, int price) {
        super(name, power, price);
    }

    public EntertainmentDevice(String name, int power, int price, String resolution) {
        super(name, power, price);
        this.screenResolution = resolution;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    @Override
    public String toString() {
        String result = getName() + ", Price: " + getPrice() + ", Power: " + getPower();
        if (screenResolution != null) {
            result += ", Resolution: " + screenResolution;
        }
        if (this.isPluggedIn()) {
            result += ", on";
        } else {
            result += ", off";
        }
        return result;
    }
}
