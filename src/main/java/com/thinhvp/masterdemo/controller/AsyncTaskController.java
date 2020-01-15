package com.thinhvp.masterdemo.controller;

import com.thinhvp.masterdemo.service.async.AsyncService;
import com.thinhvp.masterdemo.dto.async.TrackableTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task")
public class AsyncTaskController {

	@Autowired
	AsyncService asyncService;

	@GetMapping(value = "{taskId}")
	public void execute(@PathVariable int taskId) {
		TrackableTask task = new TrackableTask(taskId, 100, 0);
		asyncService.execute(task);
	}

	@GetMapping(value = "{taskId}/status")
	public int getProgress(@PathVariable int taskId) {
		return asyncService.getProgress(taskId);
	}

	@GetMapping(value = "{taskId}/cancel")
	public boolean cancel(@PathVariable int taskId) {
		return asyncService.cancel(taskId);
	}
}
