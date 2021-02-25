package one.tika.tide.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.TreeMap;

public class NumUtil {

    private final static TreeMap<Integer, String> map = CollectionUtil.treeMap(
        1000, "M",
        900, "CM",
        500, "D",
        400, "CD",
        100, "C",
        90, "XC",
        50, "L",
        40, "XL",
        10, "X",
        9, "IX",
        5, "V",
        4, "IV",
        1, "I"
    );

    public static String toRoman(int number) {
        int l = map.floorKey(number);
        return number == l ? map.get(number) : map.get(l) + toRoman(number-l);
    }

    public static String prettify(double d) {
        return NumberFormat.getNumberInstance().format(d);
    }

    public static String simplify(double d) {
        NumberFormat form = NumberFormat.getInstance(Locale.ENGLISH);

        form.setMaximumFractionDigits(2);

        form.setMinimumFractionDigits(0);
        if (d < 1000.0D) {
            return form.format(d);
        }
        if (d < 1000000.0D) {
            return form.format(d / 1000.0D) + "k";
        }
        if (d < 1.0E9D) {
            return form.format(d / 1000000.0D) + "M";
        }
        if (d < 1.0E12D) {
            return form.format(d / 1.0E9D) + "B";
        }
        if (d < 1.0E15D) {
            return form.format(d / 1.0E12D) + "T";
        }
        if (d < 1.0E18D) {
            return form.format(d / 1.0E15D) + "Q";
        }
        if (d < 1.0E21D) {
            return form.format(d / 1.0E18D) + "aa";
        }
        if (d < 1.0E24D) {
            return form.format(d / 1.0E21D) + "ab";
        }
        if (d < 1.0E27D) {
            return form.format(d / 1.0E24D) + "ac";
        }
        if (d < 1.0E30D) {
            return form.format(d / 1.0E27D) + "ad";
        }
        if (d < 1.0E33D) {
            return form.format(d / 1.0E30D) + "ae";
        }

        long l = (long) d;
        return String.valueOf(l);
    }

}
