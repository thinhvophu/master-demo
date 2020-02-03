package com.thinhvp.masterdemo.controller;

import com.thinhvp.masterdemo.persistence.entity.User;
import com.thinhvp.masterdemo.service.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class AsyncTaskController {

	@Autowired
	AsyncService asyncService;

	@GetMapping(value = "importuser/{taskId}")
	public void execute(@RequestParam int numberOfUser, @RequestParam int batchSize, @PathVariable int taskId) {
		List<User> users = mockListUsers(numberOfUser);
		asyncService.persist(users, taskId, batchSize);
	}

	@GetMapping(value = "{taskId}/status")
	public String getProgress(@PathVariable int taskId) {
		return asyncService.getProgress(taskId);
	}

	@GetMapping(value = "{taskId}/cancel")
	public boolean cancel(@PathVariable int taskId) {
		return asyncService.cancel(taskId);
	}

	private List<User> mockListUsers(int numberOfUser) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < numberOfUser; i++) {
			users.add(createUser(i));
		}
		return users;
	}

	private User createUser(int index) {
		User user = new User();
		user.setName("User " + index);
		user.setAddress("Address of user " + index);
		return user;
	}
}
