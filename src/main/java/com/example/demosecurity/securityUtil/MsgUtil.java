package com.example.demosecurity.securityUtil;

import com.example.demosecurity.domain.Message;
import com.example.demosecurity.domain.SysRole;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MsgUtil {
    public static Message getCorrectMessage(Object object){
        return new Message(1, object,"请求成功");
    }
    public static Message getErrorMessage(){
        return new Message(0, null,"未知错误");
    }
}
