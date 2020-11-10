package com.snowy_samuume.tool.other;

public enum Status {
    //正常
    NORMAL(0),
    //回收或者停用
    RECYCLING_OR_DEACTIVATE(1),
    // 删除
    DELETE(2);

    private final int values;

    Status(int values) {
        this.values = values;
    }

    public int getValues() {
        return values;
    }
}
