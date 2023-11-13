package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyObject {
    // 定义你的数据类，包括category、q、a等字段
    private String category;
    private String q;
    private String a;

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("q")
    public String getQ() {
        return q;
    }

    @JsonProperty("q")
    public void setQ(String q) {
        this.q = q;
    }

    @JsonProperty("a")
    public String getA() {
        return a;
    }

    @JsonProperty("a")
    public void setA(String a) {
        this.a = a;
    }
}
