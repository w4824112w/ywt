package gkyt.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by lyt38 on 2017/6/7.
 */
public enum ErrorEnums {
    SUCCESS(0, "成功"),
    SERVER_ERROR(-1, "服务器异常"),
    LOGIN_OVERTIME(1, "登陆超时"),
    PARAM_ERROR(99999, "参数错误");

    private int code;
    private String msg;


    private ErrorEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getResponseMsg(String code) {
        for (ErrorEnums responseInfo : ErrorEnums.values()) {
            if (code.equals(responseInfo.getCode())) {
                return responseInfo.getMsg();
            }
        }
        return SERVER_ERROR.getMsg();
    }

    public static JSONObject getResult(ErrorEnums error, String additionalMsg, Object data) {
        String msg = (additionalMsg == null ? "" : additionalMsg) + error.getMsg();
        JSONObject result = new JSONObject();
        result.put("code", error.getCode());
        result.put("msg", msg);
        if (data == null) {
            data = new JSONObject();
        }
        result.put("data", data);
        return result;
    }

}
