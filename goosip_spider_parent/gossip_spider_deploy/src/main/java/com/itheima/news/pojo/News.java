package com.itheima.news.pojo;

/**
 * @author itheima
 * @Title: News
 * @ProjectName goosip_spider_parent
 * @Description: 新闻数据的pojo
 * @date 2019/6/2915:02
 */
public class News {

    /**
     * 唯一标识id： String    分布式id
     */
    private String  id;

    //标题
    private String  title;

    //来源
    private String  source;

    //时间
    private String  time;

    //链接
    private String  url;

    //编辑
    private String  editor;

    //内容
    private String  content;


    //get和set方法


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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", editor='" + editor + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
