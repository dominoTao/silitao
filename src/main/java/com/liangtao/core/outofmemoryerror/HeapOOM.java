package com.liangtao.core.outofmemoryerror;

import java.util.ArrayList;
import java.util.List;

/**
 * java堆溢出
 * VM Args:-verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author Administrator
 *
 */
public class HeapOOM {
	static class OOMObject{
		
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
