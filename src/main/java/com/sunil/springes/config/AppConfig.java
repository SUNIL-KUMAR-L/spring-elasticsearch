package com.sunil.springes.config;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class AppConfig {

    @Bean
    public RestHighLevelClient getRestEsClient(Environment environment) {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(
                                environment.getProperty("elastic_search_host", "localhost"),
                                environment.getProperty("elastic_search_port",  Integer.class, 9200),
                                environment.getProperty("elastic_search_scheme" ,"http"))
                ));
    }

}
