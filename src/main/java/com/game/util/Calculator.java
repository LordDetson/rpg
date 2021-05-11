package com.game.util;

public class Calculator {

    public static int calculateLevel(int experience) {
        return ((int) Math.sqrt(2500 + 200 * experience) - 50) / 100;
    }

    public static int calculateUntilNextLevel(int level, int experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }
}
