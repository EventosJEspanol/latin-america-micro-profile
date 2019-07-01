package org.jespanol.conference.conference;

import com.mongodb.MongoClient;
import jakarta.nosql.document.DocumentCollectionManager;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;
import sh.platform.config.Config;
import sh.platform.config.MongoDB;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MongoConfig {

    @Produces
    public DocumentCollectionManager getColumn() {
        Config config = new Config();
        final MongoDB mongo = config.getCredential("mongodb", MongoDB::new);
        final MongoClient mongoClient = mongo.get();
        MongoDBDocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        MongoDBDocumentCollectionManagerFactory factory = configuration.get(mongoClient);
        return factory.get("conferences");
    }
}
