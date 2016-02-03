package com.github.uryyyyyyy.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class Main {

	public static void main(String[] args){
		CouchbaseCluster cluster = CouchbaseCluster.create("172.17.0.2");

		// Connect to the bucket and open it
		Bucket bucket = cluster.openBucket("default");

		// Create a JSON document and store it with the ID "helloworld"
		JsonObject content = JsonObject.create().put("hello", "world");
		bucket.upsert(JsonDocument.create("helloworld", content));

		// Read the document and print the "hello" field
		JsonDocument found = bucket.get("helloworld");
		System.out.println(found.content().getString("hello"));

		// Close all buckets and disconnect
		cluster.disconnect();
	}
}
