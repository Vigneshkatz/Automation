package org.smytten.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    public static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);


    private static final long MIN_NUMBER = 1000000000L;
    private static final long MAX_NUMBER = 5999999999L;
    private static long last = MIN_NUMBER - 1;
    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return "katziio" + uuid.substring(0, 8) + "@smytten.com";
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String getNumber() {
        Random random = new Random();
        long firstDigit = random.nextInt(5) + 1;
        long id = (firstDigit * 1000000000L) + (random.nextLong() % 1000000000L);
        if (id <= last) {
            id = last % (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        }
        last = id;
        return String.valueOf(last);
    }
}
