package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.cache.GlobalCache;
import com.gjl.weixin.entity.Pxclass;
import com.gjl.weixin.entity.ScheduledTask;
import com.gjl.weixin.entity.User;
import com.gjl.weixin.mapper.UserMapper;
import com.gjl.weixin.utils.MD5Util;
import com.gjl.weixin.utils.R;
import com.gjl.weixin.utils.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adminUser")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GlobalCache globalCache;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @RequestMapping("/qwe")
    public String test1(){
        return "indexx";
    }
    @RequestMapping("dept")
    public void test(){
        User user = new User();
        user.setLoginName("sdf");
        userMapper.insert(user);
        //System.out.println(list);
    }

    @RequestMapping("/save")
    public R save(User user, HttpServletRequest request){
        logger.debug("进入 save 方法");
        HttpSession session=request.getSession();
        List<User> all = userMapper.findAll();
        List<User> collect = all.stream().filter(x -> (
                String.valueOf(x.getId()).equals(user.getId().toString())
        )).collect(Collectors.toList());
        if(!user.getStatus().equals(collect.get(0).getStatus())){
            User userInfo =(User) session.getAttribute("userInfo");
            if(!userInfo.getLoginName().equals("111")){
                return R.error("您没有权限修改状态，请联系超级管理员");
            }
        }

        String str = MD5Util.getMD5Code(user.getPassword()+"guojinlong");
        user.setPassword(str);
        int list = userMapper.save(user);
        if(list>0){
            return R.ok(list);
        }
        return R.error("更新失败");
    }

    @RequestMapping("/findUserById")
    public R findUserById(String id){
        logger.debug("进入 findUserById 方法");
        List<User> all = userMapper.findAll();
        List<User> collect = all.stream().filter(x -> (
            String.valueOf(x.getId()).equals(id)
        )).collect(Collectors.toList());
        if(collect.size()>0){
            return R.ok(collect);
        }
        return R.error("查询失败");
    }
    @RequestMapping("/delete")
    public R delete(String id){
        logger.debug("进入 delete 方法");
        int list = userMapper.deleteById(id);
        if(list>0){
            return R.ok(list);
        }
        return R.error("删除失败");
    }
    @RequestMapping("findAllByCondition")
    public R findAllByCondition(String pageNum, String pageSize,User user){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<User> list = userMapper.findAllByCondition(user);
        PageInfo pageInfo = new PageInfo<User>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("用户不存在");
    }
    @RequestMapping("findAll")
    public R findAll(String pageNum, String pageSize){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<User> list = userMapper.findAll();
        PageInfo pageInfo = new PageInfo<User>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("用户不存在");
    }

    @RequestMapping("insert")
    public R insert(User user) {
        try{
           // SendMailUtil.SendMail("2645019356@qq.com","测试zhuti","测试neirong");
        }catch (Exception e){
            e.printStackTrace();
        }
        String str = MD5Util.getMD5Code(user.getPassword()+"guojinlong");
        user.setPassword(str);
        user.setCreateTime(new Date());
        user.setStatus("0");
        int i = userMapper.insertSelective(user);
        if(i>0){
            return R.ok();
        }
        return R.error("新增失败");
    }
    @PostMapping("/login")
    public R login(String userName, String password, HttpSession httpSession, HttpServletResponse response){
        logger.debug("进入 login 方法");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        String str = MD5Util.getMD5Code(password+"guojinlong");
        List<User> list=userMapper.login(userName,str);
        if(list.size()>0){
            globalCache.add("userInfo",list.get(0));
            httpSession.setAttribute("userInfo",list.get(0));
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }

    @GetMapping("/testGlobalCache")
    public R testGlobalCache(){
        System.out.println(globalCache.get("gjl"));
        globalCache.add("gjl","gjl");
        System.out.println(globalCache.get("gjl"));

        return R.ok();
    }

    public static void main(String[] args) {
        /*List<String> a=new ArrayList<String>();
        a.add("q");a.add("w");a.add("e");
        List<String> b=new ArrayList<String>();
        b.add("a");b.add("s");b.add("d");
        List<String> list=new ArrayList<String>();

        list=Stream.of(a,b).flatMap(s->s.stream()).sorted().collect(Collectors.toList());

        for(String item:list){
            System.out.println(item);
        }
*/


    }

}
