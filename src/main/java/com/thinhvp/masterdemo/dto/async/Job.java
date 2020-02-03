package com.thinhvp.masterdemo.dto.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class Job {

	private int jobId;

	private int totalTasks;

	private List<Task> tasks = new ArrayList<>();

	private List<Future> tasksResult = new ArrayList<>();

	public Job(int jobId) {
		this.jobId = jobId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public List<Future> getTasksResult() {
		return tasksResult;
	}

	public void setTasksResult(List<Future> tasksResult) {
		this.tasksResult = tasksResult;
	}

	public void addTasksResult(List<Future> tasksResult) {
		this.tasksResult.addAll(tasksResult);
	}

	public int getTotalTasks() {
		return tasks.size();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
