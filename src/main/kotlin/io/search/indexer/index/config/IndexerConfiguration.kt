package io.search.indexer.index.config

import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetAddress


@Configuration
class IndexerConfiguration(
        @Value("\${elasticsearch.clusterName}")
        private val esClusterName: String,

        @Value("\${elasticsearch.host}")
        private val esHost: String,

        @Value("\${elasticsearch.port}")
        private val esPort: Int
) {

    @Bean
    fun client(): Client {
        val esSettings = Settings.builder()
                .put("cluster.name", esClusterName)
                .build()
//        val transportClient = TransportClient.builder()
//                .settings(esSettings)
//                .build()
        val transportClient = PreBuiltTransportClient(esSettings)
        transportClient.addTransportAddress(
                InetSocketTransportAddress(InetAddress.getByName(esHost), esPort)
        )

        return transportClient
    }

}
