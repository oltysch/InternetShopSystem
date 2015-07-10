package entity.products;

/**
 * Created by Admin on 26.05.2015.
 */
public class KichenDevice extends ElectricDevice {
    private String appointment;

    public KichenDevice(String name, int power, int price, String appointment) {
        super(name, power, price);
        this.appointment = appointment;
    }

    public KichenDevice(String name, int power, int price) {
        super(name, power, price);
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        String result = getName() + ", Price: " + getPrice() + ", Power: " + getPower();
        if (appointment != null) {
            result += ", Appointment: " + appointment;
        }
        if (isPluggedIn()) {
            result += ", on";
        } else {
            result += ", off";
        }
        return result;
    }
}
