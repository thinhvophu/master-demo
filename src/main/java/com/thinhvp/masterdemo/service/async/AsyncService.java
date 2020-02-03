package com.thinhvp.masterdemo.service.async;

import com.thinhvp.masterdemo.dto.async.Task;
import com.thinhvp.masterdemo.persistence.entity.User;

import java.util.List;

public interface AsyncService {

	void persist(List<User> users, int taskId, int batchSize);

	void execute(List<Task> tasks);

	boolean cancel(int taskId);

	void pause(int taskId);

	void resume(int taskId);

	String getProgress(int taskId);
}
