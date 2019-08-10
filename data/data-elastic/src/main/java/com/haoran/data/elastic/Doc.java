package com.haoran.data.elastic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author hr.han
 * @date 2019/6/13 19:08
 */

public interface Doc extends Serializable {
    String FIELD_INDEX = "index";
    String FIELD_TYPE = "type";
    String FIELD_ID = "id";
    String FIELD_TIMESTAMP = "timestamp";

    /**
     * get index
     * @return index
     */
    String getIndex();

    /**
     * get type
     * @return type
     */
    default String getType() {
        return "_doc";
    }

    /**
     * get id
     * @return id
     */
    String getId();

    /**
     * get timestamp
     * @return timestamp
     */
    default Long getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        long timestamp = now.getYear() * 1_00_00_00_00_00_000L;
        timestamp = timestamp + now.getMonthValue() * 1_00_00_00_00_000L;
        timestamp = timestamp + now.getDayOfMonth() * 1_00_00_00_000L;
        timestamp = timestamp + now.getHour() * 1_00_00_000L;
        timestamp = timestamp + now.getMinute() * 1_00_000L;
        timestamp = timestamp + now.getSecond() * 1_000L;
        timestamp = timestamp + now.getNano() / 1_000_000L;
        return timestamp;
    }

    /**
     * get source
     * @return source map
     */
    default Map<String, Object> getSourceMap() {
        return DefaultSourceMapBuilder.build(this);
    }
}
