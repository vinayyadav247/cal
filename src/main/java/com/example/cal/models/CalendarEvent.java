package com.example.cal.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CalendarEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime eventDateTime;

	private String location;

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> attendees;

	private LocalDateTime reminderDateTime;

	private boolean reminderSent;
}
