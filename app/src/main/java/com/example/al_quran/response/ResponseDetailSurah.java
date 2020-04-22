package com.example.al_quran.response;

import com.example.al_quran.model.SurahDetailModel;
import com.example.al_quran.model.SurahModel;
import com.squareup.moshi.Json;

import java.util.List;

public class ResponseDetailSurah {
    @Json(name = "status")
    Boolean status;
    @Json(name = "code")
    String code;
    @Json(name = "values")
    SurahDetailModel values;
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

    public SurahDetailModel getValues() {
        return values;
    }

    public void setValues(SurahDetailModel values) {
        this.values = values;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
