package com.thinhvp.masterdemo.service.async.impl;

import com.thinhvp.masterdemo.service.async.AsyncService;
import com.thinhvp.masterdemo.dto.async.TrackableTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleAsyncService implements AsyncService {

	@Autowired
	AsyncTaskManager progressTracker;

	@Override
	public void execute(TrackableTask task) {
		progressTracker.execute(task);
	}

	@Override
	public boolean cancel(int taskId) {
		return progressTracker.cancel(taskId);
	}

	@Override
	public void pause(int taskId) {

	}

	@Override
	public void resume(int taskId) {

	}

	@Override
	public int getProgress(int taskId) {
		return progressTracker.getProgress(taskId);
	}
}
