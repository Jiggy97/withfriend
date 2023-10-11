package kumbayah.withfriend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MsgEntity {
    private boolean success;
    private String message;

    public MsgEntity(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter 및 Setter 메서드
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}






// 참고 코드
//@JsonInclude(JsonInclude.Include.NON_NULL)  // JSON 직렬화 시, null 값을 자동 제외 시킴 | 직렬화란 객체를 JSON객체로 변환
//public class MsgEntity {
//    private String msg;
//    private Object result;
//
//    public MsgEntity(String msg, Object result) {
//        this.msg = msg;
//        this.result = result;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public Object getResult() {
//        return result;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public void setResult(Object result) {
//        this.result = result;
//    }
//}
