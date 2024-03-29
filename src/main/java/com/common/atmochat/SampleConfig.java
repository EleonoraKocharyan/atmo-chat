//package com.common.atmochat;
//
//import org.atmosphere.annotation.Processor;
//import org.atmosphere.config.AtmosphereAnnotation;
//import org.atmosphere.cpr.AtmosphereFramework;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.annotation.*;
//
///**
// * A Simple {@link Processor} that demonstrate how you can extend you application with custom annotation.
// *
// * @author eanfrancois Arcand
// */
//@Targeåt({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//
//public class SampleConfig implements Processor<Object> {
//
//    private final Logger logger = LoggerFactory.getLogger(SampleConfig.class);
//
//    @Override
//    public void handle(AtmosphereFramework framework, Class<Object> annotatedClass) {
//        logger.info("Custom annotation {} discovered. Starting the Chat Sample", annotatedClass.getAnnotation(Config.class));
//    }
//}