package com.example.cal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
	private List<CalendarEvent> events;
}
