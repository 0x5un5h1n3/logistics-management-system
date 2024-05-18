package com.logistics.ejb.interceptor;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        String methodName = ic.getMethod().getName();
        logger.info("Entering method: {}", methodName);

        try {
            return ic.proceed();
        } catch (Exception e) {
            logger.error("Error during method execution: {}", methodName, e);
            throw e;
        } finally {
            logger.info("Exiting method: {}", methodName);
        }
    }
}
