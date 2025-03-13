package org.bistu.web.consts;

/**
 * 判断字段是否为有效.
 *
 * @author chenjunfeng
 *
 */
public enum ValidEnum {
    VALID(1), INVALID(0);

    private int value;

    private ValidEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

