package com.atguigu.formtag.bean;

public class Season {
	
	private String seasonId;//spring
	private String seasonLable;//春天
	
	public Season(String seasonId, String seasonLable) {
		super();
		this.seasonId = seasonId;
		this.seasonLable = seasonLable;
	}

	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	public String getSeasonLable() {
		return seasonLable;
	}

	public void setSeasonLable(String seasonLable) {
		this.seasonLable = seasonLable;
	}

}
