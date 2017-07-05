package com.atguigu.upload.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

public class SingleUploadAction implements ServletContextAware{

	private File logo;
	private String logoContentType;
	private String logoFileName;
	
	private ServletContext servletContext;
	
	public String execute() throws IOException {
		System.out.println("logo.name="+logo.getName());
		System.out.println("logoContentType="+logoContentType);
		System.out.println("logoFileName="+logoFileName);
		
		//将File对象代表的文件复制到upload目录下
		//1.创建一个读取logo文件的输入流对象
		InputStream in = new FileInputStream(logo);
		
		//2.创建能够将文件写入到upload目录的输出流对象
		//①在文件名前面附加系统时间避免覆盖
		String fileName = System.currentTimeMillis()+logoFileName;
		
		//②创建upload目录在Web应用中的虚拟路径，注意：要加上文件名
		String virtualPath = "/upload/"+fileName;
		
		//③将虚拟路径转化为真实路径
		String realPath = this.servletContext.getRealPath(virtualPath);
		
		//④创建输出流对象
		OutputStream out = new FileOutputStream(realPath);
		
		//3.通过读写操作复制文件
		byte [] buffer = new byte[1024];
		int len = 0;
		
		while((len = in.read(buffer))!=-1) {
			
			out.write(buffer, 0, len);
			
		}
		
		out.close();
		in.close();
		
		return "success";
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

}
