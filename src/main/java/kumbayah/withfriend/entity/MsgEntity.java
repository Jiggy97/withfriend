package kumbayah.withfriend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)  // JSON 직렬화 시, null 값을 자동 제외 시킴 | 직렬화란 객체를 JSON객체로 변환
public class MsgEntity {
    private String msg;
    private Object result;

    public MsgEntity(String msg, Object result) {
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public Object getResult() {
        return result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
