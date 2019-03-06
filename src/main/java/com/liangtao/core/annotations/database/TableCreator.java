package com.liangtao.core.annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {Exec : com.liangtao.core.annotations.database.Member}
 */
public class TableCreator {
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("参数:带注解的类");
			System.exit(0);
		}
		for(String className : args) {
			Class<?> cl = Class.forName(className);
			DBTable dbTable = cl.getAnnotation(DBTable.class);
			if(dbTable == null) {
				System.out.println("在类："+className+"上没有DBTable注解");
				continue;
			}
			String tableName = dbTable.name();
			//如果没有给该注解赋值，则取类名当做表名
			if(tableName.length()<1) {
				tableName = cl.getName().toUpperCase();
			}
			List<String> columnDefs = new ArrayList<String>();
			StringBuilder createCommand = new StringBuilder("CREATE TABLE "+ tableName +" (");
			for(Field field : cl.getDeclaredFields()) {
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if(anns.length<1) {
					continue;
				}
				if(anns[0] instanceof SQLInteger) {
					SQLInteger sqlInteger = (SQLInteger)anns[0];
					if(sqlInteger.name().length()<1) {
						columnName = field.getName().toUpperCase();
					}else{
						columnName = sqlInteger.name();
					}
					columnDefs.add(columnName + " INT" + getConstraints(sqlInteger.constraints()));
				}
				if(anns[0] instanceof SQLString) {
					SQLString sqlString = (SQLString)anns[0];
					if(sqlString.name().length()<1) {
						columnName = field.getName().toUpperCase();
					}else{
						columnName = sqlString.name();
					}
					columnDefs.add(columnName + " VARCHAR("+ sqlString.value() +")" + getConstraints(sqlString.constraints()));
				}
			}
			for(String columnDef : columnDefs) {
				createCommand.append("\n	"+columnDef+",");
			}
			String tableCreate = createCommand.substring(0,createCommand.length()-1) + ");";
			System.out.println("Table Creation SQL for "+className+" is :\n"+tableCreate);
		}
 	}
	private static String getConstraints(Constraints con) {
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
