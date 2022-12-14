package com.banking.banking.utils;

import java.util.HashSet;
import java.util.Set;

public class UniqueIdGenerator {
    private static final long twepoch = 1288834974657L;
    private static final long sequenceBits = 17;
    private static final long sequenceMax = 65536;
    private static volatile long lastTimestamp = -1L;
    private static volatile long sequence = 0L;

    public static void main(String[] args) {
        Set<Long> uniqueIds = new HashSet<>();
        long now = System.currentTimeMillis();
        for(int i=0; i < 1000; i++)
        {
            uniqueIds.add(generateLongId());
        }
        System.out.println("Number of Unique IDs generated: " + uniqueIds.size() + " in " + (System.currentTimeMillis() - now) + " milliseconds");
    }

    public static synchronized Long generateLongId() {
        long timestamp = System.currentTimeMillis();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        Long id = ((timestamp - twepoch) << sequenceBits) | sequence;
        return id;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}