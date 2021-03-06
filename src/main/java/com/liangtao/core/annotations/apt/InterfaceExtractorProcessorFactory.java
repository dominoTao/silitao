package com.liangtao.core.annotations.apt;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class InterfaceExtractorProcessorFactory implements
		AnnotationProcessorFactory {
	/**
	 * 返回接口提取处理器
	 * @param atds : 使用apt工具时传入的java类
	 * @param env : 将传入给处理器对象
	 */
	public AnnotationProcessor getProcessorFor(
			Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
		return new InterfaceExtractorProcessor(env);
	}

	public Collection<String> supportedAnnotationTypes() {
		//返回仅包含指定对象的不可变集
		return Collections.singleton("com.liangtao.core.annotations.apt.ExtractInterface");
	}

	public Collection<String> supportedOptions() {
		//返回空集
		return Collections.emptySet();
	}

}
