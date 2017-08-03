package com.highill.practice.solr;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class SolrBase {

	/**
	 * HttpSolrClient
	 * 
	 * @param url
	 *            http://127.0.0.1:8983/solr/techproducts
	 * @return
	 */
	public static SolrClient httpSolrClient(String url) {
		SolrClient solrClient = new HttpSolrClient.Builder(url).build();
		return solrClient;
	}

	/**
	 * CloudSolrClient with ZooKeeper
	 * 
	 * @param zookeeperAddress
	 *            zkServerA:2181,zkServerB:2181,zkServerC:2181/solr
	 * @return
	 */
	public static SolrClient zooKeeperCloudSolrClient(String zookeeperAddress) {
		SolrClient solrClient = new CloudSolrClient.Builder().withZkHost(zookeeperAddress).build();
		return solrClient;
	}

	/**
	 * CloudSolrClient with url
	 * 
	 * @param url
	 *            http://127.0.0.1:8983/solr
	 * @return
	 */
	public static SolrClient urlCloudSolrClient(String url) {
		SolrClient solrClient = new CloudSolrClient.Builder().withSolrUrl(url).build();
		return solrClient;
	}

	public static UpdateResponse solrAddDocument(SolrClient solrClient, String collection, TreeMap<String, String> documentMap)
	        throws SolrServerException, IOException {
		UpdateResponse updateResponse = null;
		if (solrClient != null && collection != null && documentMap != null && !documentMap.isEmpty()) {
			SolrInputDocument inputDocument = new SolrInputDocument();
			for (String documentKey : documentMap.keySet()) {
				String documentValue = documentMap.get(documentKey);
				if (documentKey != null && documentValue != null) {
					inputDocument.addField(documentKey, documentValue);
				}
			}
			updateResponse = solrClient.add(collection, inputDocument);
			solrClient.commit(collection);
			System.out.println("-----solrClient collection: " + collection + " add document: " + inputDocument + " success. ");
		}
		return updateResponse;
	}

}
