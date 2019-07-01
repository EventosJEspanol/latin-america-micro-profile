package org.jespanol.conference.infra;

import sh.platform.config.Config;
import sh.platform.config.JPA;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
class EntityManagerConfiguration {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    void setUp() {
        Config config = new Config();
        final JPA credential = config.getCredential("postgresql", JPA::new);
        entityManagerFactory = credential.getPostgreSQL("jpa-example");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Produces
    @ApplicationScoped
    EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Produces
    @ApplicationScoped
    EntityManager getEntityManager() {
        return entityManager;
    }

    void close(@Disposes EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }

    void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }

}
