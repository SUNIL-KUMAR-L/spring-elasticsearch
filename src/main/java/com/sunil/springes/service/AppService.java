package com.sunil.springes.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sunil.springes.model.Person;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppService {

    RestHighLevelClient client;
    ObjectMapper objectMapper = new ObjectMapper();

    public static  final String ES_INDEX = "person_index";
    public static  final String ES_TYPE = "_doc";

    public static  final String FIRST_NAME = "first_name";
    public static  final String LAST_NAME = "last_name";

    public AppService(RestHighLevelClient client) {
        this.client = client;
    }

    public Person addPerson(Person person) throws  Exception {

        IndexRequest indexRequest = new IndexRequest(ES_INDEX, ES_TYPE);
        indexRequest.id(person.getId());
        indexRequest.source(objectMapper.writeValueAsString(person), XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        return person;
    }

    public List<Person> getPersons() throws  Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        SearchRequest searchResult = buildPersonSearchRequest(ES_INDEX , ES_TYPE);
        searchResult.source(searchSourceBuilder);

        return getPersonListSearchResult(client.search(searchResult, RequestOptions.DEFAULT));
    }

    public Person getByID(String id) throws  Exception {
        GetRequest getRequest = new GetRequest(ES_INDEX , ES_TYPE, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        return objectMapper.convertValue(getResponse.getSource(), Person.class);
    }

    public List<Person> getPersonByFistNameAndLastName(String firstName, String lastName) throws Exception {

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("first_name", firstName))
                .must(QueryBuilders.matchQuery("last_name", lastName));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);

        SearchRequest searchResult = buildPersonSearchRequest(ES_INDEX , ES_TYPE);

        searchResult.source(searchSourceBuilder);

        try {
            final SearchResponse searchResponse = client.search(searchResult, RequestOptions.DEFAULT);
            return getPersonListSearchResult(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Person> getPersonBySearchAttributes(Map<String, String> searchParams) throws Exception {

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        searchParams.forEach((key, value) -> queryBuilder.must(QueryBuilders.matchQuery(key, value)));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);

        SearchRequest searchResult = buildPersonSearchRequest(ES_INDEX , ES_TYPE);

        searchResult.source(searchSourceBuilder);

        try {
            final SearchResponse searchResponse = client.search(searchResult, RequestOptions.DEFAULT);
            return getPersonListSearchResult(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Person> getPersonForAnySearchAttributes(Map<String, String> searchParams) throws Exception {

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        searchParams.forEach((key, value) -> queryBuilder.should(QueryBuilders.matchQuery(key, value)));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);

        SearchRequest searchResult = buildPersonSearchRequest(ES_INDEX , ES_TYPE);

        searchResult.source(searchSourceBuilder);

        try {
            final SearchResponse searchResponse = client.search(searchResult, RequestOptions.DEFAULT);
            return getPersonListSearchResult(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private List<Person> getPersonListSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Person> personDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit){
            personDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), Person.class));
        }

        return personDocuments;
    }

    private SearchRequest buildPersonSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }


}
