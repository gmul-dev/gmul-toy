package com.gmul.toy.utils.encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESDataEncoder implements DataEncoder {

    private static final String AES = "AES";

    private final Cipher encryptor;
    private final Cipher decryptor;
    private Boolean urlSafe = false;

    public AESDataEncoder(String key) {
        this.encryptor = createCipher(key, Cipher.ENCRYPT_MODE);
        this.decryptor = createCipher(key, Cipher.DECRYPT_MODE);
    }

    @Override
    public String encode(CharSequence source) {
        try {
            final byte[] sourceBytes = source.toString().getBytes(StandardCharsets.UTF_8);
            final byte[] bytes = encryptor.doFinal(sourceBytes);
            return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String decode(CharSequence source) {
        try {
            final byte[] sourceBytes = source.toString().getBytes(StandardCharsets.UTF_8);
            final byte[] bytes = decryptor.doFinal(Base64.getDecoder().decode(sourceBytes));
            return new String(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalStateException(e);
        }
    }

    private Cipher createCipher(String key, int encryptMode) {
        final byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.US_ASCII), 16);
        final SecretKey secretKey = new SecretKeySpec(keyBytes, AES);
        try {
            final Cipher instance = Cipher.getInstance(AES);
            instance.init(encryptMode, secretKey);
            return instance;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
    }
}
