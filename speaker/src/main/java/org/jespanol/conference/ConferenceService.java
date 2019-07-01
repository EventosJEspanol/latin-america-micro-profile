package org.jespanol.conference;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ConferenceService {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public Speaker insert(Speaker speaker) {
        entityManager.persist(speaker);
        return speaker;
    }

    @Transactional
    public void update(Speaker speaker) {
        entityManager.persist(speaker);
    }

    @Transactional
    public void delete(Integer id) {
        find(id).ifPresent(c -> entityManager.remove(c));
    }


    public Optional<Speaker> find(Integer id) {
        return Optional.ofNullable(entityManager.find(Speaker.class, id));
    }

    public List<Speaker> findAll() {
        String query = "select e from Speaker e";
        return entityManager.createQuery(query).getResultList();
    }


}
