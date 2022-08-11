package com.poly.edu;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

//Tìm các department tương ứng sql
// where dept_name in ('Account',"Research") or location = "Boston"
public class QueryWithParamDemo3 {
	//Cách 1: 
	//Viết điều kien where theo cách thông thường
	//So vơi sử dụng  QueryBuilder cách này khó hơn
	//JSON
	// {"$or":[{"dept_name":{"$in":["ACCOUNTING","Research"]}},{"location" : "BOSTON"}]}
 public static DBObject getWhereClause1() {
	 List<String> list = new ArrayList<String>();
     list.add("ACCOUNTING");
     list.add("RESEARCH");
     //JSON  { "dept_name" : { "$in" : [ "ACCOUNTING" , "RESEARCH"]}
     BasicDBObjectBuilder ob1 = BasicDBObjectBuilder.start();
     ob1.push("dept_name").add("$in",list);
     DBObject clause1 = ob1.get();
     //JSON  {"location" : "BOSTON"}
     BasicDBObjectBuilder ob2 = BasicDBObjectBuilder.start();
     ob2.append("location", "BOSTON");
     DBObject clause2 = ob2.get(); // trả về json
     //Mảng JSON
     BasicDBList or = new BasicDBList();
     or.add(clause1);
     or.add(clause2);
     
     BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
     builder.add("$or", or);
     
     //JSON  {"$or":[{"dept_name":{"$in":["ACCOUNTING","Research"]}},{"location" : "BOSTON"}]}
     DBObject query = builder.get();
     
     System.out.println("Query = " + query);
     return query;
 }
 //Cách 2
 // Viết dk vơi where sử dụng Query Builder
 
 public static DBObject getWhereClause2() {
	 List<String> list = new ArrayList<String>();
     list.add("ACCOUNTING");
     list.add("RESEARCH");
    //Có thể viêt như thể này
     /*
     QueryBuilder queryBuilder = QueryBuilder.start();
     queryBuilder.or(QueryBuilder.start().put("dept_name").in(list).get(),
             QueryBuilder.start().put("location").is("BOSTON").get());

     DBObject query = queryBuilder.get();
     */
     QueryBuilder qb1 = new QueryBuilder();
     qb1.put("dept_name").in(list);
     DBObject q1 = qb1.get();
     
     QueryBuilder qb2 = new QueryBuilder();
     qb2.put("location").is("BOSTON");
     DBObject q2 = qb2.get();
     
     //
     QueryBuilder queryBuilder = QueryBuilder.start();
     queryBuilder.or(q1, q2);
     
     DBObject query = queryBuilder.get();
     return query;
 }
 
 public static void main(String[] args) throws java.net.UnknownHostException {
		MongoClient mongoClient = MyMongo.getMongoClient();
		DB db = mongoClient.getDB("mydb");
		DBCollection dept = db.getCollection("department");
		DBObject where = getWhereClause2();
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
}
