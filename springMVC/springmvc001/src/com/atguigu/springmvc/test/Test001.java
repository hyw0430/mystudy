package com.atguigu.springmvc.test;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.springmvc.entities.User;


@Controller
//@SessionAttributes(value="user")
public class Test001
{
	@RequestMapping(value="/testParams",method=RequestMethod.GET,params={"age","tel!=110"})
	public String testParams()
	{
		return "ok";
	}

	@RequestMapping(value="/testHeaders",method=RequestMethod.GET,headers={"Host=localhost:8081"})
	public String testHeaders()
	{
		return "ok";
	}
	
	@RequestMapping(value="/testAnt/**/4",method=RequestMethod.GET)	
	public String testAnt()
	{
		return "ok";
	}

	@RequestMapping(value="/testPathVariable/{bookId}",method=RequestMethod.GET)	
	public String testPathVariable(@PathVariable("bookId") String bookId)
	{
		System.out.println("-----testPathVariable: "+bookId);
		return "ok";
	}	
	
	@RequestMapping(value="/order",method=RequestMethod.POST)	
	public String testRestful_Post()
	{
		System.out.println("-----testRestful_Post: ");
		return "ok";
	}	
	
	@RequestMapping(value="/order/{ordId}",method=RequestMethod.DELETE)	
	public String testRestful_Delete(@PathVariable("ordId") String ordId)
	{
		System.out.println("-----testRestful_Delete: "+ordId);
		return "ok";   
	}	
	@RequestMapping(value="/order/{ordId}",method=RequestMethod.PUT)	
	public String testRestful_Put(@PathVariable("ordId") String ordId)
	{
		System.out.println("-----testRestful_Put: "+ordId);
		return "ok";   
	}	
	@RequestMapping(value="/order/{ordId}",method=RequestMethod.GET)	
	public String testRestful_Get(@PathVariable("ordId") String ordId)
	{
		System.out.println("-----testRestful_Get: "+ordId);
		return "ok";   
	}
	@RequestMapping(value="/testRequestParam",method=RequestMethod.GET)	
	public String testRequestParam(@RequestParam(value="age",required=true,defaultValue="300") Integer age,
			@RequestParam("roldId")	List<String> roldId)
	{
		System.out.println("-----testRequestParam: "+age);
		for (String string : roldId)
		{
			System.out.println(string);
		}
		return "ok";   
	}
	@RequestMapping(value="/testRequestHeader",method=RequestMethod.GET)	
	public String testRequestHeader(@RequestHeader("Host") String host)
	{
		System.out.println("-----testRequestHeader: "+host);
		return "ok";   
	}	
	@RequestMapping(value="/testCookieValue",method=RequestMethod.GET)	
	public String testCookieValue(@CookieValue("JSESSIONID") String JSESSIONID)
	{
		System.out.println("-----testCookieValue: "+JSESSIONID);
		return "ok";   
	}	
	@RequestMapping(value="/testPOJO",method=RequestMethod.POST)	
	public String testPOJO(User user)
	{
		System.out.println("-----testPOJO: "+user.toString());
		return "ok";   
	}
	
	@RequestMapping(value="/testServletAPI",method=RequestMethod.GET)	
	//public String testServletAPI(HttpServletRequest request,HttpServletResponse response)
	public void testServletAPI(HttpServletRequest request,HttpServletResponse response,Writer writer) throws IOException
	{
		/*System.out.println("-----testPOJO: "+request.getContextPath());
		System.out.println("-----testPOJO: "+response.getCharacterEncoding());
		return "ok"; */  
		writer.write("hello 0719......");
		writer.close();
	}
	
	@RequestMapping(value="/testModelAndView",method=RequestMethod.GET)		
	public ModelAndView testModelAndView()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("mykey","myModelAndView--0719");
		mv.setViewName("ok");
		return mv;
	}
	@RequestMapping(value="/testMap_Model_ModelMap",method=RequestMethod.GET)			
	public String testMap_Model_ModelMap(Map<String,Object> m1,Model m2,ModelMap m3)
	//public String testMap_Model_ModelMap(Map<String,Object> m1)
	{
		m1.put("m1","java.util.Map<K, V>");
		m2.addAttribute("m2","org.springframework.ui.Model");
		m3.addAttribute("m3","org.springframework.ui.ModelMap");
		
		System.out.println(m1 == m2);
		System.out.println(m2 == m3);
		System.out.println(m1 == m3);
		
		System.out.println(m1.getClass().getName());
		System.out.println(m2.getClass().getName());
		System.out.println(m3.getClass().getName());
		
		return "ok";
	} 
	@RequestMapping(value="/testSessionAttributes",method=RequestMethod.GET)				
	public String testSessionAttributes(Map<String,Object> map)
	{
		User user = new User(11,"z3","123456",17,"z3@163.com");
		map.put("user",user);
		
		map.put("subway","line6##############");
		return "ok";
	}
	
	@RequestMapping(value="/testModelAttribute",method=RequestMethod.PUT)				
	public String testModelAttribute(@ModelAttribute("xxx") User user)
	{
		System.out.println("from front page: "+user.toString());
		return "ok";
	}
	@ModelAttribute
	public void getUserById(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map)
	{
		System.out.println("come in @ModelAttribute*************");
		if(null != id)
		{
			//模拟根据id从数据库查询出来对应的User信息
			//User user = UserService.getUserById(id);
			User user = new User(11,"lisi","123456",26,"lisi@163.com");
			
			map.put("user",user);
			System.out.println("from mysql DB:"+user.toString());
		}
	}
	//testJSTLView
	@RequestMapping(value="/testJSTLView",method=RequestMethod.GET)				
	public String testJSTLView()
	{
		System.out.println("-----testJSTLView");
		return "ok";
	}	
	
	@RequestMapping(value="/testHelloView",method=RequestMethod.GET)				
	public String testHelloView()
	{
		System.out.println("-----testHelloView");
		return "helloView";
	}
	@RequestMapping(value="/testRedirect",method=RequestMethod.GET)				
	public String testRedirect()
	{
		System.out.println("-----testRedirect");
		return "redirect:/1.jsp";
	}	

	@RequestMapping(value="/testForward",method=RequestMethod.GET)				
	public String testForward()
	{
		System.out.println("-----testForward");
		return "forward:/2.jsp";
	}	
}
