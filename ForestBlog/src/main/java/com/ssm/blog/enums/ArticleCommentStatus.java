package com.ssm.blog.enums;

/**
 * 文章评论状态
 */
public enum ArticleCommentStatus {

    ALLOW(1, "允许"),
    NOT_ALLOW(0, "不允许");

    private Integer value;

    private String message;

    ArticleCommentStatus(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
