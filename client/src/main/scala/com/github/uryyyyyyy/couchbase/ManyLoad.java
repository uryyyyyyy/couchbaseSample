package com.github.uryyyyyyy.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class ManyLoad {

	public static void main(String[] args){
		CouchbaseCluster cluster = CouchbaseCluster.create("172.17.0.2");

		// Connect to the bucket and open it
		Bucket bucket = cluster.openBucket("default");

		setValues(bucket);

		// Read the document and print the "hello" field
		JsonDocument found = bucket.get("key" + 333);
		System.out.println(found.content().getString("hoge"));

		// Close all buckets and disconnect
		cluster.disconnect();
	}

	private static void setValues(Bucket bucket) {
		long start2 = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			JsonObject content = JsonObject.create().put("hoge", "wao" + i);
			bucket.upsert(JsonDocument.create("key" + 1, content));
		}
		System.out.println(System.currentTimeMillis() - start2 + " ms");
	}
}
