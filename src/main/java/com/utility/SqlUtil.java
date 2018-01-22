package com.utility;

import com.bean.User;

import java.lang.reflect.Field;

public class SqlUtil<T> {
	
	
	public  String getSqlByConditions(T t , String tableName) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from "+tableName+" where 1 = 1  ");
		try{
			
			Field fields[] = 	t.getClass().getDeclaredFields();
			for(Field field:fields){
				
				field.setAccessible(true);
				String value = (String)field.get(t);
				
				if(value != null ){
					buffer.append(" and  "+  field.getName() + "='" +value+"'"  );
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("sql:"+buffer.toString());
		return buffer.toString();
	}
	
	
	
	public static void main( String[] args ) {
		
		User user = new User();
		user.setUserName("123");
		user.setAmazonId("");
		String sql = new SqlUtil<User>().getSqlByConditions( user , "user" );
		System.out.println("sql:"+sql);
		
	}
	


}
