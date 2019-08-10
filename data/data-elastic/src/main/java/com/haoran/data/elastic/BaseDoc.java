package com.haoran.data.elastic;

/**
 * @author hr.han
 * @date 2019/6/13 19:14
 */

public abstract class BaseDoc implements Doc {
    @DocField(index = false)
    protected String id;

    public BaseDoc(Object id) {
        this.id = String.valueOf(id);
    }

    /**
     * get index
     * @return index
     */
    @Override
    public abstract String getIndex();

    @Override
    public String getId() {
        return id;
    }
}
