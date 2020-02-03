package com.thinhvp.masterdemo.dto.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class TransactionalPersistenceTask<T, ID> extends Task {

	private static Logger LOG = LoggerFactory.getLogger(TransactionalPersistenceTask.class);

	private JpaRepository<T, ID> repository;

	private PlatformTransactionManager transactionManager;

	private List<T> data = new ArrayList<>();

	private int taskIndex;

	public TransactionalPersistenceTask(int jobId, int taskIndex, List<T> data, JpaRepository<T, ID> repository, PlatformTransactionManager transactionManager) {
		super(jobId);
		this.taskIndex = taskIndex;
		this.data.addAll(data);
		this.repository = repository;
		this.transactionManager = transactionManager;
	}

	@Override
	protected void runTask() {
		new TransactionTemplate(transactionManager).execute(status -> repository.saveAll(data));
		LOG.info("Job ID - " + getJobId() + "Persisted task: " +taskIndex);
	}

	public int getTaskIndex() {
		return taskIndex;
	}

	public void setTaskIndex(int taskIndex) {
		this.taskIndex = taskIndex;
	}
}
