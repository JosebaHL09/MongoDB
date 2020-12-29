/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dm2.mongoproiektua;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

/**
 *
 * @author Joseba
 */
public class Model {

    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    static MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .build();
    static MongoClient mongoClient = MongoClients.create(settings);

    static MongoDatabase database = mongoClient.getDatabase("gabonak").withCodecRegistry(pojoCodecRegistry);

    static MongoCollection<Student> collection = database.getCollection("students", Student.class);
    static Student student;
    static Consumer<Student> printBlock = new Consumer<Student>() {

        @Override
        public void accept(Student t) {
            System.out.println(t);
        }

    };

    public static void ikasleBerria(String izena, double an, double qn, double hn) {
        //id-a automatikoki egiten dugu erroreak ekiditzeko.
        Student myDoc = collection.find().sort(new Document("_id", -1)).first();
        int id = myDoc.getId() + 1;
        //Ikasle berria sortzen dugu
        List<Scores> scores = new ArrayList();

        Scores exam = new Scores(an, "exam");
        Scores quiz = new Scores(qn, "quiz");
        Scores hw = new Scores(hn, "homework");
        scores.add(exam);
        scores.add(quiz);
        scores.add(hw);
        Student student = new Student(id, izena, scores);
        collection.insertOne(student);
    }

    public static void ikasleakAlfabetikoki() {
        collection.find().sort(new Document("name", 1)).forEach(printBlock);
    }

    public static void notakOrdenatu() {
        //-1 jartzean 
        collection.find(eq("scores.type","exam")).sort(new Document("scores.score",-1)).forEach(printBlock);
    }

    public static void ikasleBakarra(int id) {

        Student student;
        student = collection.find(eq("_id", id)).first();
        //ikaslerik ez badago egiten dugu hau
        if (null == student) {
            System.out.println("Ikasle hori ez dago");
        } else {
            System.out.println(student);
        }
    }

    public static void notakAldatu(int id, String erabakia, double nota) {
        //Array-aren barruan sartzeko filter eta update erabili behar ditugu, tutorialeko orrian hau ez zen agertzen.
        Bson filter;
        Bson update;
        switch (erabakia) {
            case "azterketa":
                filter = and(eq("_id", id), eq("scores.type", "exam"));
                update = set("scores.$.score", nota);
                collection.updateOne(filter, update);
                System.out.println("Azterketako nota aldatu da!");
                break;
            case "quiz":
                filter = and(eq("_id", id), eq("scores.type", "quiz"));
                update = set("scores.$.score", nota);
                collection.updateOne(filter, update);
                System.out.println("Quiz-aren nota aldatu da!");
                break;
            case "etxekolanak":
                filter = and(eq("_id", id), eq("scores.type", "homework"));
                update = set("scores.$.score", nota);
                collection.updateOne(filter, update);
                System.out.println("Etxekolanen nota aldatu da!");
                break;
            default:
                System.out.println("Aukera hori ez dago");
                break;
        }
    }

    //Warning mezua agertzen da baina ondo ezabatzen ditu dokumentuak
    public static void deleteOne(int id) {
        student = collection.find(eq("_id", "id")).first();
        System.out.println(student);
        System.out.println("ezabatzen..... ok");
        collection.deleteOne(eq("_id", id));

    }

    public static void bistaratuGuztiak() {

        collection.find().forEach(printBlock);
    }

    /**
     * eq = equals gte = greater than equals and = biak beteko dituela &&
     */
    public static void gaindituak() {
        Bson filter;
        filter = and(gte("scores.score", 50));
        collection.find(filter).forEach(printBlock);
        /*
        switch (erabakia) {
            case "azterketa":
                filter = and(gte("scores.score", 50), eq("scores.type", "exam"));
                collection.find(filter).forEach(printBlock);
                break;
            case "quiz":
                filter = and(eq("scores.type", "quiz"), gte("scores.score", 50));
                collection.find(filter).forEach(printBlock);
                break;
            case "etxekolanak":
                filter = and(eq("scores.type", "homework"), gte("scores.score", 50));
                collection.find(filter).forEach(printBlock);
                break;
            default:
                System.out.println("Aukera hori ez dago");
                break;
        }*/
    }
}
