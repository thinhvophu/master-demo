package com.thinhvp.masterdemo.service.async.impl;

import com.thinhvp.masterdemo.dto.async.Job;
import com.thinhvp.masterdemo.dto.async.Task;
import com.thinhvp.masterdemo.dto.async.TransactionalPersistenceTask;
import com.thinhvp.masterdemo.persistence.entity.User;
import com.thinhvp.masterdemo.service.async.AsyncService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SimpleAsyncService implements AsyncService {

	@Autowired
	AsyncTaskManager progressTracker;

	@Autowired
	JpaRepository<User, Integer> repository;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Override
	public void persist(List<User> users, int jobId, int batchSize) {
		Job job = new Job(jobId);
		int i =0;
		for(List<User> batch: ListUtils.partition(users, batchSize)) {
			i++;
			job.getTasks().add(new TransactionalPersistenceTask<>(jobId,i, batch, repository, transactionManager));
		}
		progressTracker.execute(job);
	}

	@Override
	public void execute(List<Task> tasks) {
		for (Task task : tasks) {
//			progressTracker.execute(task);
		}
	}

	@Override
	public boolean cancel(int jobId) {
		return progressTracker.cancel(jobId);
	}

	@Override
	public void pause(int taskId) {

	}

	@Override
	public void resume(int taskId) {

	}

	@Override
	public String getProgress(int taskId) {
		return progressTracker.getProgress(taskId);
	}
}
