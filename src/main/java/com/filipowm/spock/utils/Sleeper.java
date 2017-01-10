package com.filipowm.spock.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Mateusz Filipowicz
 */
@Slf4j
public class Sleeper {

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(int min, int max) {
        Random random = new Random();
        int millis = random.nextInt(max - min) + min;
        sleep(millis);
    }
}
