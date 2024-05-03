package com.example.cal.resources;


import com.example.cal.errors.CalendarNotFoundException;
import com.example.cal.errors.InvalidRequestException;
import com.example.cal.models.Calendar;
import com.example.cal.models.CalendarEvent;
import com.example.cal.request.SearchRequest;
import com.example.cal.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CalendarResource {

	@Autowired
	private CalendarService calendarService;

	@GetMapping("/")
	public String sayHello() {
		return "Welcome to the Calendar REST API!";
	}

	@GetMapping("/calendars/{id}")
	public Calendar retrieveCalendar(@PathVariable long id) throws CalendarNotFoundException
	{
		Calendar calendar = calendarService.retrieveCalendar(id);
		if(calendar == null)
		{
			throw new CalendarNotFoundException("id->" + id);
		}
		else
		{
			return calendar;
		}
	}

	@GetMapping("/calendars")
	public List<Calendar> retrieveAllCalendars() {
		return calendarService.retrieveAllCalendars();
	}

	@PostMapping(value = "/calendars/add")
	public Calendar addCalendar(@RequestBody Calendar calendar) throws InvalidRequestException
	{
		if(calendar.getUser() != null)
		{
			return calendarService.addCalendar(calendar);			
		}
		else
		{
			throw new InvalidRequestException("Provide user details to create a calendar.");
		}
	}
	
	@PutMapping(value = "/calendars/update")
	public Calendar updateCalendar(@RequestBody Calendar calendar) throws InvalidRequestException
	{
		Calendar updated = calendarService.updateCalendar(calendar);
		if(updated != null)
		{
			return updated;
		}
		else
		{
			throw new CalendarNotFoundException("id->" + calendar.getId());
		}
	}
	
	@DeleteMapping(value = "/calendars/delete")
	public void deleteCalendar(@RequestBody Calendar calendar) {
		calendarService.deleteCalendar(calendar);
	}
	
	@PostMapping("/calendars/search")
	public List<CalendarEvent> searchEvents(@RequestBody SearchRequest searchRequest) throws InvalidRequestException
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if(searchRequest != null)
		{
			String startDateTime = searchRequest.getStartDateTime();
			String endDateTime = searchRequest.getEndDateTime();
			
			if(startDateTime == null && endDateTime == null)
			{
				throw new InvalidRequestException("Must provide a start and/or end date to retrieve calendar events.");
			}
			else if(startDateTime != null && endDateTime != null)
			{
				LocalDateTime start = LocalDateTime.parse(startDateTime, formatter);
				LocalDateTime end = LocalDateTime.parse(endDateTime, formatter);
				List<CalendarEvent> calendarEvents = calendarService.findByEventDateTimeBetween(start, end);
				return calendarEvents;
			}
			else
			{
				throw new InvalidRequestException("Must provide a start and/or end date to retrieve calendar events.");
			}
		}
		else
		{
			throw new InvalidRequestException("Search parameters startDateTime and endDateTime must be provided.");
		}
	}
}

//{ "name": "Calendar345", "user": { "id": 1, "name": "Uk1" }, "events": [ { "title": "event1", "eventDateTime": "2018-07-01 00:00:00", "location": "event location 1", "attendees": null, "reminderDateTime": null, "reminderSent": true }, { "title": "event2", "eventDateTime": "2018-07-04 00:00:00", "location": "event location 2", "attendees": null, "reminderDateTime": null, "reminderSent": false }, { "title": "event3", "eventDateTime": "2018-07-20 00:00:00", "location": "event location 3", "attendees": null, "reminderDateTime": null, "reminderSent": false } ] }