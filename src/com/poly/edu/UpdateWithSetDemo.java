package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class UpdateWithSetDemo {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mc = MyMongo.getMongoClient();
		DB db = mc.getDB("mydb");
		DBCollection city = db.getCollection("city");
		DBObject whereClause = new BasicDBObject("city_no","CHI");
		
		DBObject values = new BasicDBObject();
		values.put("population", 3400000);
	    values.put("description", "Pop 2014");
	    values.put("note", "Document update with $set");
	    
	    DBObject valuesWithSet = new BasicDBObject();
	    valuesWithSet.put("$set", values);
	    
	 // Thực hiện việc update.
	      WriteResult result = city.update(whereClause, valuesWithSet);
	     
	      int effectedCount = result.getN();
	      System.out.println("Effected Count: " + effectedCount);
	      System.out.println("Done!");
		
	}
}
