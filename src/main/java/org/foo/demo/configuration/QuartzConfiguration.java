package org.foo.demo.configuration;

import javax.annotation.PostConstruct;

import org.foo.demo.job.DemoJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

	private final Scheduler scheduler;

	public QuartzConfiguration(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@PostConstruct
	private void scheduleJobs() throws SchedulerException {
		String jobNamePrefix = "job_";
		String groupPrefix = "group_";
		extracted(jobNamePrefix + 1, groupPrefix + 1);
		extracted(jobNamePrefix + 2, groupPrefix + 1);
		extracted(jobNamePrefix + 1, groupPrefix + 2);
		extracted(jobNamePrefix + 2, groupPrefix + 2);
		if (!scheduler.isStarted()) {
			// we give 10 seconds for context to start to initialize, this is needed because
			// later we get beans from context in DemoJob
			scheduler.startDelayed(10);
		}

	}

	private void extracted(String jobName, String group) throws SchedulerException {
		JobDetail demoJobDetail = JobBuilder.newJob(DemoJob.class)
				.storeDurably()
				.requestRecovery()
				.withIdentity(jobName, group).build();
		Trigger demoTrigger = TriggerBuilder.newTrigger().forJob(demoJobDetail)
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
				.build();
		if (!scheduler.checkExists(demoTrigger.getJobKey())) {
			scheduler.scheduleJob(demoJobDetail, demoTrigger);
		} else {
			scheduler.resumeJob(demoTrigger.getJobKey());
		}
	}

}
