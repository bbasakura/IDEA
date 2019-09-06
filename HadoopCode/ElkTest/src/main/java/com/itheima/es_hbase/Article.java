package com.itheima.es_hbase;

/**
 * @ProjectName: ElkTest
 * @Package: com.itheima.es_hbase
 * @ClassName: Article excel中的数据的bean对象
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/18 0018 18:14
 * @Version: 1.0
 */
public class Article {

    private String id ;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", from='" + from + '\'' +
                ", time='" + time + '\'' +
                ", readCount='" + readCount + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    private String title;
    private String from;
    private String time;                //??
    private String readCount;            //??String
    private String content;


    public Article(String id, String title, String from, String time, String readCount, String content) {
        this.id = id;
        this.title = title;
        this.from = from;
        this.time = time;
        this.readCount = readCount;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
