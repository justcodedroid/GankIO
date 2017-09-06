package com.example.admin.gank.entity;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class BaseBean<T> {
    private boolean error;
    private T results;
    private int count;
    private List<String> category;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

}
