package com.atguigu.valuestack.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.atguigu.valuestack.bean.Person;
import com.atguigu.valuestack.bean.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class ValueStackAction {
	
	public String testReadMapStack06() {
		//测试14：使用OGNL表达式从Map栈中使用带特殊符号的键读取数据
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//String findString = valueStack.findString("#session.special-name");
		//System.out.println("findString="+findString); 0
		
		//使用[]读取包含特殊符号的键，做法和EL、JSON中一样
		String findString = valueStack.findString("#session['special-name']");
		System.out.println("findString="+findString);
		
		return "success";
	}
	
	public String testReadMapStack05() {
		
		//测试13：使用OGNL表达式从各个域对象中按从小到大的顺序读取数据
		//1.准备数据
		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("attrName", "reqValue");
		
		HttpSession session = request.getSession();
//		session.setAttribute("attrName", "sessionValue");
		
		ServletContext servletContext = session.getServletContext();
		servletContext.setAttribute("attrName", "appValue");
		
		//2.读取attrName属性名对应的属性值
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		String attrName = valueStack.findString("#attr.attrName");
		System.out.println("attrName="+attrName);
		
		return "success";
	}
	
	public String testReadMapStack03() {
		
		//测试11：使用OGNL表达式从Map栈中读取session域中的数据
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		String sessAttrName = valueStack.findString("#session.sessAttrName");
		System.out.println("sessAttrName="+sessAttrName);
		
		return "success";
	}
	
	public String testReadMapStack01() {
		
		//测试9：使用OGNL表达式从Map栈中读取请求参数
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
//		Object paramArr = valueStack.findValue("#parameters.userName");
//		System.out.println(paramArr);
		
		//注意数组
		String userName = valueStack.findString("#parameters.userName[0]");
		System.out.println("userName="+userName);
		
		return "success";
	}
	
	public String testReadObjectStack03() {
		
		//测试8：OGNL：不指定索引时表示默认从栈顶读取数据
		//1.准备数据
		Student student = new Student("快乐的学生");
		Person person = new Person("高兴的人");
		
		//2.获取ValueStack对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//3.压入测试数据
		valueStack.push(person);
		valueStack.push(student);
		
		//4.使用OGNL表达式访问
		String stuName = valueStack.findString("stuName");
		System.out.println("stuName="+stuName);
		
		String personName = valueStack.findString("personName");
		System.out.println("personName="+personName);
		
		return "success";
	}
	
	public String testReadObjectStack02() {
		
		//测试7：OGNL：[索引].属性名的方式从索引值指定的位置开始查找对象
		//1.准备数据
		Student tom01 = new Student("Tom01");
		Student tom02 = new Student("Tom02");
		Student tom03 = new Student("Tom03");
		
		Person per01 = new Person("per01");
		Person per02 = new Person("per02");
		Person per03 = new Person("per03");
		
		//2.获取值栈对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//3.压入
		valueStack.push(tom01);
		valueStack.push(tom02);
		valueStack.push(tom03);
		
		valueStack.push(per01);
		valueStack.push(per02);
		valueStack.push(per03);
		//4.通过OGNL表达式读取
		//per03[0] per02[1] per01[2] Tom03[3] Tom02[4] Tom01[5]
		String personName = valueStack.findString("[2].personName");
		System.out.println("personName="+personName);//personName=per01
		
		String stuName = valueStack.findString("[2].stuName");
		System.out.println("stuName="+stuName);//stuName=Tom03
		
		return "success";
	}
	
	public String testReadObjectStack01() {
		
		//测试6：OGNL：[0].属性名的方式从栈顶开始查找对象
		//1.准备数据
		Student student = new Student("快乐的学生");
		Person person = new Person("高兴的人");
		
		//2.获取ValueStack对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//3.压入测试数据
		valueStack.push(person);
		valueStack.push(student);
		
		//4.使用OGNL表达式读取
		String stuName = valueStack.findString("[0].stuName");
		//stuName=快乐的学生
		System.out.println("stuName="+stuName);
		
		String personName = valueStack.findString("[0].personName");
		//personName=高兴的人
		System.out.println("personName="+personName);
		
		return "success";
	}
	
	public String testMapStackValue() {
		
		//测试5：向请求域中保存数据，在Map栈中查看
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("atguiguMessage", "AtguiguIsVeryGood!");
		
		return "success";
	}
	
	public String testMapStack() {
		//测试4：Map栈的本质
		//1.通过ActionContext对象获取Map栈
		Map<String, Object> contextMap = ActionContext.getContext().getContextMap();
		
		//2.通过ValueStack对象获取Map栈
		Map<String, Object> context = ActionContext.getContext().getValueStack().getContext();
		
		//3.比较
		System.out.println("通过ActionContext对象获取Map栈："+contextMap);
		System.out.println("通过ValueStack对象获取Map栈："+context);
		
		System.out.println(contextMap == context);
		
		return "success";
	}
	
	public String testPush() {
		
		//测试3：将测试数据压入对象栈栈顶
		//1.准备数据
		Person person = new Person("GoodPerson");
		Student student = new Student("BadStudent");
		
		//2.获取ValueStack对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//3.压入
		valueStack.push(student);
		valueStack.push(person);
		
		return "success";
	}
	
	public String testGetValueStack() {
		
		//测试2：获取ValueStack对象
		//1.获取ActionContext对象
		ActionContext context = ActionContext.getContext();
		
		//2.获取ValueStack对象
		ValueStack valueStack = context.getValueStack();
		System.out.println("valueStack="+valueStack);
		
		return "success";
	}
	
	//提供一个getXxx()方法，在页面上使用EL表达式读取：${requestScope.happyMessage }
	public String getHappyMessage() {
		return "happyMessage";
	}
	
	public String testGetProperty() {
		
		//测试1：通过requestScope.属性名方式读取数据
		
		return "success";
	}

}
