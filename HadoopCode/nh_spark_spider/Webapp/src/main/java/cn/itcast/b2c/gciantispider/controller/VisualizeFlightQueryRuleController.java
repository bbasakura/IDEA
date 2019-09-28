package cn.itcast.b2c.gciantispider.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhCrawlerQueryRoutesRank;
import cn.itcast.b2c.gciantispider.model.NhFlightQueryRule;
import cn.itcast.b2c.gciantispider.service.INhCrawlerQueryRoutesRankService;
import cn.itcast.b2c.gciantispider.service.INhFlightQueryRuleService;

/**
 *可视化航班查询爬取规律控制器
 */
@Controller
@RequestMapping("/filghtQuery")
public class VisualizeFlightQueryRuleController {

//    @SuppressWarnings("unused")
//    private final static Logger logger = Logger.getLogger(VisualizeFlightQueryRuleController.class.getName());
//    
    @Autowired
    private INhFlightQueryRuleService nhFlightQueryRuleService;
    
    @Autowired
    private INhCrawlerQueryRoutesRankService nhCrawlerQueryRoutesRankService;
    /**
     * 国内、国际单程查询爬取频次
     * @param time
     * @param request
     * @return
     */
    @RequestMapping(value="/findAllOneWayByTime",method=RequestMethod.POST)
    @ResponseBody
    public List<NhFlightQueryRule> findAllOneWayByTime(@RequestParam("startData")String startTime,@RequestParam("endDate")String endTime){
        return nhFlightQueryRuleService.findAllOneWayByTime(startTime,endTime);
    }
    /**
     * 国内、国际双程查询爬取频次
     * @param startTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/findTurnAroundByTime",method = RequestMethod.POST)
    @ResponseBody
    public List<NhFlightQueryRule> findTurnaroundByTime(@RequestParam("startData")String startTime,@RequestParam("endDate")String endTime){
        return nhFlightQueryRuleService.findTurnAroundByTime(startTime,endTime);
    }
    /**
     * 爬虫查询航线排行
     * @param request
     * @return
     */
    @RequestMapping(value="/getNhCrawlerQueryRoutesRank",method = RequestMethod.POST)
    @ResponseBody
    public List<NhCrawlerQueryRoutesRank> getNhCrawlerQueryRoutesRank(@RequestParam("startData")String startTime,@RequestParam("endDate")String endTime,HttpServletRequest request){
        return nhCrawlerQueryRoutesRankService.getNhCrawlerQueryRoutesRank(startTime, endTime);
    }
}
