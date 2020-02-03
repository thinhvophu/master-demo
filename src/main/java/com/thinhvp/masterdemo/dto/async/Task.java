package com.thinhvp.masterdemo.dto.async;

public abstract class Task implements Runnable {

	private int jobId;

	protected Task(int jobId) {
		this.jobId = jobId;
	}

	@Override
	public void run() {
		runTask();
	}

	protected abstract void runTask ();

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}
