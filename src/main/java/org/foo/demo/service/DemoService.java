package org.foo.demo.service;

import java.time.LocalDateTime;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {

	private Logger logger = LoggerFactory.getLogger(DemoService.class);

	private final JdbcTemplate jdbcTemplate;
	private final String schedulerName;
	private final String schedulerInstanceId;

	public DemoService(JdbcTemplate jdbcTemplate, Scheduler scheduler) throws SchedulerException {
		this.jdbcTemplate = jdbcTemplate;
		schedulerInstanceId = scheduler.getSchedulerInstanceId();
		schedulerName = scheduler.getSchedulerName();
	}

	@Transactional
	public void writeCurrentDateTime(int nTimes, JobDetail jobDetail) {
		JobKey key = jobDetail.getKey();
		while (nTimes >= 0) {
			logger.info(
					"schedulerName={}, schedulerInstanceId={}, jobDetail.group={}, jobDetail.name={}, times_left={}",
					schedulerName,
					schedulerInstanceId,
					key.getGroup(),
					key.getName(),
					nTimes);
			jdbcTemplate.update(
					"insert into demo.execution(date_time, scheduler_name, scheduler_instance_id,job_group,job_name) values (?,?,?,?,?)",
					LocalDateTime.now(), schedulerName, schedulerInstanceId, key.getGroup(), key.getName());
			try {
				logger.debug("sleeping");
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				logger.warn("interrupted while attempt left={}", nTimes);
				Thread.currentThread().interrupt();
				return;
			}
			nTimes--;
		}
	}

}
