package org.jespanol.session;

import org.elasticsearch.client.RestHighLevelClient;
import org.jnosql.diana.elasticsearch.document.ElasticsearchDocumentCollectionManager;
import org.jnosql.diana.elasticsearch.document.ElasticsearchDocumentCollectionManagerFactory;
import org.jnosql.diana.elasticsearch.document.ElasticsearchDocumentConfiguration;
import sh.platform.config.Config;
import sh.platform.config.Elasticsearch;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@ApplicationScoped
public class ManagerConfig {

    private static final String INDEX = "conference";

    @Produces
    @ApplicationScoped
    public ElasticsearchDocumentCollectionManager getColumn() throws IOException {
        Config config = new Config();
        final Elasticsearch elasticsearch = config.getCredential("elasticsearch", Elasticsearch::new);
        final RestHighLevelClient client = elasticsearch.get();
        ElasticsearchDocumentConfiguration configuration = new ElasticsearchDocumentConfiguration();
        final ElasticsearchDocumentCollectionManagerFactory factory = configuration.get(client);
        return factory.get(INDEX);
    }
}
