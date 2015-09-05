package com.epam.ot.entity;

public class TestUtils {
    public static String generateString(int charsCount) {
        String dict = "qwertyuiopasdfghjklzxcvbnm1234567890 ";
        StringBuilder result = new StringBuilder();

        if (!(charsCount > 0)) {
            charsCount = 1;
        }
        for (int i = 0; i < charsCount; i++) {
            result.append(dict.charAt((int) Math.round(Math.random() * (dict.length() - 1))));
        }

        return result.toString();
    }

    public static void showBlock(ProductBlock block) {
        System.out.println(block.getName());
        System.out.println(block.getPrice());
        for (Characteristic characteristic : block.getCharacteristics()) {
            System.out.println(characteristic.getName() + ": " + characteristic.getValue());
        }
        System.out.println(block.getShortDescription());
        System.out.println(block.getFullDescription());
    }
}
