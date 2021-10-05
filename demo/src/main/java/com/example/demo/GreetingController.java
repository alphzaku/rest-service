package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();

	// Define a GreetingComponent object without initializing it
	private GreetingComponent g = new GreetingComponent();
	
	// Inject the object in the constructor
	@Autowired
	public GreetingController(GreetingComponent g) {
		this.g = g;
	}
	
	@GetMapping("/greeting")
	public Greeting greetings(@RequestParam(value = "name", defaultValue = "World")String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/greeting/{firstname}")
	public Greeting greetingsName(@PathVariable("firstname") String firstname) {
		String fullname = firstname;
		return new Greeting(counter.incrementAndGet(), String.format(template, fullname));
	}
	
	@GetMapping("/greeting/{firstname}/{lastname}")
	public Greeting greetingsName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
		String fullname = firstname + " " + lastname;
		return new Greeting(counter.incrementAndGet(), String.format(template, fullname));
	}

	// <> = Generic
	// Test GreetingComponent
	@GetMapping("/textgreeting")
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.ok(g.getMessage());
	}
}
