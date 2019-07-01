package org.jespanol.conference.conference;

import jakarta.nosql.mapping.Repository;

import java.util.List;

public interface ConferenceRepository extends Repository<Conference, String> {

    List<Conference> findAll();
}
