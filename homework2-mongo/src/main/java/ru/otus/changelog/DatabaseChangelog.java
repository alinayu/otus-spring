package ru.otus.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DatabaseChangelog {

//    @ChangeSet(order = "001", id = "addAuthors", author = "ayumakaeva")
//    public void insertAuthors(DB db) {
//        DBCollection myCollection = db.getCollection("authors");
//        BasicDBObject a1 = new BasicDBObject().append("lastName", "Пушкин").append("firstName", "Александр");
//        BasicDBObject a2 = new BasicDBObject().append("lastName", "Толстой").append("firstName", "Лев");
//        BasicDBObject a3 = new BasicDBObject().append("lastName", "Макконнелл").append("firstName", "Стив");
//        BasicDBObject a4 = new BasicDBObject().append("lastName", "Мартин").append("firstName", "Роберт");
//        myCollection.insert(a1, a2, a3, a4);
//    }
}
