package com.doctorcar.mobile.utils;

import java.util.List;

public class ListUtils {
	
	public static <T> boolean isList(List<T>list){
		if(list != null && list.size() >=0){
			return true;
		}
		return false;
	}

}
