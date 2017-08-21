package util;

import java.util.UUID;

/**
 * Created by esvwwxn on 8/21/2017.
 */
public class IDUtils {
	private IDUtils(){}

	public static String uuid(){
		return UUID.randomUUID().toString();
	}
}
