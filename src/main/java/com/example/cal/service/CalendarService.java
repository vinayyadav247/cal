package com.example.cal.service;

import com.example.cal.models.Calendar;
import com.example.cal.models.CalendarEvent;
import com.example.cal.models.CalendarEventRepository;
import com.example.cal.models.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
	
	@Autowired
	private CalendarRepository calendarRepository;
	@Autowired
	private CalendarEventRepository calendarEventRepository;

	public Calendar retrieveCalendar(long id) {
		Optional<Calendar> calendar = calendarRepository.findById(id);

		if (!calendar.isPresent())
		{
			return null;
		}
		return calendar.get();
	}

	public List<Calendar> retrieveAllCalendars() {
		return calendarRepository.findAll();
	}
	
	public Calendar addCalendar(Calendar calendar)
	{
		return calendarRepository.saveAndFlush(calendar);
	}
	
	public Calendar updateCalendar(Calendar calendar) {
		Calendar searchedCalendar = retrieveCalendar(calendar.getId());
		if(searchedCalendar != null)
		{
			searchedCalendar.setName(calendar.getName());
			searchedCalendar.setUser(calendar.getUser());
			return calendarRepository.saveAndFlush(calendar);
		}
		else return null;			
	}
	
	public void deleteCalendar(Calendar calendar)
	{
		calendarRepository.delete(calendar);
	}
	
	public List<CalendarEvent> findByEventDateTimeBetween(LocalDateTime start, LocalDateTime end)
	{
		return calendarEventRepository.findByEventDateTimeBetween(start, end);
	}
}
