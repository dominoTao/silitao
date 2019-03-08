package com.liangtao.core.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * 后台进程工厂
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
