package com.poly.edu;

import java.rmi.UnknownHostException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class QueryWithParamsDemo2 {
	/*
	 * Hai method add và append trong BasicDBObjectBuilder là tương đương nhau
	 * BasicDBObjectBuilder.start() ~ {_} (Pointer) .push("dept_name") ~
	 * {"dept_name":{_}} .add("$regex",".*S.*") ~ {"dept_name":{"$regex":".*S*."_}}
	 * .pop() ~ {"dept_name":{"$regex":".*S*."_}} .append("description",null) ~
	 * {"dept_name":{"$regex":".*S*."},"description":null_}
	 * 
	 * 
	 * 
	 */
	// Tìm các department theo sql
	// where dept_name like '%S%' and description is null
	//// Xây dụng JSON
	// { "dept_name" : { "$regex" : ".*S.*"} , "description" : null }
	public static DBObject getWhereClause1() {
		BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
		// Sử dụng append và add là giống nhau.
		whereBuilder.push("dept_name").add("$regex", ".*S.*");
		whereBuilder.pop();// nhảy con trỏ
		whereBuilder.push("description").add("$ne", null);
		//
		DBObject where = whereBuilder.get();
		System.out.println("Where " + where.toString());
		return where;
	}

	public static void main(String[] args) throws java.net.UnknownHostException {
		MongoClient mongoClient = MyMongo.getMongoClient();
		DB db = mongoClient.getDB("mydb");
		DBCollection dept = db.getCollection("department");
		DBObject where = getWhereClause1();
		// Truy vấn theo điều kiện
		DBCursor cursor = dept.find(where);
		int i = 1;
		while (cursor.hasNext()) {
			System.out.println("Document: " + i);
			System.out.println(cursor.next());
			i++;
		}

		System.out.println("Done!");
	}

	// Sử regex của java
	public static DBObject getWhereClause2() {
		BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
		// Sử dụng append và add là giống nhau.
		// Biểu thức chính quy mô tả một chuổi
		// Bắt đầu bởi ký tự bất kỳ xuật hiện 0 hoặc nhiều lần
		// Tiếp theo kí tự S
		// Và tiếp theo là ký tự xuất h8ienej 0 hoặc nhiêu lân
		whereBuilder.push("dept_name").add("$regex", ".*S.*");
		whereBuilder.pop();
		whereBuilder.append("description", null);
		//
		DBObject where = whereBuilder.get();
		System.out.println("Where " + where.toString());
		return where;
	}
}
