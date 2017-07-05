package com.atguigu.upload.action;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class MultiUploadAction02 extends ActionSupport {

	private List<File> uploadFile;
	private List<String> uploadFileContentType;
	private List<String> uploadFileFileName;
	
	public String execute(){
		
		int size = uploadFile.size();
		
		for(int i = 0; i < size; i++) {
			File file = uploadFile.get(i);
			String contentType = uploadFileContentType.get(i);
			String fileName = uploadFileFileName.get(i);
			System.out.println("file="+file.getName());
			System.out.println("contentType="+contentType);
			System.out.println("fileName="+fileName);
			System.out.println();
		}
		
		return "success";
	}

	public void setUploadFile(List<File> uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setUploadFileContentType(List<String> uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public void setUploadFileFileName(List<String> uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

}
