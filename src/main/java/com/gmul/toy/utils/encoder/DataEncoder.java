package com.gmul.toy.utils.encoder;

public interface DataEncoder {

    String encode(CharSequence source);

    default String decode(CharSequence source) {
        throw new UnsupportedOperationException();
    }
}
