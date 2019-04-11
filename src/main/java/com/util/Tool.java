package com.util;

import java.text.DecimalFormat;

public class Tool {
	public static String getDoubleString(double number) {
        String numberStr;
        if (((int) number * 1000) == (int) (number * 1000)) {
            //如果是一个整数
            numberStr = String.valueOf((int) number);
        } else {
            DecimalFormat df = new DecimalFormat("0");
            numberStr = df.format(number);
        }
        return numberStr;
    }
}

