package com.gjl.weixin.controller;

import com.gjl.weixin.entity.AccessToken;
import com.gjl.weixin.entity.Article;
import com.gjl.weixin.entity.news.BaseNews;
import com.gjl.weixin.entity.news.Gkfnews;
import com.gjl.weixin.service.WeiXinService;
import com.gjl.weixin.utils.HttpUtil;
import com.gjl.weixin.utils.R;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.api.*;
import weixin.popular.bean.message.MessageSendResult;
import weixin.popular.bean.message.massmessage.Filter;
import weixin.popular.bean.message.massmessage.MassTextMessage;
import weixin.popular.bean.user.FollowResult;
import weixin.popular.bean.user.Group;
import weixin.popular.client.LocalHttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WeChatController {

    private static Logger logger = Logger.getLogger(WeChatController.class);
    @Autowired
    private WeiXinService weiXinService;
    /**
     * 获取access_token
     * access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */


    //获取access_token 接口
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
            + "grant_type=client_credential&appid=APPID&secret=APPSECRET";
    @RequestMapping("/getAccessToken")
    public static String  getAccessToken() {
        AccessToken accessToken = null;
        String appidreal="wx6f6892b83d45974f";
        String appsecretreal="93bd4af5a313e973d246869aa01d4692";
        String appid="wxd1a25bf4be87e6cb";
        String appsecret="98c66119ec6799fb89b0893b72c6271c";
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        //String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appidreal).replace("APPSECRET", appsecretreal);
        JSONObject jsonObject = HttpUtil.doGetstr(requestUrl);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                System.out.println("获取token失败");
                //logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        System.out.println("accessToken="+accessToken.getAccess_token());
        return accessToken.getAccess_token();
    }


    /**
     * 添加微信公众号菜单
     * @return
     */
    @Scheduled(fixedDelay = 10*60*1000)
    @RequestMapping(value="/menuAdd",method= RequestMethod.POST)
    @ResponseBody
    public String menuAdd(){
        boolean b = weiXinService.menuAdd();
        if (b) {
            logger.debug("更新底部菜单栏成功");
            return "success";
        }
        return "unsuccess";
    }

    /*@Scheduled(fixedDelay = 10*60*1000)
    @RequestMapping(value="/menuAdd2",method= RequestMethod.POST)
    public void menuAdd2(){
        Button sub1 = new Button();
        sub1.setType("view");
        sub1.setName("View Book");
        sub1.setUrl("http://www.baidu.com/");

        Button sub2 = new Button();
        sub2.setType("click");
        sub2.setName("Click");
        sub2.setKey("click-01");

        Button sub3 = new Button();
        sub3.setType("pic_weixin");
        sub3.setName("PIC");
        sub3.setKey("click-02");

        MenuButtons btn1 = new MenuButtons();
        btn1.setButton(new Button[]{sub1, sub2, sub3});

        String accessToken = AccessTokenThread.accessToken.getAccess_token();

        MenuAPI.menuCreate(accessToken, btn1);

        System.out.println("更新底部菜单栏成功");
    }*/

    //
    /*@RequestMapping(value="/access", method=RequestMethod.POST)
    public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response)throws Exception{
        logger.info("----------------开始处理微信发过来的消息------------------");
        // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        request.setCharacterEncoding("UTF-8");
        // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        response.setCharacterEncoding("UTF-8");
        String respXml = weixinCoreService.weixinMessageHandelCoreService(request, response);
        if (dataProcess.dataIsNull(respXml)){
            logger.error("-------------处理微信消息失败-----------------------");
            return null;
        }else {
            logger.info("----------返回微信消息处理结果-----------------------:"+respXml);
            return respXml;
        }
    }*/
    //根据用户输入被动推送消息
    @RequestMapping(value = "/weChatConnect",method = RequestMethod.POST)
    public void access(HttpServletRequest req,HttpServletResponse resp)throws IOException {
        req.setCharacterEncoding("UTF-8");
        String respMessage = weiXinService.processRequest(req);
        resp.setCharacterEncoding("UTF-8");
        if(respMessage==null){
            respMessage = "";
        }
        resp.getWriter().write(respMessage);
    }

    @RequestMapping(value = "/push",method = RequestMethod.POST)
    @ResponseBody
    public R push(String pushNew) {
        System.out.println(pushNew);
        String token=AccessTokenThread.accessToken.getAccess_token();
        List<String> list = WeChatController.getUser(null);
        // 获取分组ID
        Group group = UserAPI.groupsGet(token);
        String gid = group.getGroups().get(3).getId();

        //将所有用户放到All 组里面
        UserAPI.groupsMembersBatchUpdate(token,list,gid);
        //文本群发
        MassTextMessage textMessage = new MassTextMessage(pushNew);
        //设置分组
        textMessage.setFilter(new Filter(false,gid));
        //MessageAPI.messageMassSendall(token, textMessage);

        MessageSendResult messageSendResult = MessageAPI.messageMassSendall(token, textMessage);
        if(messageSendResult.getErrcode().equals("0")){
            return R.ok();
        }
        return R.error("发送未成功，请联系管理员");
    }

    @RequestMapping(value = "/pushNews",method = RequestMethod.POST)
    @ResponseBody
    public R pushNews() {
        String token=AccessTokenThread.accessToken.getAccess_token();
        /*List<String> list = weChatController.getUser(null);
        // 获取分组ID
        Group group = UserAPI.groupsGet(token);
        String gid = group.getGroups().get(3).getId();

        //将所有用户放到All 组里面
        UserAPI.groupsMembersBatchUpdate(token,list,"gid");*/
//        File file = new File("C:\\Users\\ODAACC\\Desktop\\pwd.png");
//        Media media = MediaAPI.mediaUpload(token, MediaType.image, file);
//
//        String media_id = media.getMedia_id();
//        MassImageMessage imageMessage = new MassImageMessage(media_id);
//        imageMessage.setTouser(new HashSet<String>());
//        imageMessage.getTouser().add("oH1rG05E7jSojVGkgKHEoIWmgCZA");
//        MessageAPI.messageMassSend(token,imageMessage);
        List<String> list = WeChatController.getUser(null);
        String getkfnews = getkfnews(list.get(0));
        System.out.println(getkfnews);
        String getkfnews1 = getkfnews(getkfnews);
        sendTest(token,getkfnews1);
        return R.ok(getkfnews);
    }

    public static MessageSendResult sendTest(String access_token, String messageJson) {
        Header jsonHeader = new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(jsonHeader).setUri("https://api.weixin.qq.com/cgi-bin/message/custom/send").addParameter("access_token", access_token).setEntity(new StringEntity(messageJson, Charset.forName("utf-8"))).build();
        return (MessageSendResult) LocalHttpClient.executeJsonResult(httpUriRequest, MessageSendResult.class);
    }





    public static String getkfnews(String openid) {
//先实例化图文内容
        Article article1 = new Article();
        article1.setTitle("紧急通知，不要捡这种钱！湛江都已经传疯了！\n");
        article1.setDescription("");
        article1.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
        article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=1&sn=8bb6ae54d6396c1faa9182a96f30b225&chksm=bd117e7f8a66f769dc886d38ca2d4e4e675c55e6a5e01e768b383f5859e09384e485da7bed98&scene=4#wechat_redirect");


        Article article2 = new Article();
        article2.setTitle("紧急通知，不要捡这种钱！湛江都已经传疯了！\n");
        article2.setDescription("");
        article2.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
        article2.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=1&sn=8bb6ae54d6396c1faa9182a96f30b225&chksm=bd117e7f8a66f769dc886d38ca2d4e4e675c55e6a5e01e768b383f5859e09384e485da7bed98&scene=4#wechat_redirect");
        List<Article> list = new ArrayList<Article>();
        Gkfnews news = new Gkfnews();
        list.add(article1);
        list.add(article2);
        news.setArticles(list);


        BaseNews kfbean = new BaseNews();
        kfbean.setMsgtype("news");
        kfbean.setTouser(openid);
        kfbean.setNews(news);
//对象转json 方式1
        String jsonkfbean = JSONObject.fromObject(kfbean).toString();
        System.out.println(jsonkfbean);
//方式2
       /* Gson gson=new Gson();
        String json = gson.toJson(kfbean);
        System.out.println(json);*/
        return jsonkfbean;
    }

    //创建分组
    @RequestMapping(value = "/groupsCreate",method = RequestMethod.POST)
    @ResponseBody
    public R groupsCreate() {
        Group all = UserAPI.groupsCreate(AccessTokenThread.accessToken.getAccess_token(), "All");
        return R.ok(all);
    }

    @RequestMapping(value = "/groupsGet",method = RequestMethod.POST)
    @ResponseBody
    public R groupsGet() {
        Group all = UserAPI.groupsGet(AccessTokenThread.accessToken.getAccess_token());

        return R.ok(all);
    }

    //获取关注用户列表
    @RequestMapping(value = "/user/get",method = RequestMethod.GET )
    @ResponseBody
    public static List<String>  getUser( String nextOpenId){
        /*String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=";
        String requestUrl=url.replace("ACCESS_TOKEN",AccessTokenThread.accessToken.getAccess_token());
        String str=HttpUtil.sendGet(requestUrl);
        JSONObject jsonObject = HttpUtil.doGetstr(requestUrl);
        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = JSONObject.fromObject(data);
        String openid = jsonObject1.getString("openid");
        WeixinUser weixinUser = (WeixinUser) JSONObject.toBean(jsonObject1, WeixinUser.class);
        List<String> list=weixinUser.getOpenid();
        for(String item:list){
            System.out.println(item);
        }
        return jsonObject1;*/
        FollowResult followResult = UserAPI.userGet(AccessTokenThread.accessToken.getAccess_token(), nextOpenId);
        String[] openid = followResult.getData().getOpenid();
        List<String> list = new ArrayList<>();
        for(String item:openid){
            list.add(item);
        }

        return list;
    }
}
