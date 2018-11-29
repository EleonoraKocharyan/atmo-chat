package com.common.atmochat.config;

import org.atmosphere.config.service.AtmosphereInterceptorService;
import org.atmosphere.cpr.Action;
import org.atmosphere.cpr.AtmosphereConfig;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.interceptor.InvokationOrder;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

@AtmosphereInterceptorService
public class RealTimeHandshakeInterceptor implements AtmosphereInterceptor, InvokationOrder {


    /*
     * Logger used in this resource.
     */
//    private static final Logger LOGGER = LoggerFactory.getLogger(RealTimeHandshakeInterceptor.class);


    @Override
    public void configure(AtmosphereConfig arg0) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public Action inspect(AtmosphereResource arg0) {


//        LOGGER.info("I am in inspect of RealTimeHandshakeInterceptor.");
        return Action.CONTINUE;
    }


    @Override
    public void postInspect(AtmosphereResource arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void destroy() {

    }


    @Override
    public PRIORITY priority() {


        return PRIORITY.FIRST_BEFORE_DEFAULT;
    }


}