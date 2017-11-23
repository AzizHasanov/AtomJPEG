package com.myProjects.atomjpeg.model;

public class SearchInput {

	private String url;
	private String profile;
	private String level;	
	

	private boolean jpg;
	private boolean png;
	private boolean gif;


	private String pageview;
	private String billing;

	// default constructor is required
	public SearchInput() {

	}

	public SearchInput(String url, String profile, String level,String pageview, String billing) {
		this.url = url;
		this.profile = profile;
		this.level = level;
		this.pageview = pageview;
		this.billing = billing;
	}

	public String toString()
	{
		return "Url: " + url + "; profile: " + profile + "; level: " + level + 
				"; imageJPG: " + jpg + "; imagePNG: " + png + "; imageGIF: " + gif +
				"; pageview: " + pageview + "; billing: " + billing;
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	

	public boolean isJpg() {
		return jpg;
	}

	public void setJpg(boolean jpg) {
		this.jpg = jpg;
	}

	public boolean isPng() {
		return png;
	}

	public void setPng(boolean png) {
		this.png = png;
	}

	public boolean isGif() {
		return gif;
	}

	public void setGif(boolean gif) {
		this.gif = gif;
	}
	

	public String getPageview() {
		return pageview;
	}

	public void setPageview(String pageview) {
		this.pageview = pageview;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
