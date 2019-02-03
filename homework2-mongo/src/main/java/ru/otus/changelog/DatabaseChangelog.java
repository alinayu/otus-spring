package ru.otus.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addRecords", author = "ayumakaeva")
    public void insert(DB db) {
        DBCollection myCollection = db.getCollection("books");
        BasicDBObject a1 = new BasicDBObject().append("lastName", "Пушкин").append("firstName", "Александр");
        BasicDBObject a2 = new BasicDBObject().append("lastName", "Гоголь").append("firstName", "Николай");
        BasicDBObject a3 = new BasicDBObject().append("lastName", "Крылов").append("firstName", "Иван");
        BasicDBObject g1 = new BasicDBObject().append("name", "Проза");
        BasicDBObject g2 = new BasicDBObject().append("name", "Стихотворение");
        BasicDBObject g3 = new BasicDBObject().append("name", "Поэма");
        BasicDBObject g4 = new BasicDBObject().append("name", "Басня");
        BasicDBObject c1 = new BasicDBObject().append("text", "Хорошо");
        BasicDBObject c4 = new BasicDBObject().append("text", "Интересно");

        BasicDBObject b1 = new BasicDBObject().append("_id", "1")
                .append("name", "Дубровский")
                .append("author", a1)
                .append("genre", g1)
                .append("comments", new BasicDBObject[]{c1});
        BasicDBObject b2 = new BasicDBObject().append("_id", "2")
                .append("name", "Пророк")
                .append("author", a1)
                .append("genre", g2);
        BasicDBObject b3 = new BasicDBObject().append("_id", "3")
                .append("name", "Мертвые души")
                .append("author", a2)
                .append("genre", g3);
        BasicDBObject b4 = new BasicDBObject().append("_id", "4")
                .append("name", "Ворона и лисица")
                .append("author", a3)
                .append("genre", g4)
                .append("comments", new BasicDBObject[]{c4});

        myCollection.insert(b1, b2, b3, b4);
    }
}
