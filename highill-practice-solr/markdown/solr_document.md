
# solr_document  

http://lucene.apache.org/solr/guide/6_6/using-solrj.html

maven 
http://mvnrepository.com/artifact/org.apache.solr/solr-solrj  


# Solr collection  

```  
$ /usr/local/solr-6.6.0/bin/solr create -c java_collection

Connecting to ZooKeeper at localhost:9983 ...
INFO  - 2017-08-03 15:36:18.200; org.apache.solr.client.solrj.impl.ZkClientClusterStateProvider; Cluster at localhost:9983 ready
Uploading /usr/local/solr-6.6.0/server/solr/configsets/data_driven_schema_configs/conf for config java_collection to ZooKeeper at localhost:9983

Creating new collection 'java_collection' using command:
http://localhost:8983/solr/admin/collections?action=CREATE&name=java_collection&numShards=1&replicationFactor=1&maxShardsPerNode=1&collection.configName=java_collection

{
  "responseHeader":{
    "status":0,
    "QTime":4410},
  "success":{"192.168.1.101:8983_solr":{
      "responseHeader":{
        "status":0,
        "QTime":3249},
      "core":"java_collection_shard1_replica1"}}}
      
```  

# Solr配置文件 
```  
$ cat /usr/local/solr-6.6.0/server/solr/java_collection_shard1_replica1/core.properties
$ cat /usr/local/solr-6.6.0/server/solr/demo_shard1_replica1/core.properties

$ cat  /usr/local/solr-6.6.0/server/solr/solr.xml


```  


