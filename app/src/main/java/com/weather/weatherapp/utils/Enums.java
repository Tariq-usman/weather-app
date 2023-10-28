package com.weather.weatherapp.utils;

import java.util.ArrayList;

public class Enums {

    public enum VisibilityUnit {
        KM(Constants.KM, 0),
        MILE(Constants.MILE, 1),
        M(Constants.M, 2);

        private final String level;
        private final int value;

        VisibilityUnit(String level, int value) {
            this.level = level;
            this.value = value;
        }

        public String getKey() {
            return level;
        }

        public int getValue() {
            return value;
        }

        public static VisibilityUnit fromString(String text) {
            for (VisibilityUnit level : values()) {
                if (level.getKey().equalsIgnoreCase(text)) {
                    return level;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return KM;
        }

        public static VisibilityUnit fromInt(int val) {
            for (VisibilityUnit level : values()) {
                if (level.getValue() == val) {
                    return level;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return KM;
        }

        public static ArrayList<String> getAllVisibilityUnits() {
            ArrayList<String> list = new ArrayList<>();
            for (VisibilityUnit visibilityUnit : values()) {
                list.add(visibilityUnit.getKey());
            }
            return list;
        }
    }

}
