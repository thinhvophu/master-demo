package com.thinhvp.masterdemo.service.async.impl;


import com.thinhvp.masterdemo.dto.async.Job;
import com.thinhvp.masterdemo.dto.async.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class AsyncTaskManager {

	private static Logger LOG = LoggerFactory.getLogger(AsyncTaskManager.class);

	private Map<Integer, Job> jobMap = new ConcurrentHashMap<>();

	private ThreadPoolTaskExecutor taskExecutor;

	public void execute(Job job) {
		int jobId = job.getJobId();
		if (!jobMap.containsKey(jobId)) {
			for (Task task : job.getTasks()) {
				job.getTasksResult().add(taskExecutor.submit(task));
			}
			jobMap.put(jobId, job);
		}
	}


	public boolean cancel(int jobId) {
		List<Future> futures = jobMap.get(jobId).getTasksResult();

		if (CollectionUtils.isNotEmpty(futures)) {
			futures.stream().filter(future -> !future.isDone() && !future.isCancelled()).forEach(future -> future.cancel(true));
			return true;
		}

		return false;
	}

	public String getProgress(int jobId) {
		Job jobProgress = jobMap.get(jobId);
		if (jobProgress != null && CollectionUtils.isNotEmpty(jobProgress.getTasksResult())) {
			long size = jobProgress.getTotalTasks();
			long done = jobProgress.getTasksResult().stream().filter(Future::isDone).count();
			long cancelled = jobProgress.getTasksResult().stream().filter(Future::isCancelled).count();
			return "Finished: " + done + ", cancelled" + cancelled + ",Total: " + size;
		}
		return "Not running";
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
