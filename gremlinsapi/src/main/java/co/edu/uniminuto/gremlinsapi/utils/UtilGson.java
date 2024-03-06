package co.edu.uniminuto.gremlinsapi.utils;

import com.google.gson.Gson;

public class UtilGson {
	
	public static String SerializeObjet (Object obj) {
		Gson gson = new Gson();
		return  gson.toJson(obj);
		
	}

}
