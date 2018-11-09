package com.common.atmochat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Encoder;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.inject.Inject;
import java.io.IOException;

/**
 * Encode a {@link ChatProtocol} into a String
 */
public class JacksonEncoder implements Encoder<JacksonEncoder.Encodable, String> {

    @Autowired
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