package com.liangtao.core.annotations.database;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;
import static com.sun.mirror.util.DeclarationVisitors.*;

/**
 * 数据表生成处理器工程，嵌套表格生成处理器内部类
 * 嵌套表格生成处理器内部类，再嵌套表格创建访问者类
 * 注意：静态引用
 * {Exec： apt -factory com.liangtao.core.annotations.database.TableCreationProcessorFactory Member.java -s ../database}
 */
public class TableCreationProcessorFactory implements AnnotationProcessorFactory {

	@Override
	public AnnotationProcessor getProcessorFor(
			Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
		return new TableCreationProcessor(env);
	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Arrays.asList(
				"com.liangtao.core.annotations.database.Constraints",
				"com.liangtao.core.annotations.database.DBTable",
				"com.liangtao.core.annotations.database.SQLInteger",
				"com.liangtao.core.annotations.database.SQLString");
	}

	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptySet();
	}
	
	private static class TableCreationProcessor implements AnnotationProcessor {
		private final AnnotationProcessorEnvironment env;
		private String sql = "";
		public TableCreationProcessor(AnnotationProcessorEnvironment env) {
			super();
			this.env = env;
		}

		@Override
		public void process() {
			for(TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
				typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(),NO_OP));
				sql = sql.substring(0,sql.length()-1) + ");";
				System.out.println("creation sql is : \n"+sql);
				sql = "";
			}
		}
		
		private class TableCreationVisitor extends SimpleDeclarationVisitor{
			@Override  
			public void visitClassDeclaration(ClassDeclaration d) {
				DBTable dbTable = d.getAnnotation(DBTable.class);
				if(dbTable!=null) {
					sql += "CREATE TABLE ";
					sql += (dbTable.name().length()<1)?d.getSimpleName().toUpperCase():dbTable.name();
					sql += " (";
				}
			}
			@Override  
			public void visitFieldDeclaration(FieldDeclaration d) {
				String columnName = "";
				if(d.getAnnotation(SQLInteger.class)!=null) {
					SQLInteger sqlInteger = d.getAnnotation(SQLInteger.class);
					if(sqlInteger.name().length()<1) {
						columnName = d.getSimpleName().toUpperCase();
					}else{
						columnName = sqlInteger.name();
					}
					sql += "\n	"+columnName+" INT"+getConstraints(sqlInteger.constraints())+",";
				}
				if(d.getAnnotation(SQLString.class)!=null) {
					SQLString sqlString = d.getAnnotation(SQLString.class);
					if(sqlString.name().length()<1) {
						columnName = d.getSimpleName().toUpperCase();
					}else{
						columnName = sqlString.name();
					}
					sql += "\n	"+columnName+" VARCHAR("+sqlString.value()+")"+getConstraints(sqlString.constraints())+",";
				}
			}
			private String getConstraints(Constraints con) {
				String constraints = "";
				if(!con.allowNull()) {
					constraints += " NOT NULL";
				}
				if(con.primaryKey()) {
					constraints += " PRIMARY KEY";
				}
				if(con.unique()) {
					constraints += " UNIQUE";
				}
				return constraints;
			}
		}
	}
	
	
}
