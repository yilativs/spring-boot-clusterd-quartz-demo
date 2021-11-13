package org.foo.demo.web;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scheduler")
public class SchedulerController {

	private final Scheduler scheduler;

	public SchedulerController(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@GetMapping
	public Scheduler scheduler() throws SchedulerException {
		return scheduler;
	}
	

}