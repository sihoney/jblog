package com.javaex.vo;

public class SearchVo {

	private String logoFile;
	private String id;
	private String userName;
	private String postTitle;
	private String regDate;
	
	public SearchVo() {}
	
	public SearchVo(String logoFile, String id, String userName, String postTitle, String regDate) {
		super();
		this.logoFile = logoFile;
		this.id = id;
		this.userName = userName;
		this.postTitle = postTitle;
		this.regDate = regDate;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "SearchVo [logoFile=" + logoFile + ", id=" + id + ", userName=" + userName + ", postTitle=" + postTitle
				+ ", regDate=" + regDate + "]";
	}
}
