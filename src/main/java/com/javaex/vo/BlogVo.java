package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class BlogVo {

	private String id;
	private String blogTitle;
	private MultipartFile logoFile;
	
	private String userName;
	
	public BlogVo() {}
	

	public BlogVo(String id, String blogTitle, MultipartFile logoFile, String userName) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.userName = userName;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getBlogTitle() {
		return blogTitle;
	}


	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}


	public MultipartFile getLogoFile() {
		return logoFile;
	}


	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", userName=" + userName
				+ "]";
	}


	
}
