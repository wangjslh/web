package com.jslh.bean.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DemoDto {

	private Long id;
	private String name;
	private Date date;
	private List<DemoDto2> data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<DemoDto2> getData() {
		return data;
	}

	public void setData(List<DemoDto2> data) {
		this.data = data;
	}

}
