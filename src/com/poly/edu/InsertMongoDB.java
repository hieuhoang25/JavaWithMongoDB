package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertMongoDB {
	public static void main(String[] args) throws UnknownHostException {
		// kết nối tơi Mongo
		MongoClient mongoClient = MyMongo.getMongoClient();
		// Kết nối tới Database
		// Không nhất thiết phải tồn tại sẵn
		// nó sẽ tự động tạo ra nếu chưa tồn tại
		DB db = mongoClient.getDB("mydb");

		// Lấy ra Collection với ten ...
		// ko nhất thết collection phải tồn tại trong db
		DBCollection dept = db.getCollection("department");
		// Insery Doc
		BasicDBObject doc1 = new BasicDBObject();
		doc1.append("_id", 10);
		doc1.append("dept_no", "D10");
		doc1.append("dept_name", "ACCOUNTING");
		doc1.append("location", "NEW YORK");
		dept.insert(doc1);
		BasicDBObject doc2 = new BasicDBObject();
		doc2.append("_id", 20);
		doc2.append("dept_no", "D20");
		doc2.append("dept_name", "RESEARCH");
		doc2.append("description", "First department");
		dept.insert(doc2);
		System.out.println("Done!");
	}
}
