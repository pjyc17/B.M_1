package org.kurento.tutorial.groupcall.repository;

import org.kurento.tutorial.groupcall.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Spring Data JPA 사용
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}