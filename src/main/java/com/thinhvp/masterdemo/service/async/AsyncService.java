package com.thinhvp.masterdemo.service.async;

import com.thinhvp.masterdemo.dto.async.TrackableTask;

public interface AsyncService {

	void execute(TrackableTask runnable);

	boolean cancel(int taskId);

	void pause(int taskId);

	void resume(int taskId);

	int getProgress(int taskId);
}
