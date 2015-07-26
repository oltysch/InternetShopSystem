package com.epam.ot.products;

public class EntertainmentDevice extends ElectricDevice {
    private String screenResolution;

    public EntertainmentDevice(long id, String type, String name, int power, Double price) {
        super(id, type, name, power, price);
    }

    public EntertainmentDevice(long id, String type, String name, int power, Double price, String resolution) {
        super(id, type, name, power, price);
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
        String result = getType() + ": " + getName() + ", Price: " + getPrice() + ", Power: " + getPower();
        if (screenResolution != null) {
            result += ", Resolution: " + screenResolution;
        }
        return result;
    }
}
