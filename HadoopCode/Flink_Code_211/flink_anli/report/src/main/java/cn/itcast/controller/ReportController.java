package cn.itcast.controller;

import cn.itcast.bean.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *2019/9/4
 */
@RestController
//@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "put",method = RequestMethod.POST)
//    @ResponseBody
    public void putData(@RequestBody String str, HttpServletResponse rsp) throws IOException {
        Message message = new Message();
        message.setCount(1);
        message.setMessage(str);
        message.setTimestamp(new Date().getTime());

        //将bean转换json字符串
        String jsonStr = JSON.toJSONString(message);
//        System.out.println(jsonStr);
        //获取kafka实例发送数据到kafka
        kafkaTemplate.send("pyg-0904-1","test",jsonStr);

        //发送响应状态给前端用户/或者是访问接口
        PrintWriter printWriter = write(rsp);
        printWriter.flush();
        printWriter.close();

    }

    private PrintWriter write(HttpServletResponse rsp) throws IOException {
        //设置响应头 json
        rsp.setContentType("application/json");
        //设置编码格式
        rsp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = new PrintWriter(rsp.getOutputStream());
        printWriter.write("send success");
        return printWriter;
    }

}
