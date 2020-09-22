package com.ljs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    private  Object data;
    private String msg;
    private  int code;

    public JsonResult(String msg, int code) {
        this.msg = msg;
        this.code = code;
        data=null;/*处理404*/
    }





    @Override
    public String toString() {
        return "JsonReult{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
