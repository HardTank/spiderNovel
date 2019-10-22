package com.spider.common;

public class Result {
    private Integer code;// 状态码
    private int number;// 条数
    private Boolean isSuccess;// 状态
    private String message;// 消息
    private Object result;// 数据对象

    /**
     * 无参构造器
     */
    public Result() {
        super();
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param statu
     * @param code
     * @param message
     */
    public Result(Boolean success, Integer code, String message, int number) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.message = message;
        this.number = number;
    }

    /**
     * 只返回状态，状态码，数据对象
     *
     * @param statu
     * @param code
     * @param object
     */
    public Result(Boolean success, Integer code, Object result, int number) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.result = result;
        this.number = number;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     *
     * @param statu
     * @param code
     * @param message
     * @param result
     */
    public Result(Boolean success, Integer code, String message, Object result, int number) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.message = message;
        this.result = result;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
