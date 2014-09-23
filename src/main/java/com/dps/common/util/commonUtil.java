package com.dps.common.util;

import java.util.Random;

public class commonUtil {
	public static  String getSixDigitRandomNum(){
		char array[] = {'0','1','2','3','4','5','6','7','8','9'};
		Random rand = new Random();
		for(int i =10;i>1;i--){
			int index = rand.nextInt(i);
			char tmp = array[index];
			array[index] = array[i-1];
			array[i-1] = tmp;
		}
		String result = "";
		for(int i=0;i<6;i++)
			result+= array[i];
        return result;
	}
	
}
