package org.jespanol.speaker.infra;


import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Transactional
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionInterceptor {

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object manageTransaction(InvocationContext context) throws Exception {
        final EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            Object result = context.proceed();
            transaction.commit();
            return result;
        } catch (Exception exp) {
            transaction.rollback();
            throw exp;
        }
    }
}
