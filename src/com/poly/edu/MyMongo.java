package com.poly.edu;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MyMongo {
	// thông tin host, port mongo
	private static final String host = "localhost";
	private static final int port = 27017;
	//
	private static final String db_name="mydb";
	private static final String username = "hieu2510";
	private static final String password = "1234";
	//Cách kết nối vào MongoDB không bắt buộc bảo mật
	public static MongoClient getMongoClient() throws UnknownHostException {
		MongoClient mongoClient =  new MongoClient(host, port);
		return mongoClient;
	}
	//Cách kết nối vào DB Mongo có bảo mật
	private static MongoClient getMongoClientByAccount() throws UnknownHostException {
		MongoCredential credential = MongoCredential.createMongoCRCredential(username, db_name, password.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(host, port),Arrays.asList(credential));
		return mongoClient;
	}
	public static void ping() throws UnknownHostException {
		MongoClient mongoClient =  getMongoClient();
		System.out.println("List all DB");
		//Danh sách các DB Names
		List<String> dbNames =  mongoClient.getDatabaseNames();
		for (String string : dbNames) {
			System.out.println("DB Names: "+string+"\n");
			
		}
		System.out.println("Done!");
	}
	public static void main(String[] args) throws UnknownHostException {
		ping();
	}
}
