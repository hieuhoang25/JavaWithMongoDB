package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class UpdateDemo {
public static void main(String[] args) throws UnknownHostException {
	MongoClient mongoClient = MyMongo.getMongoClient();
	DB db = mongoClient.getDB("mydb");
	DBCollection city = db.getCollection("city");
	
	DBObject whereClause = new BasicDBObject("city_no","WAS");
    DBObject values = new BasicDBObject();
    values.put("population", 1200000);
    values.put("description", "Pop 2014");
    values.put("note", "Document replaced!");
    //Thực hiện việt update
    WriteResult result = city.update(whereClause, values);
    int effectedCount = result.getN(); // số dòng đc update
    System.out.println("Effected Count: " + effectedCount);
    System.out.println("Done!");
    //=> Việc này sẽ thay thế tất cả các filed cũ thành filed mới chỉ trừ _id
}
}
