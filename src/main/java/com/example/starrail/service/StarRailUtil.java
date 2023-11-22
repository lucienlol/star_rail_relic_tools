package com.example.starrail.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class StarRailUtil {

    public static Integer HEAD_TYPE = 1;

    public static Integer HAND_TYPE = 2;

    public static Integer BODY_TYPE = 3;

    public static Integer FEET_TYPE = 4;

    public static Integer SPHERE_TYPE = 5;

    public static Integer ROPE_TYPE = 6;

    public static Integer CAVERN_RELICS = 1;

    public static Integer PLANAR_ORNAMENTS = 2;

    public static Integer HALF_SET_DEMAND = 1;

    public static Integer ALL_SET_DEMAND = 2;

    public static Double formatPrecision(Double num) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.valueOf(df.format(num));
    }

}
