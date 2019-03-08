package com.liangtao.core.concurrency;

/**
 * 共享受限资源
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public abstract int next();
	public void cancel(){
		canceled = true;
	}
	public boolean isCanceled() {
		return canceled;
	}
}
