package com.ultra.hibernate.model;

public class Module {

	private Integer moduleId;
	private String moduleName;
	private String moduleType;
	private News news;
	
	public Module() {}

	public Module(String moduleName, String moduleType, News news) {
		this.moduleName = moduleName;
		this.moduleType = moduleType;
		this.news = news;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleType=" + moduleType + "]";
	}

}
