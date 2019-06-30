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
    public Conference insert(Conference conference) {
        entityManager.persist(conference);
        return conference;
    }

    @Transactional
    public void update(Conference conference) {
        entityManager.persist(conference);
    }

    @Transactional
    public void delete(Long id) {
        find(id).ifPresent(c -> entityManager.remove(c));
    }


    public Optional<Conference> find(Long id) {
        return Optional.ofNullable(entityManager.find(Conference.class, id));
    }

    public List<Conference> findAll() {
        String query = "select e from Conference e";
        return entityManager.createQuery(query).getResultList();
    }


}
