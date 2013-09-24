package com.vows.ninja.data.access;

/*
 * Interface for DAO to access Profile related information
 */

public interface ProfileDAO {
	public void create(Profile p);
	public Profile get(int id);
}
