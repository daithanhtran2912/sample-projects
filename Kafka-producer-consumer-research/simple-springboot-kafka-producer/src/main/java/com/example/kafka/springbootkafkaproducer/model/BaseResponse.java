package com.example.kafka.springbootkafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BaseResponse<T> implements Serializable {

    @JsonProperty
    private String status;
    @JsonProperty
    private String code;
    @JsonProperty
    private String total;
    @JsonProperty
    private List<T> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", total='" + total + '\'' +
                ", data=" + data +
                '}';
    }
}
