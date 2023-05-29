package com.wax.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author wax
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class ResultBase implements Serializable {

    public static final String CODE_FIELD = "code";
    public static final String MSG_FIELD = "msg";

    private int code;

    private String msg;


}
