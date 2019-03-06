package com.liangtao.core.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用例注解 处理器
 */
public class UseCaseTracker {
	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		//对字节码对象cl遍历，找到所有方法上的UseCase注解，打印信息后将该注解编号移出useCases列表
		for(Method m : cl.getDeclaredMethods()) {
			UseCase uc = m.getAnnotation(UseCase.class);
			if(uc != null) {
				System.out.println("找到注解@UseCase："+uc.id()+"\t描述信息："+uc.description());
				useCases.remove(new Integer(uc.id()));
			}
		}
		for(int i : useCases) {
			System.out.println("警告：丢失用例 - "+i);
		}
	}
	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 47,48,49,50);
		trackUseCases(useCases, PasswordUtils.class);
	}
}
