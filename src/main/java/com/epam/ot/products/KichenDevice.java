package com.epam.ot.products;

public class KichenDevice extends ElectricDevice {
    private String appointment;

    public KichenDevice(long id, String type, String name, int power, Double price, String appointment) {
        super(id, type, name, power, price);
        this.appointment = appointment;
    }

    public KichenDevice(long id, String type, String name, int power, Double price) {
        super(id, type, name, power, price);
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        String result = getType() + ": " + getName() + ", Price: " + getPrice() + ", Power: " + getPower();
        if (appointment != null) {
            result += ", Appointment: " + appointment;
        }
        return result;
    }
}
