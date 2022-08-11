package com.poly.edu;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class GettingCollectionDemo {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = MyMongo.getMongoClient();
		
		DB db = mongoClient.getDB("mydb");
		//Collection trong MongoDB trương đương với Table trong csdl oracle, mysql, server, oracle
		DBCollection dept = db.getCollection("movie");
		
		System.out.println("Collection: "+ dept);
		
		//Số document
		//Chú ý: Document trong MongoDB tuongw ứng với 1 dòng dữ liệu quan hệ
		long rowCount = dept.count();
		System.out.println("Document count: " + rowCount);
		Set<String> collection = db.getCollectionNames();
		for (String string : collection) {
			System.out.println("Collection" + string );
		}
		
	}
}
