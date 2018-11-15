package com.eddue.jms.core.utils;

import java.util.Random;

/**
 * @author zzx
 * @Description TODO
 * @company www.eddue.com
 * @since 2018/11/14 16:15
 */
public class RandomUtils {
    private static Random random = new Random();

    public static int genRandom(int min, int max) {
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    public static int genRandom(int max) {
        return random.nextInt(max);
    }
}
