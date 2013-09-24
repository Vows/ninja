package com.vows.ninja.data.access;


/*
 * Simple Profile class which maps to table in DB
 */

public class Profile {
	int profile_id;
	String profile_name;
	
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	
}
