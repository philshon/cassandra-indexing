package com.hmsonline.cassandra.index.dao.impl;

import java.nio.ByteBuffer;

import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.utils.ByteBufferUtil;

import com.hmsonline.cassandra.index.dao.IndexDAO;

public class IndexDAOCassandra extends AbstractCassandraDAO implements IndexDAO {
  public static final String KEYSPACE = "Indexing";
  public static final String COLUMN_FAMILY = "Indexes";

  public IndexDAOCassandra() {
    super(KEYSPACE, COLUMN_FAMILY);
  }

  public void insertIndex(String indexName, ByteBuffer indexValue,
          ConsistencyLevel consistency) {
    try {
      insertColumn(ByteBufferUtil.bytes(indexName), indexValue,
              ByteBufferUtil.EMPTY_BYTE_BUFFER, consistency);
    }
    catch (Exception ex) {
      throw new RuntimeException("Failed to insert index: " + indexName + "["
              + indexValue + "]", ex);
    }
  }

  public void deleteIndex(String indexName, ByteBuffer column,
          ConsistencyLevel consistency) {
    try {
      deleteColumn(ByteBufferUtil.bytes(indexName), column, consistency);
    }
    catch (Exception ex) {
      throw new RuntimeException("Failed to delete index: " + indexName + "["
              + column + "]", ex);
    }
  }
}
