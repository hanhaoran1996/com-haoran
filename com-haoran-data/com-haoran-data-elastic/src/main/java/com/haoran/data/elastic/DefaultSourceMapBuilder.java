package com.haoran.data.elastic;

import com.haoran.common.Constants;
import com.haoran.common.u.U4Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hr.han
 * @date 2019/6/15 15:47
 */

class DefaultSourceMapBuilder {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSourceMapBuilder.class);

    private static final Map<Class<?>, Map<String, Field>> CACHE = new ConcurrentHashMap<>();

    static <T extends Doc> Map<String, Object> build(T doc) {
        if (! CACHE.containsKey(doc.getClass())) {
            Map<String, Field> fieldMap = new HashMap<>(Constants.SIXTEEN);

            Class<?> temp = doc.getClass();
            while (! Object.class.equals(temp)) {
                Field[] fields = temp.getDeclaredFields();
                Arrays.stream(fields).forEach(field -> {
                    field.setAccessible(true);
                    DocField docField = field.getAnnotation(DocField.class);
                    if (U4Object.isNull(docField) || !docField.index()) {
                        return;
                    }
                    fieldMap.put(Constants.EMPTY.equals(docField.name()) ? field.getName() : docField.name(), field);
                });
                temp = temp.getSuperclass();
            }

            CACHE.put(doc.getClass(), fieldMap);
        }

        Map<String, Field> fieldMap = CACHE.get(doc.getClass());
        Map<String, Object> result = new HashMap<>(fieldMap.size() * 4 / 3 + 1);
        try {
            for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
                Field field = entry.getValue();
                Object val = field.get(doc);
                if (U4Object.nonNull(val)) {
                    result.put(entry.getKey(), val);
                }
            }
        } catch (IllegalAccessException e) {
            logger.warn(
                    "build SourceMap failed on [" + doc.getIndex() + "->" + doc.getType() + "->" + doc.getId() + "]",
                    e
            );
        }

        return result;
    }
}
