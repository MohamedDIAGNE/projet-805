package com.tracking.Database;


import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.tracking.Model.Activites;
import com.tracking.Model.Users;

public class DatabaseClass {
	
	
	/*-------------------   CONNECTION BASE DE DONNEES ------------------------------------------------------*/
	public DB Connection() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("tracking");
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		return db;
	}
	
	/*-----------------------   USER  -------------------------------------------*/
	
	public Boolean CreateUser(String login , String email , String password ) {
		Users user = createUser(login ,email ,password );
		DBObject userdoc = createDBObject(user);
		DB db = this.Connection();
		DBCollection collection = db.getCollection("Users");	
		//create user
		WriteResult result = collection.insert(userdoc);
		return true ;
	}
	
	
	public DBObject FindUser(String login , String password ) {
		DBObject user = null;
		DB db = this.Connection();
		DBCollection collection = db.getCollection("Users");
		DBObject query = BasicDBObjectBuilder.start().add("login", login).add("password", password).get();
		DBCursor cursor = collection.find(query);
		if(cursor.hasNext()){
			user = cursor.next();
			System.out.println(user);
		}
		return user;
	}
	
	private static Users createUser(String login , String email , String password ) {
		Users user = new Users();
		user.setLogin(login);
		user.setPassword(password);
		user.setEmail(email);
		return user;
	}
	
	private static DBObject createDBObject(Users user) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("_id", user.getId());
		docBuilder.append("login", user.getLogin());
		docBuilder.append("password", user.getPassword());
		docBuilder.append("email", user.getEmail());
		
		return docBuilder.get();
	}
	
	/*-------------------------------------ACTIVITE-----------------------------*/
	
	
	public Boolean CreateActivite(String nomactivite ,String iduser,Date date ) {
		Activites activite = createActivity( nomactivite , iduser, date  );
		DBObject activitedoc = createDBObject(activite);
		DB db = this.Connection();
		DBCollection collection = db.getCollection("Activites");	
		//create activity
		WriteResult result = collection.insert(activitedoc);
		return true ;
	}
	
	private static Activites createActivity(String nomactivite ,String iduser,Date date  ) {
		Activites activite = new Activites();
		activite.setNomActivite(nomactivite);
		activite.setIduser(iduser);
		activite.setDate(date);
		return activite;
	}
	
	private static DBObject createDBObject(Activites activite) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("_id", activite.getId());
		docBuilder.append("nomactivite", activite.getNomActivite());
		docBuilder.append("iduser", activite.getIduser());
		docBuilder.append("date", activite.getDate());
		
		return docBuilder.get();
	}
	
	public List<DBObject> FindActivities(String iduser) {
		List<DBObject> activites = null;
		DB db = this.Connection();
		DBCollection collection = db.getCollection("Activites");
		DBObject query = BasicDBObjectBuilder.start().add("iduser", iduser).get();
		DBCursor cursor = collection.find(query);
		activites = cursor.toArray();
		return activites;
	}

	
	
}
