package com.gmul.toy.config;

import com.gmul.toy.utils.encoder.AESDataEncoder;
import com.gmul.toy.utils.encoder.DataEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataEncoderConfig {
    private static final String AES_DATA_KEY = "1v1b11e11t11e1d1";

    @Bean
    @Primary
    public DataEncoder usernameEncoder() { return new AESDataEncoder(AES_DATA_KEY); }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(6) {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            @Override
            public String encode(CharSequence rawPassword) {
                return "{bcrypt}" + super.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return passwordEncoder.matches(rawPassword, encodedPassword);
            }
        };
    }

    @Bean
    public PasswordEncoder plainPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword);
            }
        };
    }
}
