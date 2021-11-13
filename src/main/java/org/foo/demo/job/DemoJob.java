package org.foo.demo.job;

import org.foo.demo.QuartzWithSpringDemoApplication;
import org.foo.demo.service.DemoService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Instances of this class are not managed by Spring.
 *
 */
@DisallowConcurrentExecution
public class DemoJob implements Job {

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		DemoService service = QuartzWithSpringDemoApplication.getContext().getBean(DemoService.class);
		service.writeCurrentDateTime(10,jobContext.getJobDetail());
	}

}
