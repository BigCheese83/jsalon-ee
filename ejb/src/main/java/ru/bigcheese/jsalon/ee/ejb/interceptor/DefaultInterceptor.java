package ru.bigcheese.jsalon.ee.ejb.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by BigCheese on 18.03.2016.
 */
public class DefaultInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultInterceptor.class);

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = context.proceed();
            return result;
        } finally {
            long end = System.currentTimeMillis() - start;
            String resultMsg = getResultMessage(result);
            LOG.info("call {}.{} ended {} ms {}",
                    context.getTarget().getClass().getName(),
                    context.getMethod().getName(),
                    end, resultMsg);
        }
    }

    private String getResultMessage(Object object) {
        if (object instanceof CrudEntityResult) {
            CrudEntityResult result = (CrudEntityResult)object;
            return "with result code=" + result.getCode() + " message= " + result.getMessage();
        }
        return "";
    }
}
