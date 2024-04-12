package com.guardz.alive.iogame.logic.process.message.request;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * author: wyx
 * date: 2024/4/11
 * description:
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "socket.proto", filePackage = "pb.socket")
public class ActionRequest {
    /**
     * 命令code
     */
    public String payload;

    public String data;
}
