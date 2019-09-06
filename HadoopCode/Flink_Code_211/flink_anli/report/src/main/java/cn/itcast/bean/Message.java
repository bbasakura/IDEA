package cn.itcast.bean;

/**
 *  2019/9/4
 */
public class Message {

    //发送条数
    private int count ;
    //发送消息
    private String message;
    //发送时间
    private Long timestamp;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "count=" + count +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
