package com.common.atmochat.cipher;

import com.common.atmochat.dto.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;

import java.io.IOException;

/**
 * Decode a String into a {@link UserMessage}.
 */
public class UserDecoder implements Decoder<String, UserMessage> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserMessage decode(String s) {
        try {
            return mapper.readValue(s, UserMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}