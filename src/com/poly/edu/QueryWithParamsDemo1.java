package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
/*
 * Ví dụ truy vấn có điều kiện 1
 * Để tạo ra các đối tượng DBObject bn có thể sử dụng BasicDBOjbjectBuilder
 *	   BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start()
 *	   whereBuilder.append("dept_name","ACCOUNTING")
 *		DBObject where = whereBuilder.get()
 *	Query
 *     DBcursor cursor = dept.find(where)
 * */


public class QueryWithParamsDemo1 {
	//Xây dưng JSON 
	//{{"dept_name" :"ACCOUNTING"}}
	public static DBObject getWhereClause1() {
		BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
		//Sư dụng method append (cũng giống với dùng add)
		whereBuilder.append("dept_name", "ACCOUNTING");
		//
		DBObject where = whereBuilder.get();
		System.out.println("Where: "+ where.toString());
		return where;
	}
	public static void main(String[] args) throws UnknownHostException {
		  MongoClient mongoClient = MyMongo.getMongoClient();
		  	DB db = mongoClient.getDB("mydb");
		  	DBCollection dept = db.getCollection("department");
		  	DBObject where = getWhereClause1();
		  	//Truy vấn theo điều kiện
		  	DBCursor  cursor=dept.find(where);
		  	int i=1;
		  	 while (cursor.hasNext()) {
		           System.out.println("Document: " + i);
		           System.out.println(cursor.next());
		           i++;
		       }

		       System.out.println("Done!");
	}
}
