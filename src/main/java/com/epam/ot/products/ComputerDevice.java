package com.epam.ot.products;

public class ComputerDevice extends ElectricDevice {
    private Double processingPowerHZ;

    public ComputerDevice(long id, String type, String name, int power, Double price) {
        super(id, type, name, power, price);
    }

    public ComputerDevice(long id, String type, String name, int power, Double price, Double processingPowerHZ) {
        super(id, type, name, power, price);
        this.processingPowerHZ = processingPowerHZ;
    }

    public Double getProcessingPower() {
        return processingPowerHZ;
    }

    public void setProcessingPower(Double processingPower) {
        this.processingPowerHZ = processingPower;
    }

    @Override
    public String toString() {
        String result = getType() + ": " + getName() + ", Price: " + getPrice() + ", Power: " + getPower();
        if (processingPowerHZ != null) {
            result += ", CPU: " + processingPowerHZ + " GHz";
        }
        return result;
    }
}
