//package com.common.atmochat.config;
//
//import org.atmosphere.cpr.AtmosphereRequest;
//import org.atmosphere.cpr.AtmosphereResource;
//import org.atmosphere.cpr.BroadcastFilter;
//import org.atmosphere.cpr.PerRequestBroadcastFilter;
//import org.codehaus.jackson.map.ObjectMapper;
//
//import java.io.IOException;
//
//import static org.atmosphere.cpr.HeaderConfig.X_ATMOSPHERE_TRACKMESSAGESIZE;
//
//public class ResponseSizeFilter implements PerRequestBroadcastFilter {
//
//    private final static ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public BroadcastAction filter(AtmosphereResource r, Object originalMessage, Object message) {
//        AtmosphereRequest request = r.getRequest();
//        if ("true".equalsIgnoreCase(request.getHeader(X_ATMOSPHERE_TRACKMESSAGESIZE))) {
//
//            String msg = null;
//            try {
//                msg = mapper.writeValueAsString(message);
//                r.getResponse().write(msg.length() + "\r\n".length() + "|" );
//            } catch (IOException e) {
//            }
//        }
//        return new BroadcastAction(BroadcastAction.ACTION.CONTINUE, message);
//    }
//
//    @Override
//    public BroadcastAction filter(Object originalMessage, Object message) {
//        return new BroadcastAction(message);
//    }
//
//    @Override
//    public BroadcastAction filter(String broadcasterId, AtmosphereResource r, Object originalMessage, Object message) {
//        return null;
//    }
//
//    @Override
//    public BroadcastAction filter(String broadcasterId, Object originalMessage, Object message) {
//        return null;
//    }
//}