package com.taufik.Dao;

import com.taufik.Model.DataStored;

public interface Dao {
	public void Register(DataStored data);
	String Retrieve (String itemName);
	int GetType (String itemName);
	void Deregister (String itemName);
}
