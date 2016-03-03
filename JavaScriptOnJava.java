package demo.jsj.demo;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptOnJava {

	public static void main(String[] args)throws Exception {
		//获得一个javascript的执行引擎
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		//建立上下文变量
		Bindings bind = engine.createBindings();
		bind.put("factor", 10);
		//绑定上下文，作用域是当前引擎范围
		engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
		Scanner input = new Scanner(System.in);
		while(input.hasNextInt())
		{
			int first = input.nextInt();
			int sec = input.nextInt();
			System.out.println("输入参数是："+first+","+sec);
			//执行js代码
			//engine.eval(new FileReader("D:/model.js"));
			
			//获得项目指定目录下的js文件
			URL url = JavaScriptOnJava.class.getResource("model.js");  
			//执行js代码
			engine.eval(new FileReader(url.getPath()));
			
			//是否可调用方法
			if(engine instanceof Invocable)
			{
				Invocable in = (Invocable)engine;
				//执行js中的函数
				Double result = (Double)in.invokeFunction("formula", first,sec);
				System.out.println("运行结果："+result.intValue());
			}
		}
		System.out.println("异常输入，程序终止。");
	}

}
