package com.cobble.takeaway.enumx;

public enum AcquireWxCardJsStatusEnum {
    SUCCESS("成功", "SUCCESS", 1), FAIL("失败", "FAIL", 0), CANCEL("取消", "CANCEL", 2);

    // 显示
    private String displayName;
    private String name;
    // 冗余
    private int index;

    AcquireWxCardJsStatusEnum(String displayName, String name, int index) {
        this.displayName = displayName;
        this.name = name;
        this.index = index;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static String getDisplayName(int index) {
        for (AcquireWxCardJsStatusEnum c : AcquireWxCardJsStatusEnum.values()) {
            if (c.getIndex() == index) {
                return c.displayName;
            }
        }
        return null;
    }

    public static String getDisplayName(String name) {
        for (AcquireWxCardJsStatusEnum c : AcquireWxCardJsStatusEnum.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.displayName;
            }
        }
        return null;
    }
}
