package com.guardz.alive.iogame.logic.process.message.response;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * author: wyx
 * date: 2024/4/7
 * description:
 */
@ToString
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "socket.proto", filePackage = "pb.socket")
public class TurnStatusMessage {
    /**
     * 0 不可操作 1 可操作
     */
    Integer status;
}
