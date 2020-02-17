package com.guoyun.student.utils;

public class StringUtil {
    /**
     * 验证数据是否为空
     * @param value 验证参数
     * @return 返回bool
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }
}
