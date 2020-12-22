package com.eventapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventapp.models.Event;
import com.eventapp.repository.EventRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository er;
	
	@RequestMapping(value="/register-event", method=RequestMethod.GET)
	public String form() {
		return "event/formEvent";
	}
	
	@RequestMapping(value="/register-event", method=RequestMethod.POST)
	public String form(Event event) {
		er.save(event);
		return "redirect:/events";
	}
	
	@RequestMapping(value="/events")
	public ModelAndView listEvents() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Event> events = er.findAll();
		mv.addObject("events", events);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView eventDetail(@PathVariable("id") long id) {
		Event event = er.findById(id);
		ModelAndView mv = new ModelAndView("event/eventDetail");
		mv.addObject("event", event);
		return mv;
	}
}
