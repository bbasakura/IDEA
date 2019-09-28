package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhProcessNum;

public interface INhProcessNumService {
    /**
     * 查询所有的NhProcessNum
     * @return
     */
    public List<NhProcessNum> getAllNhProcessNums();
    /**
     * 根据流程ID删除某条记录
     * @param processId
     */
    public void delNhProcessNum(NhProcessNum nhProcessNum);
    /**
     * 插入记录
     */
    public void insertNhProcessNum(NhProcessNum nhProcessNum);
    /**
     * 更新记录
     */
    public void updateNhProcessNum(NhProcessNum nhProcessNum);
    /**
     * 查询记录
     */
    public NhProcessNum getNhProcessNum(String processId);
}
