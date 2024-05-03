package com.example.cal.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
    @Transactional
    public List<CalendarEvent> findByEventDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
