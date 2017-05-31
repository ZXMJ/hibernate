package com.ultra.hibernate.model;

import java.sql.Blob;
import java.util.Date;

public class News {

	private Integer id;
	private String title;
	private String author;
	private Date date;
	private String desc;
	// 大文本
	private String content;
	// 二进制数据
	private Blob image;

	// 组件属性
	private Detail detail;

	public News() {
	}

	public News(String title, String author, Date date, Detail detail) {
		this.title = title;
		this.author = author;
		this.date = date;
		this.detail = detail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author + ", date=" + date + ", desc=" + desc
				+ ", content=" + content + ", image=" + image + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

}
