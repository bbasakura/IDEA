package cn.itcast.pojo;

public class User {
	public String user_id;							//用户ID
	public String user_name;						//用户登陆名
	public String user_sex;							//用户性别
	public String user_birthday;					//用户生日
	public String user_age;							//用户年龄
	public String constellation;					//用户星座
	public String province ;						//省份
	public String city;								//城市
	public String city_level;						//城市等级
	public String hex_mai;							//邮箱
	public String op_mail;							//邮箱运营商
	public String hex_phone;						//手机号
	public String fore_phone;						//手机前3位
	public String op_phone;							//手机运营商
	public String add_time;							//注册时间
	public String login_ip;							//登陆ip地址
	public String login_source;						//登陆来源
	public String request_user;						//邀请人
	public String total_mark;						//会员积分
	public String used_mark;						//已使用积分
	public String level_name;						//会员等级名称
	public String blacklist;						//用户黑名单
	public String is_married;						//婚姻状况
	public String education;						//学历
	public String monthly_money;					//收入
	public String profession;						//职业
	public String sex_model;						//性别模型
	public String is_pregnant_woman;				//是否孕妇
	public String is_have_children;					//是否有小孩
	public String children_sex_rate;				//孩子性别概率
	public String children_age_rate;				//孩子年龄概率
	public String is_have_car;						//是否有车
	public String potential_car_user_rate;			//潜在汽车用户概率
	public String phone_brand;						//使用手机品牌
	public String phone_brand_level;				//使用手机品牌档次
	public String phone_cnt;						//使用多少种不同的手机
	public String change_phone_rate;				//更换手机频率
	public String majia_flag;						//马甲标志
	public String majie_account_cnt;				//马甲账号数量
	public String loyal_model;						//用户忠诚度
	public String shopping_type_model;				//用户购物类型
	public String figure_model;						//身材
	public String stature_model;					//身高
	public String first_order_time;		    	   //第一次消费时间
	public String last_order_time;		    	   //最近一次消费时间
	public String first_order_ago;				   //首单距今时间
	public String last_order_ago;				   //尾单距今时间
	public String month1_hg_order_cnt;			   //近30天购买次数（不含退拒）
	public String month1_hg_order_amt;			   //近30天购买金额（不含退拒）
	public String month2_hg_order_cnt;			   //近60天购买次数（不含退拒）
	public String month2_hg_order_amt;			   //近60天购买金额（不含退拒）
	public String month3_hg_order_cnt;			   //近90天购买次数（不含退拒）
	public String month3_hg_order_amt;			   //近90天购买金额（不含退拒）
	public String month1_order_cnt;				   //近30天购买次数（含退拒）
	public String month1_order_amt;				   //近30天购买金额（含退拒）
	public String month2_order_cnt;				   //近60天购买次数（含退拒）
	public String month2_order_amt;				   //近60天购买金额（含退拒）
	public String month3_order_cnt;				   //近90天购买次数（含退拒）
	public String month3_order_amt;				   //近90天购买金额（含退拒）
	public String max_order_amt;				   //最大消费金额
	public String min_order_amt;				   //最小消费金额
	public String total_order_cnt;				   //累计消费次数（不含退拒）
	public String total_order_amt;				   //累计消费金额（不含退拒）
	public String user_avg_amt;			    	   //客单价（含退拒）
	public String month3_user_avg_amt;			   //近90天的客单价
	public String common_address;				   //常用收货地址
	public String common_paytype;				   //常用支付方式
	public String month1_cart_cnt;				   //近30天购物车的次数
	public String month1_cart_goods_cnt;		   //近30天购物车商品件数
	public String month1_cart_submit_cnt;		   //近30天购物车提交商品件数
	public String month1_cart_rate;		           //近30天购物车成功率
	public String month1_cart_cancle_cnt;		   //近30天购物车放弃件数
	public String return_cnt;					   //退货商品数量
	public String return_amt;					   //退货商品金额
	public String reject_cnt;					   //拒收商品数量
	public String reject_amt;					   //拒收商品金额
	public String last_return_time;		    	   //最近一次退货时间
	public String school_order_cnt;		    	   //学校下单总数
	public String company_order_cnt;			   //单位下单总数
	public String home_order_cnt;				   //家里下单总数
	public String forenoon_order_cnt;			   //上午下单总数
	public String afternoon_order_cnt;			   //下午下单总数
	public String night_order_cnt;				   //晚上下单总数
	public String morning_order_cnt;			   //凌晨下单总数
	public String first_category_id;               //一级分类ID
	public String first_category_name;             //一级分类名称
	public String second_category_id;              //二分类ID
	public String second_catery_name;              //二级分类名称
	public String third_category_id;               //三级分类ID
	public String third_category_name;             //三级分类名称
	public String month1_category_cnt;             //近30天购物类目次数
	public String month1_category_amt;             //近30天购物类目金额
	public String month3_category_cnt;             //近90天购物类目次数
	public String month3_category_amt;             //近90天购物类目金额
	public String month6_category_cnt;             //近180天购物类目次数
	public String month6_category_amt;             //近180天购物类目金额
	public String total_category_cnt;              //累计购物类目次数
	public String total_category_amt;              //累计购物类目次数
	public String month1_cart_category_cnt;        //近30天购物车类目次数
	public String month3_cart_category_cnt;        //近90天购物车类目次数
	public String month6_cart_category_cnt;        //近180天购物车类目次数
	public String total_cart_category_cnt;         //累计购物车类目次数
	public String last_category_time;              //最后一次购买类目时间
	public String last_category_ago;               //最后一次购买类目距今天数
	public String latest_pc_visit_date;            //最近一次PC端访问日期
	public String latest_app_visit_date;           //最近一次APP端访问日期
	public String latest_pc_visit_session;         //最近一次PC端访问的session
	public String latest_pc_cookies;               //最近一次PC端访问的cookies
	public String latest_pc_pv;                    //最近一次PC端访问的PV
	public String latest_pc_browser_name;          //最近一次PC端访问使用的游览器
	public String latest_pc_visit_os;              //最近一次PC端访问使用的操作系统
	public String latest_app_name;                 //最近一次APP端访问app名称
	public String latest_app_visit_os;             //最近一次APP端访问使用的操作系统
	public String latest_visit_ip;                 //最近一次访问IP(不分APP与PC)
	public String latest_city;                     //最近一次访问城市(不分APP与PC)
	public String latest_province;                 //最近一次访问省份(不分APP与PC)
	public String first_pc_visit_date;             //第一次PC端访问日期
	public String first_app_visit_date;            //第一次APP端访问日期
	public String first_pc_visit_session;          //第一次PC端访问的session
	public String first_pc_cookies;                //第一次PC端访问的cookies
	public String first_pc_pv;                     //第一次PC端访问的PV
	public String first_pc_browser_name;           //第一次PC端访问使用的游览器
	public String first_pc_visit_os;               //第一次PC端访问使用的操作系统
	public String first_app_name;                  //第一次APP端访问app名称
	public String first_app_visit_os;              //第一次APP端访问使用的操作系统
	public String first_visit_ip;                  //第一次访问IP(不分APP与PC)
	public String first_city;                      //第一次访问城市(不分APP与PC)
	public String first_province;                  //第一次访问省份(不分APP与PC)
	public String day7_app_cnt;                    //近7天APP端访问次数
	public String day15_app_cnt;                   //近15天APP端访问次数
	public String month1_app_cnt;                  //近30天APP端访问次数
	public String month2_app_cnt;                  //近60天APP端访问次数
	public String month3_app_cnt;                  //近90天APP端访问次数	
	public String day7_pc_cnt;                     //近7天PC端访问次数
	public String day15_pc_cnt;                    //近15天PC端访问次数
	public String month1_pc_cnt;                   //近30天PC端访问次数
	public String month2_pc_cnt;                   //近60天PC端访问次数
	public String month3_pc_cnt;                   //近90天PC端访问次数
	public String month1_pc_days;                  //近30天PC端访问天数
	public String month1_pc_pv;                    //近30天PC端访问PV
	public String month1_pc_avg_pv;                //近30天PC端访问平均PV
	public String month1_pc_diff_ip_cnt;           //近30天PC端访问不同ip数
	public String month1_pc_diff_cookie_cnt;       //近30天PC端访问不同的cookie数
	public String month1_pc_common_ip;             //近30天PC端访问最常用ip
	public String month1_pc_common_cookie;         //近30天PC端访问最常用的cookie
	public String month1_pc_common_browser_name;   //近30天PC端访问最常用游览器
	public String month1_pc_common_os;             //近30天PC端访问最常用的操作系统
	public String month1_hour025_cnt;              //近30天0-5点访问次数(不分PC与APP)
	public String month1_hour627_cnt;              //近30天6-7点访问次数(不分PC与APP)
	public String month1_hour829_cnt;              //近30天8-9点访问次数(不分PC与APP)
	public String month1_hour10212_cnt;            //近30天10-12点访问次数(不分PC与APP)
	public String month1_hour13214_cnt;            //近30天13-14点访问次数(不分PC与APP)
	public String month1_hour15217_cnt;            //近30天15-17点访问次数(不分PC与APP)
	public String month1_hour18219_cnt;            //近30天18-19点访问次数(不分PC与APP)
	public String month1_hour20221_cnt;            //近30天20-21点访问次数(不分PC与APP)
	public String month1_hour22223_cnt;            //近30天22-23点访问次数(不分PC与APP)
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_level() {
		return city_level;
	}
	public void setCity_level(String city_level) {
		this.city_level = city_level;
	}
	public String getHex_mai() {
		return hex_mai;
	}
	public void setHex_mai(String hex_mai) {
		this.hex_mai = hex_mai;
	}
	public String getOp_mail() {
		return op_mail;
	}
	public void setOp_mail(String op_mail) {
		this.op_mail = op_mail;
	}
	public String getHex_phone() {
		return hex_phone;
	}
	public void setHex_phone(String hex_phone) {
		this.hex_phone = hex_phone;
	}
	public String getFore_phone() {
		return fore_phone;
	}
	public void setFore_phone(String fore_phone) {
		this.fore_phone = fore_phone;
	}
	public String getOp_phone() {
		return op_phone;
	}
	public void setOp_phone(String op_phone) {
		this.op_phone = op_phone;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public String getLogin_source() {
		return login_source;
	}
	public void setLogin_source(String login_source) {
		this.login_source = login_source;
	}
	public String getRequest_user() {
		return request_user;
	}
	public void setRequest_user(String request_user) {
		this.request_user = request_user;
	}
	public String getTotal_mark() {
		return total_mark;
	}
	public void setTotal_mark(String total_mark) {
		this.total_mark = total_mark;
	}
	public String getUsed_mark() {
		return used_mark;
	}
	public void setUsed_mark(String used_mark) {
		this.used_mark = used_mark;
	}
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public String getIs_married() {
		return is_married;
	}
	public void setIs_married(String is_married) {
		this.is_married = is_married;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMonthly_money() {
		return monthly_money;
	}
	public void setMonthly_money(String monthly_money) {
		this.monthly_money = monthly_money;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getSex_model() {
		return sex_model;
	}
	public void setSex_model(String sex_model) {
		this.sex_model = sex_model;
	}
	public String getIs_pregnant_woman() {
		return is_pregnant_woman;
	}
	public void setIs_pregnant_woman(String is_pregnant_woman) {
		this.is_pregnant_woman = is_pregnant_woman;
	}
	public String getIs_have_children() {
		return is_have_children;
	}
	public void setIs_have_children(String is_have_children) {
		this.is_have_children = is_have_children;
	}
	public String getChildren_sex_rate() {
		return children_sex_rate;
	}
	public void setChildren_sex_rate(String children_sex_rate) {
		this.children_sex_rate = children_sex_rate;
	}
	public String getChildren_age_rate() {
		return children_age_rate;
	}
	public void setChildren_age_rate(String children_age_rate) {
		this.children_age_rate = children_age_rate;
	}
	public String getIs_have_car() {
		return is_have_car;
	}
	public void setIs_have_car(String is_have_car) {
		this.is_have_car = is_have_car;
	}
	public String getPotential_car_user_rate() {
		return potential_car_user_rate;
	}
	public void setPotential_car_user_rate(String potential_car_user_rate) {
		this.potential_car_user_rate = potential_car_user_rate;
	}
	public String getPhone_brand() {
		return phone_brand;
	}
	public void setPhone_brand(String phone_brand) {
		this.phone_brand = phone_brand;
	}
	public String getPhone_brand_level() {
		return phone_brand_level;
	}
	public void setPhone_brand_level(String phone_brand_level) {
		this.phone_brand_level = phone_brand_level;
	}
	public String getPhone_cnt() {
		return phone_cnt;
	}
	public void setPhone_cnt(String phone_cnt) {
		this.phone_cnt = phone_cnt;
	}
	public String getChange_phone_rate() {
		return change_phone_rate;
	}
	public void setChange_phone_rate(String change_phone_rate) {
		this.change_phone_rate = change_phone_rate;
	}
	public String getMajia_flag() {
		return majia_flag;
	}
	public void setMajia_flag(String majia_flag) {
		this.majia_flag = majia_flag;
	}
	public String getMajie_account_cnt() {
		return majie_account_cnt;
	}
	public void setMajie_account_cnt(String majie_account_cnt) {
		this.majie_account_cnt = majie_account_cnt;
	}
	public String getLoyal_model() {
		return loyal_model;
	}
	public void setLoyal_model(String loyal_model) {
		this.loyal_model = loyal_model;
	}
	public String getShopping_type_model() {
		return shopping_type_model;
	}
	public void setShopping_type_model(String shopping_type_model) {
		this.shopping_type_model = shopping_type_model;
	}
	public String getFigure_model() {
		return figure_model;
	}
	public void setFigure_model(String figure_model) {
		this.figure_model = figure_model;
	}
	public String getStature_model() {
		return stature_model;
	}
	public void setStature_model(String stature_model) {
		this.stature_model = stature_model;
	}
	public String getFirst_order_time() {
		return first_order_time;
	}
	public void setFirst_order_time(String first_order_time) {
		this.first_order_time = first_order_time;
	}
	public String getLast_order_time() {
		return last_order_time;
	}
	public void setLast_order_time(String last_order_time) {
		this.last_order_time = last_order_time;
	}
	public String getFirst_order_ago() {
		return first_order_ago;
	}
	public void setFirst_order_ago(String first_order_ago) {
		this.first_order_ago = first_order_ago;
	}
	public String getLast_order_ago() {
		return last_order_ago;
	}
	public void setLast_order_ago(String last_order_ago) {
		this.last_order_ago = last_order_ago;
	}
	public String getMonth1_hg_order_cnt() {
		return month1_hg_order_cnt;
	}
	public void setMonth1_hg_order_cnt(String month1_hg_order_cnt) {
		this.month1_hg_order_cnt = month1_hg_order_cnt;
	}
	public String getMonth1_hg_order_amt() {
		return month1_hg_order_amt;
	}
	public void setMonth1_hg_order_amt(String month1_hg_order_amt) {
		this.month1_hg_order_amt = month1_hg_order_amt;
	}
	public String getMonth2_hg_order_cnt() {
		return month2_hg_order_cnt;
	}
	public void setMonth2_hg_order_cnt(String month2_hg_order_cnt) {
		this.month2_hg_order_cnt = month2_hg_order_cnt;
	}
	public String getMonth2_hg_order_amt() {
		return month2_hg_order_amt;
	}
	public void setMonth2_hg_order_amt(String month2_hg_order_amt) {
		this.month2_hg_order_amt = month2_hg_order_amt;
	}
	public String getMonth3_hg_order_cnt() {
		return month3_hg_order_cnt;
	}
	public void setMonth3_hg_order_cnt(String month3_hg_order_cnt) {
		this.month3_hg_order_cnt = month3_hg_order_cnt;
	}
	public String getMonth3_hg_order_amt() {
		return month3_hg_order_amt;
	}
	public void setMonth3_hg_order_amt(String month3_hg_order_amt) {
		this.month3_hg_order_amt = month3_hg_order_amt;
	}
	public String getMonth1_order_cnt() {
		return month1_order_cnt;
	}
	public void setMonth1_order_cnt(String month1_order_cnt) {
		this.month1_order_cnt = month1_order_cnt;
	}
	public String getMonth1_order_amt() {
		return month1_order_amt;
	}
	public void setMonth1_order_amt(String month1_order_amt) {
		this.month1_order_amt = month1_order_amt;
	}
	public String getMonth2_order_cnt() {
		return month2_order_cnt;
	}
	public void setMonth2_order_cnt(String month2_order_cnt) {
		this.month2_order_cnt = month2_order_cnt;
	}
	public String getMonth2_order_amt() {
		return month2_order_amt;
	}
	public void setMonth2_order_amt(String month2_order_amt) {
		this.month2_order_amt = month2_order_amt;
	}
	public String getMonth3_order_cnt() {
		return month3_order_cnt;
	}
	public void setMonth3_order_cnt(String month3_order_cnt) {
		this.month3_order_cnt = month3_order_cnt;
	}
	public String getMonth3_order_amt() {
		return month3_order_amt;
	}
	public void setMonth3_order_amt(String month3_order_amt) {
		this.month3_order_amt = month3_order_amt;
	}
	public String getMax_order_amt() {
		return max_order_amt;
	}
	public void setMax_order_amt(String max_order_amt) {
		this.max_order_amt = max_order_amt;
	}
	public String getMin_order_amt() {
		return min_order_amt;
	}
	public void setMin_order_amt(String min_order_amt) {
		this.min_order_amt = min_order_amt;
	}
	public String getTotal_order_cnt() {
		return total_order_cnt;
	}
	public void setTotal_order_cnt(String total_order_cnt) {
		this.total_order_cnt = total_order_cnt;
	}
	public String getTotal_order_amt() {
		return total_order_amt;
	}
	public void setTotal_order_amt(String total_order_amt) {
		this.total_order_amt = total_order_amt;
	}
	public String getUser_avg_amt() {
		return user_avg_amt;
	}
	public void setUser_avg_amt(String user_avg_amt) {
		this.user_avg_amt = user_avg_amt;
	}
	public String getMonth3_user_avg_amt() {
		return month3_user_avg_amt;
	}
	public void setMonth3_user_avg_amt(String month3_user_avg_amt) {
		this.month3_user_avg_amt = month3_user_avg_amt;
	}
	public String getCommon_address() {
		return common_address;
	}
	public void setCommon_address(String common_address) {
		this.common_address = common_address;
	}
	public String getCommon_paytype() {
		return common_paytype;
	}
	public void setCommon_paytype(String common_paytype) {
		this.common_paytype = common_paytype;
	}
	public String getMonth1_cart_cnt() {
		return month1_cart_cnt;
	}
	public void setMonth1_cart_cnt(String month1_cart_cnt) {
		this.month1_cart_cnt = month1_cart_cnt;
	}
	public String getMonth1_cart_goods_cnt() {
		return month1_cart_goods_cnt;
	}
	public void setMonth1_cart_goods_cnt(String month1_cart_goods_cnt) {
		this.month1_cart_goods_cnt = month1_cart_goods_cnt;
	}
	public String getMonth1_cart_submit_cnt() {
		return month1_cart_submit_cnt;
	}
	public void setMonth1_cart_submit_cnt(String month1_cart_submit_cnt) {
		this.month1_cart_submit_cnt = month1_cart_submit_cnt;
	}
	public String getMonth1_cart_rate() {
		return month1_cart_rate;
	}
	public void setMonth1_cart_rate(String month1_cart_rate) {
		this.month1_cart_rate = month1_cart_rate;
	}
	public String getMonth1_cart_cancle_cnt() {
		return month1_cart_cancle_cnt;
	}
	public void setMonth1_cart_cancle_cnt(String month1_cart_cancle_cnt) {
		this.month1_cart_cancle_cnt = month1_cart_cancle_cnt;
	}
	public String getReturn_cnt() {
		return return_cnt;
	}
	public void setReturn_cnt(String return_cnt) {
		this.return_cnt = return_cnt;
	}
	public String getReturn_amt() {
		return return_amt;
	}
	public void setReturn_amt(String return_amt) {
		this.return_amt = return_amt;
	}
	public String getReject_cnt() {
		return reject_cnt;
	}
	public void setReject_cnt(String reject_cnt) {
		this.reject_cnt = reject_cnt;
	}
	public String getReject_amt() {
		return reject_amt;
	}
	public void setReject_amt(String reject_amt) {
		this.reject_amt = reject_amt;
	}
	public String getLast_return_time() {
		return last_return_time;
	}
	public void setLast_return_time(String last_return_time) {
		this.last_return_time = last_return_time;
	}
	public String getSchool_order_cnt() {
		return school_order_cnt;
	}
	public void setSchool_order_cnt(String school_order_cnt) {
		this.school_order_cnt = school_order_cnt;
	}
	public String getCompany_order_cnt() {
		return company_order_cnt;
	}
	public void setCompany_order_cnt(String company_order_cnt) {
		this.company_order_cnt = company_order_cnt;
	}
	public String getHome_order_cnt() {
		return home_order_cnt;
	}
	public void setHome_order_cnt(String home_order_cnt) {
		this.home_order_cnt = home_order_cnt;
	}
	public String getForenoon_order_cnt() {
		return forenoon_order_cnt;
	}
	public void setForenoon_order_cnt(String forenoon_order_cnt) {
		this.forenoon_order_cnt = forenoon_order_cnt;
	}
	public String getAfternoon_order_cnt() {
		return afternoon_order_cnt;
	}
	public void setAfternoon_order_cnt(String afternoon_order_cnt) {
		this.afternoon_order_cnt = afternoon_order_cnt;
	}
	public String getNight_order_cnt() {
		return night_order_cnt;
	}
	public void setNight_order_cnt(String night_order_cnt) {
		this.night_order_cnt = night_order_cnt;
	}
	public String getMorning_order_cnt() {
		return morning_order_cnt;
	}
	public void setMorning_order_cnt(String morning_order_cnt) {
		this.morning_order_cnt = morning_order_cnt;
	}
	public String getFirst_category_id() {
		return first_category_id;
	}
	public void setFirst_category_id(String first_category_id) {
		this.first_category_id = first_category_id;
	}
	public String getFirst_category_name() {
		return first_category_name;
	}
	public void setFirst_category_name(String first_category_name) {
		this.first_category_name = first_category_name;
	}
	public String getSecond_category_id() {
		return second_category_id;
	}
	public void setSecond_category_id(String second_category_id) {
		this.second_category_id = second_category_id;
	}
	public String getSecond_catery_name() {
		return second_catery_name;
	}
	public void setSecond_catery_name(String second_catery_name) {
		this.second_catery_name = second_catery_name;
	}
	public String getThird_category_id() {
		return third_category_id;
	}
	public void setThird_category_id(String third_category_id) {
		this.third_category_id = third_category_id;
	}
	public String getThird_category_name() {
		return third_category_name;
	}
	public void setThird_category_name(String third_category_name) {
		this.third_category_name = third_category_name;
	}
	public String getMonth1_category_cnt() {
		return month1_category_cnt;
	}
	public void setMonth1_category_cnt(String month1_category_cnt) {
		this.month1_category_cnt = month1_category_cnt;
	}
	public String getMonth1_category_amt() {
		return month1_category_amt;
	}
	public void setMonth1_category_amt(String month1_category_amt) {
		this.month1_category_amt = month1_category_amt;
	}
	public String getMonth3_category_cnt() {
		return month3_category_cnt;
	}
	public void setMonth3_category_cnt(String month3_category_cnt) {
		this.month3_category_cnt = month3_category_cnt;
	}
	public String getMonth3_category_amt() {
		return month3_category_amt;
	}
	public void setMonth3_category_amt(String month3_category_amt) {
		this.month3_category_amt = month3_category_amt;
	}
	public String getMonth6_category_cnt() {
		return month6_category_cnt;
	}
	public void setMonth6_category_cnt(String month6_category_cnt) {
		this.month6_category_cnt = month6_category_cnt;
	}
	public String getMonth6_category_amt() {
		return month6_category_amt;
	}
	public void setMonth6_category_amt(String month6_category_amt) {
		this.month6_category_amt = month6_category_amt;
	}
	public String getTotal_category_cnt() {
		return total_category_cnt;
	}
	public void setTotal_category_cnt(String total_category_cnt) {
		this.total_category_cnt = total_category_cnt;
	}
	public String getTotal_category_amt() {
		return total_category_amt;
	}
	public void setTotal_category_amt(String total_category_amt) {
		this.total_category_amt = total_category_amt;
	}
	public String getMonth1_cart_category_cnt() {
		return month1_cart_category_cnt;
	}
	public void setMonth1_cart_category_cnt(String month1_cart_category_cnt) {
		this.month1_cart_category_cnt = month1_cart_category_cnt;
	}
	public String getMonth3_cart_category_cnt() {
		return month3_cart_category_cnt;
	}
	public void setMonth3_cart_category_cnt(String month3_cart_category_cnt) {
		this.month3_cart_category_cnt = month3_cart_category_cnt;
	}
	public String getMonth6_cart_category_cnt() {
		return month6_cart_category_cnt;
	}
	public void setMonth6_cart_category_cnt(String month6_cart_category_cnt) {
		this.month6_cart_category_cnt = month6_cart_category_cnt;
	}
	public String getTotal_cart_category_cnt() {
		return total_cart_category_cnt;
	}
	public void setTotal_cart_category_cnt(String total_cart_category_cnt) {
		this.total_cart_category_cnt = total_cart_category_cnt;
	}
	public String getLast_category_time() {
		return last_category_time;
	}
	public void setLast_category_time(String last_category_time) {
		this.last_category_time = last_category_time;
	}
	public String getLast_category_ago() {
		return last_category_ago;
	}
	public void setLast_category_ago(String last_category_ago) {
		this.last_category_ago = last_category_ago;
	}
	public String getLatest_pc_visit_date() {
		return latest_pc_visit_date;
	}
	public void setLatest_pc_visit_date(String latest_pc_visit_date) {
		this.latest_pc_visit_date = latest_pc_visit_date;
	}
	public String getLatest_app_visit_date() {
		return latest_app_visit_date;
	}
	public void setLatest_app_visit_date(String latest_app_visit_date) {
		this.latest_app_visit_date = latest_app_visit_date;
	}
	public String getLatest_pc_visit_session() {
		return latest_pc_visit_session;
	}
	public void setLatest_pc_visit_session(String latest_pc_visit_session) {
		this.latest_pc_visit_session = latest_pc_visit_session;
	}
	public String getLatest_pc_cookies() {
		return latest_pc_cookies;
	}
	public void setLatest_pc_cookies(String latest_pc_cookies) {
		this.latest_pc_cookies = latest_pc_cookies;
	}
	public String getLatest_pc_pv() {
		return latest_pc_pv;
	}
	public void setLatest_pc_pv(String latest_pc_pv) {
		this.latest_pc_pv = latest_pc_pv;
	}
	public String getLatest_pc_browser_name() {
		return latest_pc_browser_name;
	}
	public void setLatest_pc_browser_name(String latest_pc_browser_name) {
		this.latest_pc_browser_name = latest_pc_browser_name;
	}
	public String getLatest_pc_visit_os() {
		return latest_pc_visit_os;
	}
	public void setLatest_pc_visit_os(String latest_pc_visit_os) {
		this.latest_pc_visit_os = latest_pc_visit_os;
	}
	public String getLatest_app_name() {
		return latest_app_name;
	}
	public void setLatest_app_name(String latest_app_name) {
		this.latest_app_name = latest_app_name;
	}
	public String getLatest_app_visit_os() {
		return latest_app_visit_os;
	}
	public void setLatest_app_visit_os(String latest_app_visit_os) {
		this.latest_app_visit_os = latest_app_visit_os;
	}
	public String getLatest_visit_ip() {
		return latest_visit_ip;
	}
	public void setLatest_visit_ip(String latest_visit_ip) {
		this.latest_visit_ip = latest_visit_ip;
	}
	public String getLatest_city() {
		return latest_city;
	}
	public void setLatest_city(String latest_city) {
		this.latest_city = latest_city;
	}
	public String getLatest_province() {
		return latest_province;
	}
	public void setLatest_province(String latest_province) {
		this.latest_province = latest_province;
	}
	public String getFirst_pc_visit_date() {
		return first_pc_visit_date;
	}
	public void setFirst_pc_visit_date(String first_pc_visit_date) {
		this.first_pc_visit_date = first_pc_visit_date;
	}
	public String getFirst_app_visit_date() {
		return first_app_visit_date;
	}
	public void setFirst_app_visit_date(String first_app_visit_date) {
		this.first_app_visit_date = first_app_visit_date;
	}
	public String getFirst_pc_visit_session() {
		return first_pc_visit_session;
	}
	public void setFirst_pc_visit_session(String first_pc_visit_session) {
		this.first_pc_visit_session = first_pc_visit_session;
	}
	public String getFirst_pc_cookies() {
		return first_pc_cookies;
	}
	public void setFirst_pc_cookies(String first_pc_cookies) {
		this.first_pc_cookies = first_pc_cookies;
	}
	public String getFirst_pc_pv() {
		return first_pc_pv;
	}
	public void setFirst_pc_pv(String first_pc_pv) {
		this.first_pc_pv = first_pc_pv;
	}
	public String getFirst_pc_browser_name() {
		return first_pc_browser_name;
	}
	public void setFirst_pc_browser_name(String first_pc_browser_name) {
		this.first_pc_browser_name = first_pc_browser_name;
	}
	public String getFirst_pc_visit_os() {
		return first_pc_visit_os;
	}
	public void setFirst_pc_visit_os(String first_pc_visit_os) {
		this.first_pc_visit_os = first_pc_visit_os;
	}
	public String getFirst_app_name() {
		return first_app_name;
	}
	public void setFirst_app_name(String first_app_name) {
		this.first_app_name = first_app_name;
	}
	public String getFirst_app_visit_os() {
		return first_app_visit_os;
	}
	public void setFirst_app_visit_os(String first_app_visit_os) {
		this.first_app_visit_os = first_app_visit_os;
	}
	public String getFirst_visit_ip() {
		return first_visit_ip;
	}
	public void setFirst_visit_ip(String first_visit_ip) {
		this.first_visit_ip = first_visit_ip;
	}
	public String getFirst_city() {
		return first_city;
	}
	public void setFirst_city(String first_city) {
		this.first_city = first_city;
	}
	public String getFirst_province() {
		return first_province;
	}
	public void setFirst_province(String first_province) {
		this.first_province = first_province;
	}
	public String getDay7_app_cnt() {
		return day7_app_cnt;
	}
	public void setDay7_app_cnt(String day7_app_cnt) {
		this.day7_app_cnt = day7_app_cnt;
	}
	public String getDay15_app_cnt() {
		return day15_app_cnt;
	}
	public void setDay15_app_cnt(String day15_app_cnt) {
		this.day15_app_cnt = day15_app_cnt;
	}
	public String getMonth1_app_cnt() {
		return month1_app_cnt;
	}
	public void setMonth1_app_cnt(String month1_app_cnt) {
		this.month1_app_cnt = month1_app_cnt;
	}
	public String getMonth2_app_cnt() {
		return month2_app_cnt;
	}
	public void setMonth2_app_cnt(String month2_app_cnt) {
		this.month2_app_cnt = month2_app_cnt;
	}
	public String getMonth3_app_cnt() {
		return month3_app_cnt;
	}
	public void setMonth3_app_cnt(String month3_app_cnt) {
		this.month3_app_cnt = month3_app_cnt;
	}
	public String getDay7_pc_cnt() {
		return day7_pc_cnt;
	}
	public void setDay7_pc_cnt(String day7_pc_cnt) {
		this.day7_pc_cnt = day7_pc_cnt;
	}
	public String getDay15_pc_cnt() {
		return day15_pc_cnt;
	}
	public void setDay15_pc_cnt(String day15_pc_cnt) {
		this.day15_pc_cnt = day15_pc_cnt;
	}
	public String getMonth1_pc_cnt() {
		return month1_pc_cnt;
	}
	public void setMonth1_pc_cnt(String month1_pc_cnt) {
		this.month1_pc_cnt = month1_pc_cnt;
	}
	public String getMonth2_pc_cnt() {
		return month2_pc_cnt;
	}
	public void setMonth2_pc_cnt(String month2_pc_cnt) {
		this.month2_pc_cnt = month2_pc_cnt;
	}
	public String getMonth3_pc_cnt() {
		return month3_pc_cnt;
	}
	public void setMonth3_pc_cnt(String month3_pc_cnt) {
		this.month3_pc_cnt = month3_pc_cnt;
	}
	public String getMonth1_pc_days() {
		return month1_pc_days;
	}
	public void setMonth1_pc_days(String month1_pc_days) {
		this.month1_pc_days = month1_pc_days;
	}
	public String getMonth1_pc_pv() {
		return month1_pc_pv;
	}
	public void setMonth1_pc_pv(String month1_pc_pv) {
		this.month1_pc_pv = month1_pc_pv;
	}
	public String getMonth1_pc_avg_pv() {
		return month1_pc_avg_pv;
	}
	public void setMonth1_pc_avg_pv(String month1_pc_avg_pv) {
		this.month1_pc_avg_pv = month1_pc_avg_pv;
	}
	public String getMonth1_pc_diff_ip_cnt() {
		return month1_pc_diff_ip_cnt;
	}
	public void setMonth1_pc_diff_ip_cnt(String month1_pc_diff_ip_cnt) {
		this.month1_pc_diff_ip_cnt = month1_pc_diff_ip_cnt;
	}
	public String getMonth1_pc_diff_cookie_cnt() {
		return month1_pc_diff_cookie_cnt;
	}
	public void setMonth1_pc_diff_cookie_cnt(String month1_pc_diff_cookie_cnt) {
		this.month1_pc_diff_cookie_cnt = month1_pc_diff_cookie_cnt;
	}
	public String getMonth1_pc_common_ip() {
		return month1_pc_common_ip;
	}
	public void setMonth1_pc_common_ip(String month1_pc_common_ip) {
		this.month1_pc_common_ip = month1_pc_common_ip;
	}
	public String getMonth1_pc_common_cookie() {
		return month1_pc_common_cookie;
	}
	public void setMonth1_pc_common_cookie(String month1_pc_common_cookie) {
		this.month1_pc_common_cookie = month1_pc_common_cookie;
	}
	public String getMonth1_pc_common_browser_name() {
		return month1_pc_common_browser_name;
	}
	public void setMonth1_pc_common_browser_name(String month1_pc_common_browser_name) {
		this.month1_pc_common_browser_name = month1_pc_common_browser_name;
	}
	public String getMonth1_pc_common_os() {
		return month1_pc_common_os;
	}
	public void setMonth1_pc_common_os(String month1_pc_common_os) {
		this.month1_pc_common_os = month1_pc_common_os;
	}
	public String getMonth1_hour025_cnt() {
		return month1_hour025_cnt;
	}
	public void setMonth1_hour025_cnt(String month1_hour025_cnt) {
		this.month1_hour025_cnt = month1_hour025_cnt;
	}
	public String getMonth1_hour627_cnt() {
		return month1_hour627_cnt;
	}
	public void setMonth1_hour627_cnt(String month1_hour627_cnt) {
		this.month1_hour627_cnt = month1_hour627_cnt;
	}
	public String getMonth1_hour829_cnt() {
		return month1_hour829_cnt;
	}
	public void setMonth1_hour829_cnt(String month1_hour829_cnt) {
		this.month1_hour829_cnt = month1_hour829_cnt;
	}
	public String getMonth1_hour10212_cnt() {
		return month1_hour10212_cnt;
	}
	public void setMonth1_hour10212_cnt(String month1_hour10212_cnt) {
		this.month1_hour10212_cnt = month1_hour10212_cnt;
	}
	public String getMonth1_hour13214_cnt() {
		return month1_hour13214_cnt;
	}
	public void setMonth1_hour13214_cnt(String month1_hour13214_cnt) {
		this.month1_hour13214_cnt = month1_hour13214_cnt;
	}
	public String getMonth1_hour15217_cnt() {
		return month1_hour15217_cnt;
	}
	public void setMonth1_hour15217_cnt(String month1_hour15217_cnt) {
		this.month1_hour15217_cnt = month1_hour15217_cnt;
	}
	public String getMonth1_hour18219_cnt() {
		return month1_hour18219_cnt;
	}
	public void setMonth1_hour18219_cnt(String month1_hour18219_cnt) {
		this.month1_hour18219_cnt = month1_hour18219_cnt;
	}
	public String getMonth1_hour20221_cnt() {
		return month1_hour20221_cnt;
	}
	public void setMonth1_hour20221_cnt(String month1_hour20221_cnt) {
		this.month1_hour20221_cnt = month1_hour20221_cnt;
	}
	public String getMonth1_hour22223_cnt() {
		return month1_hour22223_cnt;
	}
	public void setMonth1_hour22223_cnt(String month1_hour22223_cnt) {
		this.month1_hour22223_cnt = month1_hour22223_cnt;
	}


}
