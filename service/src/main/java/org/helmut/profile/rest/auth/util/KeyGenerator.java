package org.helmut.profile.rest.auth.util;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class KeyGenerator {

    public Key generateKey() {
        String keyString = "simplekey";
        return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
    }
}
