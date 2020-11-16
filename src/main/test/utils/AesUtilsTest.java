package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AesUtilsTest {

    @Test
    void encrypt() {
        String encypt = AesUtils.encrypt("hello,world");
        System.out.println(encypt);
    }

    @Test
    void decrypt() {
        String encypt = AesUtils.encrypt("hello,world");
        System.out.println(AesUtils.decrypt(encypt));
    }
}