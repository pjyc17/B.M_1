package org.kurento.tutorial.groupcall.repository;

import org.kurento.tutorial.groupcall.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}