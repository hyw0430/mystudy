package com.atguigu.download.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import sun.misc.BASE64Encoder;

public class DownloadAction implements ServletContextAware,ServletRequestAware{
	
	private String contentType;
	private int contentLength;
	private String contentDisposition;
	private String inputName;
	private InputStream inputStream;
	
	private ServletContext servletContext;
	private HttpServletRequest request;
	
	public String execute() throws IOException {
		
		//1.准备要下载的文件的虚拟路径
		String virtualPath = "/WEB-INF/郁金香.jpg";
		
		//2.将虚拟路径转换为真实路径
		String realPath = this.servletContext.getRealPath(virtualPath);
		
		//3.创建读取要下载的目标文件的输入流对象
		inputStream = new FileInputStream(realPath);
		
		//4.设置内容类型
		contentType = "application/x-msdownload";
		
		//5.获取目标文件的大小
		contentLength = inputStream.available();
		
		//6.准备文件名
		String fileName = "郁金香.jpg";
		
		//7.获取User-Agent请求消息头
		String userAgent = request.getHeader("User-Agent");
		
		//8.对文件名进行编码
		if(userAgent.contains("Firefox")) {
			//①火狐浏览器
			fileName = "=?utf-8?B?"+ new BASE64Encoder().encode(fileName .getBytes("utf-8"))+ "?=";
			
		}else{
			//②其他浏览器
			fileName = URLEncoder.encode(fileName, "UTF-8");
			
		}
		
		//9。设置contentDisposition的值
		contentDisposition = "attachment;filename=" +fileName;
		
		//10.设置inputName值
		//说明：inputStream是inputName的默认值，可以省略
		inputName = "inputStream";
		
		return "success";
	}

	public String getContentType() {
		return contentType;
	}

	public int getContentLength() {
		return contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public String getInputName() {
		return inputName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
