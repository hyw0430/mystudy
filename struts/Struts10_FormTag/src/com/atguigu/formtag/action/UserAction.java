package com.atguigu.formtag.action;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.formtag.bean.Season;


public class UserAction {
	
	private String userName;
	private String userPwd;
	private String describe;
	private String userId;
	
	private String season;
	private List<String> team;
	private String favoriteSeason;
	
	//用于生成单选框数据的List
	private List<Season> seasonList;
	
	//用于注入请求参数
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//用于回显表单
	public String getUserName() {
		return userName;
	}
	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<Season> getSeasonList() {
		return seasonList;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	
	public String getSeason() {
		return season;
	}

	public List<String> getTeam() {
		return team;
	}

	public void setTeam(List<String> team) {
		this.team = team;
	}

	public String getFavoriteSeason() {
		return favoriteSeason;
	}

	public void setFavoriteSeason(String favoriteSeason) {
		this.favoriteSeason = favoriteSeason;
	}

	public String execute() {
		this.userId = "111001010";
		
		System.out.println("userName="+userName);
		System.out.println("userPwd="+userPwd);
		System.out.println("describe="+describe);
		System.out.println("userId="+userId);
		
		System.out.println("season="+season);
		
		for(String teamName:team) {
			System.out.println("teamName="+teamName);
		}
		
		System.out.println("favoriteSeason="+favoriteSeason);
		
//		User user = new User();
//		user.setUserName("Jerry");
//		
//		ValueStack valueStack = ActionContext.getContext().getValueStack();
//		valueStack.push(user);
		
		//为了生成单选框，给List填充数据
		seasonList = new ArrayList<Season>();
		seasonList.add(new Season("spring", "春天"));
		seasonList.add(new Season("summer","夏天"));
		seasonList.add(new Season("autumn","秋天"));
		seasonList.add(new Season("winter","冬天"));
		
		return "success";
	}

}
