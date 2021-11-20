package org.foo.demo.job;

import org.foo.demo.service.DemoService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

//new instance will be created on each execution and autowired by spring, because it extends QuartzJobBean
@DisallowConcurrentExecution
public class DemoJob extends QuartzJobBean {

	final DemoService service;

	public DemoJob(DemoService service) {
		this.service = service;
	}

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
		service.writeCurrentDateTime(10, jobContext.getJobDetail());

	}

}
