package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class UpdateWithIncDemo {
	//Sử dung $inc trong trượp hơp bn muốn tăng fias trị cho một cọt nào đốt
	//Chằng hạn tìm các city và update cot population lên 10000
public static void main(String[] args) throws UnknownHostException {
	MongoClient mongoClient = MyMongo.getMongoClient();
	DB db = mongoClient.getDB("mydb");
	DBCollection city = db.getCollection("city");
	DBObject whereClause = new BasicDBObject("city_no","NYO");
	DBObject values = new BasicDBObject();
	  values.put("population", 10000);
	  DBObject valuesWithInc = new BasicDBObject();
	  valuesWithInc.put("$inc", values);
	  // Thực hiện việc update.
	     WriteResult result = city.update(whereClause, valuesWithInc);
	     int effectedCount = result.getN();
	     System.out.println("Effected Count: " + effectedCount);
	     System.out.println("Done!");
}
}
