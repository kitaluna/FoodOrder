package kitaluna.foodorder.utils;

import java.util.UUID;

/**
 * Created by Van Hien on 1/2/2018.
 */

public class TokenCreate {
    public static String generateString() {
        String token = UUID.randomUUID().toString();
        token= token.replace("-","_");
        token += System.currentTimeMillis();
        return token;
    }
}
