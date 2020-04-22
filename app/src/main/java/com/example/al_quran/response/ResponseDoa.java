package com.example.al_quran.response;

import com.example.al_quran.model.DoaModel;
import com.squareup.moshi.Json;

import java.util.List;

public class ResponseDoa {
    @Json(name = "status")
    Boolean status;
    @Json(name = "code")
    String code;
    @Json(name = "values")
    List<DoaModel> values;
    @Json(name = "message")
    String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DoaModel> getValues() {
        return values;
    }

    public void setValues(List<DoaModel> values) {
        this.values = values;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
