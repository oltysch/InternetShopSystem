package com.epam.ot.products;

/*
 * Basic class for all Electric Devices
 */

import java.util.*;

public abstract class ElectricDevice extends Product {
    private String name;
    private int power;
    private int price;
    private Boolean pluggedIn = false;

    public ElectricDevice(String name, int power, int price) {
        this.name = name;
        this.power = power;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return this.power;
    }

    public int getPrice() {
        return price;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Boolean isPluggedIn() {
        return pluggedIn;
    }

    public void setPluggedIn(Boolean pluggedIn) {
        this.pluggedIn = pluggedIn;
    }

    /*@Override
    public int compareTo(Object obj) {
        ElectricDevice tmp = (ElectricDevice) obj;
        if (this.power < tmp.power) {
            return -1;
        } else if (this.power > tmp.power) {
            return 1;
        }
        return 0;
    }*/

    @Override
    public String toString() {
        String result = name + ", Price: " + price + ", Power: " + power;
        if (this.pluggedIn) {
            result += ", on";
        } else {
            result += ", off";
        }
        return result;
    }

    public static final Comparator<ElectricDevice> COMPARE_BY_POWER = new Comparator<ElectricDevice>() {
        @Override
        public int compare(ElectricDevice device1, ElectricDevice device2) {
            if (device1.power < device2.power) return -1;
            else if (device1.power > device2.power) return 1;
            return 0;
        }
    };

    public static final Comparator<ElectricDevice> COMPARE_BY_PRICE = new Comparator<ElectricDevice>() {
        @Override
        public int compare(ElectricDevice device1, ElectricDevice device2) {
            if (device1.price < device2.price) return -1;
            else if (device1.price > device2.price) return 1;
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
            /*if (device1.price < device2.price) return -1;
            else if (device1.price > device2.price) return 1;
            return 0;*/
        }
    };
}