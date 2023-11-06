package com.burcu.bankingAPI.util;

import java.util.UUID;

public class UUIDGeneratorUtil {
    public static Long generateUUID(){
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
