package com.mooo.bitarus.chucknorris;

// import com.fasterxml.jackson.annotation.JsonProperty
// @JsonProperty("propInJSON")
public class Joke {
	private String[] categories;
	private String created_at;
	private String icon_url;
	private String id;
	private String updated_at;
	private String url;
	private String value;

	public String[] getCategories() {
		return this.categories;
	}

	public String getCreated_at() {
		return this.created_at;
	}

	public String getIcon_url() {
		return this.icon_url;
	}

	public String getId() {
		return this.id;
	}

	public String getUpdated_at() {
		return this.updated_at;
	}

	public String getUrl() {
		return this.url;
	}

	public String getValue() {
		return this.value;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public void setCreated_at(String createdAt) {
		this.created_at = created_at;
	}

	public void setIcon_url(String iconUrl) {
		this.icon_url = icon_url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUpdated_at(String updatedAt) {
		this.updated_at = updated_at;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
