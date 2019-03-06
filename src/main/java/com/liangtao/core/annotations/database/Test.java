package com.liangtao.core.annotations.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String... args) {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c apt -factory com.liangtao.core.annotations.database.TableCreationProcessorFactory com/liangtao/core/annotations/database/Member.java");
			final BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream(),"gb2312"));//解决中文乱码  
            final BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream(),"gb2312"));  
            
            new Thread(){  
                String str = null;  
                @Override  
                public void run() {  
                    try {  
                        while((str = input.readLine())!=null){  
                            System.out.println(str);  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
                  
            }.start();  
            new Thread(){  
                String str = null;  
                @Override  
                public void run() {  
                    try {  
                        while((str = error.readLine())!=null){  
                            System.out.println("Err: "+str);  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
                  
            }.start();  
            System.out.println("exit: "+p.waitFor()); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
