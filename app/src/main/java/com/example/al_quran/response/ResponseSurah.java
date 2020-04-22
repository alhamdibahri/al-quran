package com.example.al_quran.response;

import com.example.al_quran.model.SurahModel;
import com.squareup.moshi.Json;

import java.util.List;

public class ResponseSurah {
    @Json(name = "status")
    Boolean status;
    @Json(name = "code")
    String code;
    @Json(name = "values")
    List<SurahModel> values;
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

    public List<SurahModel> getValues() {
        return values;
    }

    public void setValues(List<SurahModel> values) {
        this.values = values;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
