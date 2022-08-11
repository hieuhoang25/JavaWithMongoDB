package com.poly.edu;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class CreateDataForUpdate {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClien = MyMongo.getMongoClient();

		DB db = mongoClien.getDB("mydb");

		DBCollection city = db.getCollection("city");

		String[] cityNos = new String[] { "CHI", "NYO", "WAS" };
		String[] cityNames = new String[] { "Chicago", "New York", "Washington" };
		int[] populations = new int[] { 3000000, 8000000, 1000000 };
		String[] descriptions = new String[] { "Pop 2013", null, "Pop 2013" };
		// Trèn một vài Document vào Collection City.
	      for (int i = 0; i < 3; i++) {
	          BasicDBObject doc = new BasicDBObject();
	          doc.append("_id", i);
	          doc.append("city_no", cityNos[i]);
	          doc.append("city_name", cityNames[i]);
	          doc.append("population", populations[i]);
	          if (descriptions[i] != null) {
	              doc.append("description", descriptions[i]);
	          }
	          city.insert(doc);
	        
	      }

	      System.out.println("Done!");
	}
}
