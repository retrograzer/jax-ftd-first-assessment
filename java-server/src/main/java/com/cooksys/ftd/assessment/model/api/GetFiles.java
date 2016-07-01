package com.cooksys.ftd.assessment.model.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetFiles {
	
	private String name;

	public String setName (String name) {
		this.name = name;
		return name;
	}

	public String getName() {
		return name;
	}

}
