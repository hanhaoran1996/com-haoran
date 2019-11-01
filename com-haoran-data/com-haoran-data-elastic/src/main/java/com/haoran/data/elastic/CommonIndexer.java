package com.haoran.data.elastic;

import com.haoran.common.u.U4Object;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.util.List;

/**
 * @author hr.han
 * @date 2019/6/14 13:53
 */
class CommonIndexer {
    private CommonIndexer() {}

    static final TransportClient TC = ElasticClientLoader.load();

    static <T extends Doc> ActionFuture<IndexResponse> insert(T doc) {
        return TC.index(buildIndexRequest(doc));
    }

    static <T extends Doc> void insert(T doc, ActionListener<IndexResponse> listener) {
        TC.index(buildIndexRequest(doc), listener);
    }

    static <T extends Doc> ActionFuture<BulkResponse> insert(List<T> docs) {
        return TC.bulk(buildIndexBulkRequest(docs));
    }

    static <T extends Doc> void insert(List<T> docs, ActionListener<BulkResponse> listener) {
        TC.bulk(buildIndexBulkRequest(docs), listener);
    }

    private static <T extends Doc> BulkRequest buildIndexBulkRequest(List<T> docs) {
        final BulkRequest request = new BulkRequest();
        docs.forEach(doc -> {
            if (U4Object.nonNull(doc)) {
                request.add(buildIndexRequest(doc));
            }
        });
        return request;
    }

    private static <T extends Doc> IndexRequest buildIndexRequest(T doc) {
        doc.getSourceMap().put(Doc.FIELD_TIMESTAMP, doc.getTimestamp());
        return new IndexRequest()
                .index(doc.getIndex())
                .type(doc.getType())
                .id(doc.getId())
                .source(doc.getSourceMap());
    }

    static <T extends Doc> ActionFuture<UpdateResponse> update(T doc) {
        return TC.update(buildUpdateRequest(doc, false));
    }

    static <T extends Doc> void update(T doc, ActionListener<UpdateResponse> listener) {
        TC.update(buildUpdateRequest(doc, false), listener);
    }

    static <T extends Doc> ActionFuture<BulkResponse> update(List<T> docs) {
        return TC.bulk(buildUpdateBulkRequest(docs, false));
    }

    static <T extends Doc> void update(List<T> docs, ActionListener<BulkResponse> listener) {
        TC.bulk(buildUpdateBulkRequest(docs, false), listener);
    }

    static <T extends Doc> ActionFuture<UpdateResponse> upsert(T doc) {
        return TC.update(buildUpdateRequest(doc, true));
    }

    static <T extends Doc> void upsert(T doc, ActionListener<UpdateResponse> listener) {
        TC.update(buildUpdateRequest(doc, true), listener);
    }

    static <T extends Doc> ActionFuture<BulkResponse> upsert(List<T> docs) {
        return TC.bulk(buildUpdateBulkRequest(docs, true));
    }

    static <T extends Doc> void upsert(List<T> docs, ActionListener<BulkResponse> listener) {
        TC.bulk(buildUpdateBulkRequest(docs, true), listener);
    }

    private static <T extends Doc> BulkRequest buildUpdateBulkRequest(List<T> docs, boolean upsert) {
        final BulkRequest request = new BulkRequest();
        docs.forEach(doc -> {
            if (U4Object.nonNull(doc)) {
                request.add(buildUpdateRequest(doc, upsert));
            }
        });
        return request;
    }

    private static <T extends Doc>  UpdateRequest buildUpdateRequest(T doc, boolean upsert) {
        doc.getSourceMap().put(Doc.FIELD_TIMESTAMP, doc.getTimestamp());
        return new UpdateRequest()
                .index(doc.getIndex())
                .type(doc.getType())
                .id(doc.getId())
                .doc(doc.getSourceMap())
                .docAsUpsert(upsert);
    }

    static <T extends Doc> ActionFuture<DeleteResponse> delete(T doc) {
        return TC.delete(buildDeleteRequest(doc));
    }

    static <T extends Doc> void delete(T doc, ActionListener<DeleteResponse> listener) {
        TC.delete(buildDeleteRequest(doc), listener);
    }

    static <T extends Doc> ActionFuture<BulkResponse> delete(List<T> docs) {
        return TC.bulk(buildDeleteBulkRequest(docs));
    }

    static <T extends Doc> void delete(List<T> docs, ActionListener<BulkResponse> listener) {
        TC.bulk(buildDeleteBulkRequest(docs), listener);
    }

    private static <T extends Doc> BulkRequest buildDeleteBulkRequest(List<T> docs) {
        final BulkRequest request = new BulkRequest();
        docs.forEach(doc -> {
            if (U4Object.nonNull(doc)) {
                request.add(buildDeleteRequest(doc));
            }
        });
        return request;
    }

    private static <T extends Doc> DeleteRequest buildDeleteRequest(T doc) {
        return new DeleteRequest()
                .index(doc.getIndex())
                .type(doc.getType())
                .id(doc.getId());
    }
}
