package cn.itcast.b2c.gciantispider.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.service.INhIllegalOccFlightRankService;

@Controller
@RequestMapping("/occRate")
public class VisualizeOccRateController {
    
    private final static Logger logger = Logger.getLogger(VisualizeOccRateController.class.getName());
    
    @Autowired
    private INhIllegalOccFlightRankService nhIllegalOccFlightRankService;
    /**
     * 近一周爬虫非法占座排行
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/getNhIllegalOccFlightRank",method = RequestMethod.POST)
    @ResponseBody
    public Map getNhIllegalOccFlightRank(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,HttpServletRequest request){
        String pages = request.getParameter("pages");
        String rows = request.getParameter("rows");
        if(pages==null){
            pages="1";
        }
        if(rows==null){
            rows="20";
        }
        int pageSize = Integer.valueOf(rows);
        List seataList = new ArrayList();
        Map<String, Object> mapS = new HashMap<String, Object>();
        try{
            seataList = nhIllegalOccFlightRankService.getNhIllegalOccFlightRank(Integer.valueOf(pages),Integer.valueOf(rows),startDate,endDate);
            BigInteger pageAll = nhIllegalOccFlightRankService.getAllCount(startDate,endDate);
            Long pageA = pageAll.longValue();
            int pageCount = (int) (pageA % pageSize == 0 ? pageA / pageSize : pageA / pageSize + 1);
            mapS.put("seataList", seataList);
            mapS.put("pageCount", pageCount);
            System.out.println("pageCount : "+pageCount);
        }catch(Exception e){
            logger.info(e.getMessage());
        }
        return mapS;
    }

}
