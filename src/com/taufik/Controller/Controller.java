package com.taufik.Controller;

import com.taufik.Dao.DaoImplement;
import com.taufik.Model.DataStored;

public class Controller {
	public static void main(String[] args) {
		 DaoImplement dao = new DaoImplement();
		 DataStored data = new DataStored("Test322", "Isi Content", 2);
		 //dao.Register(data);
		 dao.Retrieve("Test");
		 dao.GetType("Test");
		 dao.Deregister("Test");
		// System.out.println("Content Type :"+dao.GetType("file"));
	}
}
 