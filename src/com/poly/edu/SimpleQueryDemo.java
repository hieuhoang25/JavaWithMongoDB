package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
/*
 * Một số method của class DBCollection cho việc truy vấn
 * public DBCursor find(DBObject ref)
 * 
 * public DBCursor find(DBObject ref, DBObject key)
 * */
public class SimpleQueryDemo {
	public static void main(String[] args) throws UnknownHostException {
		//kết nối DB trương
		MongoClient mongodb = MyMongo.getMongoClient();
		DB db = mongodb.getDB("mydb");
		DBCollection dept = db.getCollection("department");
		// Truy vấn tất cả object trong Collection 
		DBCursor cursor = dept.find();
		int i = 1;
		
		while(cursor.hasNext()) {
			System.out.println("Document: " +1);
			System.out.println(cursor.next());
			i++;
		}
	}
}
