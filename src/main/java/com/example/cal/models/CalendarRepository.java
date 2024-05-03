package com.example.cal.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
