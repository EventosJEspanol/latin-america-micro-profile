package org.jespanol.conference.session;

import jakarta.nosql.document.DocumentCollectionManager;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
    public DocumentCollectionManager getColumn() throws IOException {
        Config config = new Config();
        final Elasticsearch elasticsearch = config.getCredential("elasticsearch", Elasticsearch::new);
        final RestHighLevelClient client = elasticsearch.get();
        ElasticsearchDocumentConfiguration configuration = new ElasticsearchDocumentConfiguration();
        final ElasticsearchDocumentCollectionManagerFactory factory = configuration.get(client);
        CreateIndexRequest request = new CreateIndexRequest(INDEX);

        GetIndexRequest exist = new GetIndexRequest();
        exist.indices(INDEX);
        if (!client.indices().exists(exist, RequestOptions.DEFAULT)) {
            client.indices().create(request, RequestOptions.DEFAULT);
        }
        return factory.get(INDEX);
    }
}
