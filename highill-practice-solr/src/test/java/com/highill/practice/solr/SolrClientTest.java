package com.highill.practice.solr;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class SolrClientTest {

	@Test
	public void solrClient() {
		String solrUrl = "http://192.168.1.101:8983/solr";
		SolrClient solrClient = SolrBase.urlCloudSolrClient(solrUrl);
		System.out.println("-----solrClient: " + solrClient);

		// $ /usr/local/solr-6.6.0/bin/solr create -c java_collection
		String collectionName = "java_collection";
		try {
			int recordSize = 10;
			for(int size = 1; size <= recordSize; size++) {
				TreeMap<String, String> documentMap = new TreeMap<String, String>();
				documentMap.put("id", String.valueOf(size + 10000));
				documentMap.put("name", "Name " + size);
				documentMap.put("price", String.valueOf(size + size / 2));
				if(size % 3 == 0) {
					documentMap.put("otherInfo", "This is " + size + " record. 方便测试 ");
				}
				SolrBase.solrAddDocument(solrClient, collectionName, documentMap);
			}
			
			SolrQuery solrQuery = new SolrQuery();
		
			// solrQuery.setRequestHandler("/demo");
			solrQuery.set("q", "测试");
			solrQuery.set("fl", "id, name,price, otherInfo, abcd");
			solrQuery.setHighlight(true);
			solrQuery.setFacetLimit(5);

			
			// QueryResponse queryResponse = solrClient.query(collectionName, solrQuery);
			QueryResponse queryResponse = solrClient.query(collectionName, solrQuery, METHOD.GET);
			SolrDocumentList queryDocumentList = queryResponse.getResults();
			
			System.out.println("-----queryResponse: " + queryResponse);
			queryDocumentList.forEach(queryDocument -> {
				Collection<String> fieldNameCollection = queryDocument.getFieldNames();
				Map<String, Object> fieldValueMap = queryDocument.getFieldValueMap();
				System.out.println("-----queryDocument: " + queryDocument);
				System.out.println("-----fieldNameCollection: " + fieldNameCollection);
				System.out.println("-----fieldValueMap: " + fieldValueMap);
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
