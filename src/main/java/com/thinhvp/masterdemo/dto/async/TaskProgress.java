package com.thinhvp.masterdemo.dto.async;

import java.util.concurrent.Future;

public class TaskProgress {

	private TrackableTask task;

	private Future future;

	public TaskProgress(TrackableTask task, Future future) {
		this.task = task;
		this.future = future;
	}

	public TrackableTask getTask() {
		return task;
	}

	public void setTask(TrackableTask task) {
		this.task = task;
	}

	public Future getFuture() {
		return future;
	}

	public void setFuture(Future future) {
		this.future = future;
	}
}
