package com.liangtao.core.annotations.apt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

/**
 * 接口提取处理器
 * {Exec： apt -factory com.liangtao.core.annotations.apt.InterfaceExtractorProcessorFactory Multiplier.java -s ../apt}
 * 参数-s表示任何生成的新文件，都必须放在apt目录中
 */
public class InterfaceExtractorProcessor implements AnnotationProcessor {
	private final AnnotationProcessorEnvironment env;
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<MethodDeclaration>();
	
	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		super();
		this.env = env;
	}

	@Override
	public void process() {
		for(TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
			ExtractInterface annot = typeDecl.getAnnotation(ExtractInterface.class);
			if(annot==null)
				break;
			for(MethodDeclaration m : typeDecl.getMethods()) {
				if(m.getModifiers().contains(Modifier.PUBLIC)&&!(m.getModifiers().contains(Modifier.STATIC))){
					interfaceMethods.add(m);
				}
			}
			//组装将要生成的接口结构
			if(interfaceMethods.size()>0) {
				try {
					PrintWriter writer = env.getFiler().createSourceFile(annot.value());
					writer.println("package "+typeDecl.getPackage().getQualifiedName()+";");
					writer.println("public interface "+annot.value()+" {");
					for(MethodDeclaration m : interfaceMethods){
						writer.print(" public ");
						writer.print(m.getReturnType()+" ");
						writer.print(m.getSimpleName()+" (");
						int i = 0;
						for(ParameterDeclaration parm : m.getParameters()) {
							writer.print(parm.getType()+" " + parm.getSimpleName());
							if(++i < m.getParameters().size()) {
								writer.print(", ");
							}
						}
						writer.println(");");
					}
					writer.println("}");
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			
		}
	}
}
