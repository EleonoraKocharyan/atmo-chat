package com.common.atmochat.cipher;

import com.common.atmochat.dto.ChatProtocol;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;

//import javax.inject.Inject;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Decode a String into a {@link ChatProtocol}.
 */
public class ProtocolDecoder implements Decoder<String, ChatProtocol> {

    @Inject
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