package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class BlogVo {

	private String id;
	private String blogTitle;
	private MultipartFile logoFile;
	
	private String saveName;
	private String filePath;
	
	private String userName;
	
	public BlogVo() {}

	public BlogVo(String id, String blogTitle, MultipartFile logoFile, String saveName, String filePath,
			String userName) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.saveName = saveName;
		this.filePath = filePath;
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

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", saveName=" + saveName
				+ ", filePath=" + filePath + ", userName=" + userName + "]";
	}
	
	

}
