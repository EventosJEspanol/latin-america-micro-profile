package org.jespanol.conference.infra;

import org.jespanol.conference.Transactional;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.logging.Logger;

@Transactional
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionInterceptor {

    private static final Logger LOGGER = Logger.getLogger(TransactionInterceptor.class.getName());

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object manageTransaction(InvocationContext context) throws Exception {
        final EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        LOGGER.info("Starting transaction");
        try {
            Object result = context.proceed();
            transaction.commit();
            LOGGER.info("Committing transaction");
            return result;
        } catch (Exception exp) {
            transaction.rollback();
            throw exp;
        }
    }
}
