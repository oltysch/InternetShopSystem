package com.epam.ot.products;

import java.util.*;

public abstract class ElectricDevice extends Product {
    public static final Comparator<ElectricDevice> COMPARE_BY_PRICE = new Comparator<ElectricDevice>() {
        @Override
        public int compare(ElectricDevice device1, ElectricDevice device2) {
            if (device1.getPrice() < device2.getPrice()) return -1;
            else if (device1.getPrice() > device2.getPrice()) return 1;
            return 0;
        }
    };
    public static final Comparator<ElectricDevice> COMPARE_BY_NAME = new Comparator<ElectricDevice>() {
        @Override
        public int compare(ElectricDevice device1, ElectricDevice device2) {
            int len1 = device1.getName().length();
            int len2 = device2.getName().length();
            int lim = Math.min(len1, len2);
            char v1[] = device1.getName().toCharArray();
            char v2[] = device2.getName().toCharArray();

            int k = 0;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c1 - c2;
                }
                k++;
            }
            return len1 - len2;
        }
    };
    private String type;
    private int power;
    public static final Comparator<ElectricDevice> COMPARE_BY_POWER = new Comparator<ElectricDevice>() {
        @Override
        public int compare(ElectricDevice device1, ElectricDevice device2) {
            if (device1.power < device2.power) return -1;
            else if (device1.power > device2.power) return 1;
            return 0;
        }
    };

    public ElectricDevice(long id, String type, String name, int power, Double price) {
        super(id, name, price);
        this.type = type;
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String result = type + ": " + getName() + ", Price: " + getClass() + ", Power: " + power;
        return result;
    }
}