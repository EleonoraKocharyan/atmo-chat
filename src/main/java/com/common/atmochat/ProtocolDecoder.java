package com.common.atmochat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.inject.Inject;
import java.io.IOException;

/**
 * Decode a String into a {@link ChatProtocol}.
 */
public class ProtocolDecoder implements Decoder<String, ChatProtocol> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public ChatProtocol decode(String s) {
        try {
            return mapper.readValue(s, ChatProtocol.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}