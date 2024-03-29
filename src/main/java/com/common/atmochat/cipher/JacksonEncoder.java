package com.common.atmochat.cipher;

import com.common.atmochat.dto.ChatProtocol;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Encoder;

//import javax.inject.Inject;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Encode a {@link ChatProtocol} into a String
 */
public class JacksonEncoder implements Encoder<JacksonEncoder.Encodable, String> {

    @Inject
    private ObjectMapper mapper;

    @Override
    public String encode(Encodable m) {
        try {
            return mapper.writeValueAsString(m);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marker interface for Jackson.
     */
    public static interface Encodable {
    }
}