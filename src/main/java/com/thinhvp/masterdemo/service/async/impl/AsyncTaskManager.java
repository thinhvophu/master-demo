package com.thinhvp.masterdemo.service.async.impl;


import com.thinhvp.masterdemo.dto.async.TaskProgress;
import com.thinhvp.masterdemo.dto.async.TrackableTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class AsyncTaskManager {

	private Map<Integer, TaskProgress> taskMap = new ConcurrentHashMap<>();

	private ThreadPoolTaskExecutor taskExecutor;

	public void execute(TrackableTask task) {
		TaskProgress progress = new TaskProgress(task, taskExecutor.submit(task));
		taskMap.put(task.getTaskId(), progress);
	}

	public boolean cancel(int taskId) {
		TrackableTask task = taskMap.get(taskId).getTask();
		Future future = taskMap.get(taskId).getFuture();

		if (task != null && !future.isCancelled() && !future.isDone()) {
			return future.cancel(true);
		}

		return false;
	}

	public int getProgress(int taskId) {
		TrackableTask task = taskMap.get(taskId).getTask();
		Future future = taskMap.get(taskId).getFuture();
		if (task != null && !future.isCancelled()) {
			return task.getCurrentStep();
		}
		return -1;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
