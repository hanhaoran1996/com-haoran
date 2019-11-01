package com.haoran.data.elastic;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author hr.han
 * @date 2019/6/13 19:35
 */
public class BaseIndexer<T extends Doc> {

    protected static final TransportClient TC = CommonIndexer.TC;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public ActionFuture<IndexResponse> insert(T doc) {
        return CommonIndexer.insert(doc);
    }

    public void insert(T doc, ActionListener<IndexResponse> listener) {
        CommonIndexer.insert(doc, listener);
    }

    public ActionFuture<BulkResponse> insert(List<T> docs) {
        return CommonIndexer.insert(docs);
    }

    public void insert(List<T> docs, ActionListener<BulkResponse> listener) {
        CommonIndexer.insert(docs, listener);
    }

    public ActionFuture<UpdateResponse> update(T doc) {
        return CommonIndexer.update(doc);
    }

    public void update(T doc, ActionListener<UpdateResponse> listener) {
        CommonIndexer.update(doc, listener);
    }

    public ActionFuture<BulkResponse> update(List<T> docs) {
        return CommonIndexer.update(docs);
    }

    public void update(List<T> docs, ActionListener<BulkResponse> listener) {
        CommonIndexer.update(docs, listener);
    }

    public ActionFuture<UpdateResponse> upsert(T doc) {
        return CommonIndexer.upsert(doc);
    }

    public void upsert(T doc, ActionListener<UpdateResponse> listener) {
        CommonIndexer.upsert(doc, listener);
    }

    public ActionFuture<BulkResponse> upsert(List<T> docs) {
        return CommonIndexer.upsert(docs);
    }

    public void upsert(List<T> docs, ActionListener<BulkResponse> listener) {
        CommonIndexer.upsert(docs, listener);
    }

    public ActionFuture<DeleteResponse> delete(T doc) {
        return CommonIndexer.delete(doc);
    }

    public void delete(T doc, ActionListener<DeleteResponse> listener) {
        CommonIndexer.delete(doc, listener);
    }

    public ActionFuture<BulkResponse> delete(List<T> docs) {
        return CommonIndexer.delete(docs);
    }

    public void delete(List<T> docs, ActionListener<BulkResponse> listener) {
        CommonIndexer.delete(docs, listener);
    }
}
