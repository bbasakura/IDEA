package pojo;

import org.apache.solr.client.solrj.beans.Field;

public class News {

    @Field
    private String id;
    @Field
    private String title;
    @Field
    private String content;
    //如果属性名字和字段的名称不一致,需要添加value属性让他对应
    @Field
    private String docurl;
    @Field
    private Long click;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }


    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", docurl='" + docurl + '\'' +
                ", click=" + click +
                '}';
    }
}
