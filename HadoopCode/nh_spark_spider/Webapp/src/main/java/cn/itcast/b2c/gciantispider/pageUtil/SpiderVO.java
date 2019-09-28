package cn.itcast.b2c.gciantispider.pageUtil;

public class SpiderVO {

    private Integer numspider;
    
    private Long sumspider;

    public Integer getNumspider() {
        return numspider;
    }

    public void setNumspider(Integer numspider) {
        this.numspider = numspider;
    }

    public Long getSumspider() {
        return sumspider;
    }

    public void setSumspider(Long sumspider) {
        this.sumspider = sumspider;
    }

    @Override
    public String toString() {
        return "SpiderVO [numspider=" + numspider + ", sumspider=" + sumspider + "]";
    }
    
}
