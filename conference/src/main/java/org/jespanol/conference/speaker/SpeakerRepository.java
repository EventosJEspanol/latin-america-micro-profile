package org.jespanol.conference.speaker;

import jakarta.nosql.mapping.Repository;

import java.util.List;

public interface SpeakerRepository extends Repository<Speaker, String> {

    List<Speaker> findAll();
}
