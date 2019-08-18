package cn.itcast.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("hello")
public class Hello2Controller {


    @RequestMapping("show1")//localhost:9090/hello/show1.do
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc的第一个注解程序");
        mv.setViewName("hello");

        return mv;
    }

    @RequestMapping("show2")
    public ModelAndView test2222() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springm多福多寿的非官方个注解程序");
        mv.setViewName("hello");

        return mv;
    }


    //	?：通配一个字符
    @RequestMapping("/a?/show3")
    public ModelAndView test2() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc的ant风格映射:?");
        mv.setViewName("hello");

        return mv;
    }

    //*：单独使用的时候只能匹配1或多个,配合其他一个字符以上的时候,通配0个或者多个字符
    @RequestMapping("/a*/show4")
    public ModelAndView test3() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc的ant风格映射:*");
        mv.setViewName("hello");

        return mv;
    }


//**：通配0个或者多个路径, 配合其他一个字符以上的时候通配0或多个字符,而单独使用的时候,可以配置多个路径

    @RequestMapping("/a**/show5")
    public ModelAndView test4() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc的ant风格映射:**");
        mv.setViewName("hello");

        return mv;
    }

    //占位符风格映射

    @RequestMapping("show6/{username}/{age}")
    public ModelAndView test5(@PathVariable("username") String name, @PathVariable("age") Integer age) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc的占位符风格映射:" + name + ",," + age);
        mv.setViewName("hello");

        return mv;
    }

    @RequestMapping(value = "show7", method = RequestMethod.POST)
    public ModelAndView test7() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc请求路径之限定方法");
        mv.setViewName("hello");

        return mv;
    }


    @RequestMapping(value = "show8", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView test8() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc请求路径之限定方法POST+GET");
        mv.setViewName("hello");

        return mv;
    }


    @RequestMapping(value = "show9", params = "id")
    public ModelAndView test9() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之限定请求参数，ID");
        mv.setViewName("hello");

        return mv;
    }

    @RequestMapping(value = "show10", params = "!id")
    public ModelAndView test10() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之限定请求参数，！ID");
        mv.setViewName("hello");

        return mv;
    }


    @RequestMapping(value = "show11", params = "id=10")
    public ModelAndView test11() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之限定请求参数，ID=10,权限鉴定");
        mv.setViewName("hello");

        return mv;
    }

    @RequestMapping(value = "show12", params = "id>10")//此处不会进行比较，满足字符串“>10”后放开
    public ModelAndView test12() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之限定请求参数，ID>10,权限鉴定");
        mv.setViewName("hello");

        return mv;
    }

    @RequestMapping(value = "show13", params = {"id", "name"})//此处不会进行比较，满足字符串“>10”后放开
    public ModelAndView test13() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之限定请求参数,ID+name");
        mv.setViewName("hello");

        return mv;
    }

    @GetMapping(value = "show14")//此处不会进行比较，满足字符串“>10”后放开
    public ModelAndView test14() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之组合注解GetMapping");
        mv.setViewName("hello");

        return mv;
    }

    @PostMapping(value = "show15")//此处不会进行比较，满足字符串“>10”后放开
    public ModelAndView test15() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之组合注解PostMapping");
        mv.setViewName("hello");

        return mv;
    }

    @PutMapping(value = "show16")
    public ModelAndView test16() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之组合注解PutMapping");
        mv.setViewName("hello");

        return mv;
    }

    @DeleteMapping(value = "show17")
    public ModelAndView test17() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "springmvc映射之组合注解DeleteMapping");
        mv.setViewName("hello");

        return mv;
    }


    @RequestMapping(value = "show19/{name}")
    public String test19(Model model, @PathVariable("name") String name) {

        model.addAttribute("msg", "springmvc接收参数与数据绑定。占位符" + name);

        return "hello";
    }

    @RequestMapping(value = "show20")
    public String test20(Model model, @RequestParam(value = "name") String name) {

        model.addAttribute("msg", "springmvc接收普通参数请" + name);

        return "hello";
    }

    @RequestMapping(value = "show21")
    public String test21(Model model, @RequestParam(value = "name", required = false) String name) {

        model.addAttribute("msg", "springmvc接收普通参数请" + name);

        return "hello";
    }

    @RequestMapping(value = "show22")
    public String test22(Model model, @RequestParam(value = "name", defaultValue = "goodgg") String name) {

        model.addAttribute("msg", "springmvc接收普通参数请" + name);

        return "hello";
    }


    @RequestMapping(value = "show23")
    public String test23(Model model, @CookieValue("JSESSIONID") String cookie) {

        model.addAttribute("msg", "springmvc接收Cookie请求：" + cookie);

        return "hello";
    }


    @RequestMapping(value = "show24")
    public String test24(

            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            @RequestParam(value = "marry", defaultValue = "false") boolean isMarry,
            @RequestParam("income") double income,
            @RequestParam("interests") String[] interests
    ) {
        StringBuffer sb = new StringBuffer();

        sb.append("name" + name + "\r\n");
        sb.append("age" + age + "\r\n");
        sb.append("isMarry" + isMarry + "\r\n");
        sb.append("income" + income + "\r\n");
        sb.append("interests" + Arrays.toString(interests) + "\r\n");

        System.out.println("sb = " + sb);


        return "hello";
    }

    @RequestMapping(value = "show25")
    public String test25(Model model, User user) {

        model.addAttribute("msg", "springmvc通過pojo封装页面：" + user);

        return "hello";
    }


    @RequestMapping(value = "show26")
    public String test26(Model model, UserVO userVO) {

        model.addAttribute("msg", "springmvc通過pojo集合封装页面：" + userVO.getUsers());

        return "hello";
    }


    @RequestMapping(value = "show27")
    public String test27(Model model) {
        List<User> list = new ArrayList<User>();

        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(15 + i);
            user.setName("zajmgh" + i);

            user.setName("zhangdan" + i);
            list.add(user);
        }

        model.addAttribute("userList", "list");

        return "users";
    }

    @RequestMapping(value = "show28")
    @ResponseBody
    public List<User> test28() {
        List<User> users = new ArrayList<User>();

        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(15 + i);
            user.setName("zajmgh" + i);

            user.setName("zhangdan" + i);
            users.add(user);
        }

        return users;
    }

    @RequestMapping(value = "show29")
    public String test29(@RequestBody User user, Model model) {

        model.addAttribute("msg", user);

        return "hello";
    }


    @RequestMapping(value = "show30")
    @ResponseStatus(value = HttpStatus.OK)
    public void test30(@RequestParam("aaa") MultipartFile file) throws IOException {

        if (file != null) {
            file.transferTo(new File("d:/" + file.getOriginalFilename()));
        }

    }


    @RequestMapping(value = "show31")
    public String test31( Model model) {


        return "redirect:show33.do?type=redirect";
    }


    @RequestMapping(value = "show32")
    public String test32(Model model) {


        return "forward:show33.do?type=forward";
    }

    @RequestMapping(value = "show33")
    public String test33( Model model, @RequestParam("type") String type) {

        model.addAttribute("msg", type);

        return "hello";
    }


}
