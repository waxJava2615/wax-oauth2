package com.wax.utils.enums;

/**
 * @author wax
 * @description: 基本默认返回码
 * @date 2022-01-05
 */
public enum ResultCode implements IResultCode {

    SUCCESS {
        @Override
        public String getMessage() {
            return "成功";
        }

        @Override
        public int getCode() {
            return 0;
        }
    },
    FAIL {
        @Override
        public String getMessage() {
            return "失败";
        }

        @Override
        public int getCode() {
            return 10000;
        }
    },
    REQUEST_URL_NOT_FOUND {
        @Override
        public String getMessage() {
            return "资源不存在";
        }

        @Override
        public int getCode() {
            return 10400;
        }
    },
    REQUEST_SERVER_ERROR {
        @Override
        public String getMessage() {
            return "网络异常,请稍后再试";
        }

        @Override
        public int getCode() {
            return 10500;
        }
    },
    ACCESS_DENIED_NO_PERMISSION {
        @Override
        public String getMessage() {
            return "您没有访问权限";
        }

        @Override
        public int getCode() {
            return 10800;
        }
    },
    REQUEST_SUPPORT_POST {
        @Override
        public String getMessage() {
            return "只支持POST提交";
        }

        @Override
        public int getCode() {
            return 20000;
        }
    },
    INVALID_DATA_ERROR {
        @Override
        public String getMessage() {
            return "无效的数据,请刷新页面在操作";
        }

        @Override
        public int getCode() {
            return 50000;
        }
    },
    USER_NOT_FOUND {
        /**
         * 消息
         *
         * @return
         */
        @Override
        public String getMessage() {
            return "用户不存在";
        }

        /**
         * 状态码
         *
         * @return
         */
        @Override
        public int getCode() {
            return 500404;
        }
    },
    USERNAME_OR_PASSWORD_ERROR {
        /**
         * 消息
         *
         * @return
         */
        @Override
        public String getMessage() {
            return "用户名或密码错误";
        }

        /**
         * 状态码
         *
         * @return
         */
        @Override
        public int getCode() {
            return 500501;
        }
    }

}
