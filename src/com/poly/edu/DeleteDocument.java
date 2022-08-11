package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DeleteDocument {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient  mongoClient = MyMongo.getMongoClient();
		DB db = mongoClient.getDB("mydb");
		DBCollection city = db.getCollection("city");
		city.remove(new BasicDBObject("city_no","CHI"));
		System.out.println("Done!");
	}
}
