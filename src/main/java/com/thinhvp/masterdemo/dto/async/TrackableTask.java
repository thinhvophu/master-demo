package com.thinhvp.masterdemo.dto.async;

import com.thinhvp.masterdemo.controller.AsyncTaskController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackableTask implements Runnable {

	private static Logger LOG = LoggerFactory.getLogger(AsyncTaskController.class);

	private int taskId;
	private int steps;
	private int currentStep;

	public TrackableTask(int taskId, int steps, int currentStep) {
		this.taskId = taskId;
		this.steps = steps;
		this.currentStep = currentStep;
	}

	@Override
	public void run() {
		runTask();
	}

	protected void runTask () {
		for (int i = 0; i < steps; i ++) {
			try {
				Thread.sleep(1000);
				currentStep = i;
				LOG.info("increase to " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}
}
