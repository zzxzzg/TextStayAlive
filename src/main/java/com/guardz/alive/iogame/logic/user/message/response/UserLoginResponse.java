package com.guardz.alive.iogame.logic.user.message.response;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * author: wyx
 * date: 2024/4/3
 * description:
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "socket.proto", filePackage = "pb.socket")
public class UserLoginResponse {
    Long userId;
    String message;
}
