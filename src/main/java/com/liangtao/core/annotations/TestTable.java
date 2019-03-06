package com.liangtao.core.annotations;

public class TestTable {
	public void execute() {
		System.out.println("Executing..");
	}
	@Test
	void testExecute() {
		execute();
	}
}
