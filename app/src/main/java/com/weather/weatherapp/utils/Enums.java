package com.weather.weatherapp.utils;

import java.util.ArrayList;

public class Enums {
    public enum Units {
        TEMPERATURE(Constants.TEMPERATURE),
        WIND(Constants.WIND),
        PRESSURE(Constants.PRESSURE),
        PRECIPITATION(Constants.PRECIPITATION),
        VISIBILITY(Constants.VISIBILITY),
        TIME(Constants.TIME),
        DATE(Constants.DATE);

        public final String key;

        Units(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Units fromKey(String key) {
            for (Units units : values()) {
                if (units.getKey().equalsIgnoreCase(key)) {
                    return units;
                }
            }
            return TEMPERATURE;
        }
    }

    public enum TemperatureUnit {
        FAHRENHEIT(Constants.FAHRENHEIT, "F°"),
        CELSIUS(Constants.CELSIUS, "C°");
        private final String key, value;

        TemperatureUnit(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static TemperatureUnit fromKey(String key) {
            for (TemperatureUnit unit : values()) {
                if (unit.getKey().equalsIgnoreCase(key)) {
                    return unit;
                }
            }
            return FAHRENHEIT;
        }

        public static TemperatureUnit fromValue(String value) {
            for (TemperatureUnit unit : values()) {
                if (unit.getValue().equalsIgnoreCase(value)) {
                    return unit;
                }
            }
            return FAHRENHEIT;
        }

        public static ArrayList<String> getALlTempUnits() {
            ArrayList<String> list = new ArrayList<>();
            for (TemperatureUnit unit : values()) {
                list.add(unit.getValue());
            }
            return list;
        }

    }

    public enum WindUnit {
        KILOMETER_PER_HR(Constants.KILOMETER_PER_HR, "km/h"),
        MILE_PER_HR(Constants.MILE_PER_HR, "mph"),
        METE_PER_SEC(Constants.METE_PER_SEC, "m/s"),
        KNOTS(Constants.KNOTS, "kt"),
        BEAUFORT_SCALE(Constants.BEAUFORT_SCALE, "Bft");

        private final String key, value;

        WindUnit(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static WindUnit fromKey(String key) {
            for (WindUnit unit : values()) {
                if (unit.getKey().equalsIgnoreCase(key)) {
                    return unit;
                }
            }
            return KILOMETER_PER_HR;
        }

        public static WindUnit fromValue(String value) {
            for (WindUnit unit : values()) {
                if (unit.getValue().equalsIgnoreCase(value)) {
                    return unit;
                }
            }
            return KILOMETER_PER_HR;
        }

        public static ArrayList<String> getALlWindUnits() {
            ArrayList<String> list = new ArrayList<>();
            for (WindUnit unit : values()) {
                list.add(unit.getValue());
            }
            return list;
        }

    }

    public enum PressureUnit {
        MILLIBAR(Constants.MILLIBAR, "mbar"),
        BAR(Constants.BAR, "bar"),
        POUND_PER_SQ_IN(Constants.POUND_PER_SQ_IN, "psi"),
        IN_OF_MERCURY(Constants.IN_OF_MERCURY, "inHg"),
        MILLIMETER_OF_MERCURY(Constants.MILLIMETER_OF_MERCURY, "mmHg"),
        HECTOPASCAL(Constants.HECTOPASCAL, "hPa");

        private final String key, value;

        PressureUnit(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static PressureUnit fromKey(String key) {
            for (PressureUnit unit : values()) {
                if (unit.getKey().equalsIgnoreCase(key)) {
                    return unit;
                }
            }
            return MILLIBAR;
        }

        public static PressureUnit fromValue(String value) {
            for (PressureUnit unit : values()) {
                if (unit.getValue().equalsIgnoreCase(value)) {
                    return unit;
                }
            }
            return MILLIBAR;
        }

        public static ArrayList<String> getALlPressureUnits() {
            ArrayList<String> list = new ArrayList<>();
            for (PressureUnit unit : values()) {
                list.add(unit.getValue());
            }
            return list;
        }

    }

    public enum PrecipitationUnit {
        CENTIMETERS(Constants.CENTIMETERS, "cm"),
        MILLIMETERS(Constants.MILLIMETERS, "mm"),
        INCHES(Constants.INCHES, "in");

        private final String key, value;

        PrecipitationUnit(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static PrecipitationUnit fromKey(String key) {
            for (PrecipitationUnit unit : values()) {
                if (unit.getKey().equalsIgnoreCase(key)) {
                    return unit;
                }
            }
            return CENTIMETERS;
        }

        public static PrecipitationUnit fromValue(String value) {
            for (PrecipitationUnit unit : values()) {
                if (unit.getValue().equalsIgnoreCase(value)) {
                    return unit;
                }
            }
            return CENTIMETERS;
        }

        public static ArrayList<String> getALlPrecipitationUnits() {
            ArrayList<String> list = new ArrayList<>();
            for (PrecipitationUnit unit : values()) {
                list.add(unit.getValue());
            }
            return list;
        }

    }

    public enum VisibilityUnit {
        KM(Constants.KM, "Km"),
        MILE(Constants.MILE, "Mile"),
        M(Constants.M, "M");

        private final String level;
        private final String value;

        VisibilityUnit(String level, String value) {
            this.level = level;
            this.value = value;
        }

        public String getKey() {
            return level;
        }

        public String getValue() {
            return value;
        }

        public static VisibilityUnit fromString(String text) {
            for (VisibilityUnit visibilityUnit : values()) {
                if (visibilityUnit.getKey().equalsIgnoreCase(text)) {
                    return visibilityUnit;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return KM;
        }

        public static VisibilityUnit fromInt(String val) {
            for (VisibilityUnit visibilityUnit : values()) {
                if (visibilityUnit.getValue().equalsIgnoreCase(val)) {
                    return visibilityUnit;
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

    public enum TimeFormat {
        TWELVE_HRS(Constants.TWELVE_HRS, "12 hours"),
        TWENTY_FOUR_HRS(Constants.TWENTY_FOUR_HRS, "24 hours");

        private final String level;
        private final String value;

        TimeFormat(String level, String value) {
            this.level = level;
            this.value = value;
        }

        public String getKey() {
            return level;
        }

        public String getValue() {
            return value;
        }

        public static TimeFormat fromString(String text) {
            for (TimeFormat timeFormat : values()) {
                if (timeFormat.getKey().equalsIgnoreCase(text)) {
                    return timeFormat;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return TWELVE_HRS;
        }

        public static TimeFormat fromInt(String val) {
            for (TimeFormat timeFormat : values()) {
                if (timeFormat.getValue().equalsIgnoreCase(val)) {
                    return timeFormat;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return TWELVE_HRS;
        }

        public static ArrayList<String> getAllTimeFormats() {
            ArrayList<String> list = new ArrayList<>();
            for (TimeFormat timeFormat : values()) {
                list.add(timeFormat.getKey());
            }
            return list;
        }
    }

    public enum DateFormat {
        DAY_MONTH_YEAR(Constants.DAY_MONTH_YEAR, "dd/mm/yyyy"),
        MONTH_DAY_YEAR(Constants.MONTH_DAY_YEAR, "mm/dd/yyyy"),
        YEAR_MONTH_DAY(Constants.YEAR_MONTH_DAY, "yyyy/mm/dd");

        private final String level;
        private final String value;

        DateFormat(String level, String value) {
            this.level = level;
            this.value = value;
        }

        public String getKey() {
            return level;
        }

        public String getValue() {
            return value;
        }

        public static DateFormat fromString(String text) {
            for (DateFormat dateFormat : values()) {
                if (dateFormat.getKey().equalsIgnoreCase(text)) {
                    return dateFormat;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return DAY_MONTH_YEAR;
        }

        public static DateFormat fromInt(String val) {
            for (DateFormat dateFormat : values()) {
                if (dateFormat.getValue().equalsIgnoreCase(val)) {
                    return dateFormat;
                }
            }
            // Default to a fallback level if the input does not match any known level.
            return DAY_MONTH_YEAR;
        }

        public static ArrayList<String> getAllDateFormats() {
            ArrayList<String> list = new ArrayList<>();
            for (DateFormat dateFormat : values()) {
                list.add(dateFormat.getKey());
            }
            return list;
        }
    }

}
