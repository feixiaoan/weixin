package com.gjl.weixin.controller.export;


import ch.qos.logback.core.util.FileUtil;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.gjl.weixin.utils.ExportWordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExportWord {

    @Autowired
    private ExportWordUtil exportWordUtil;

    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response,int[][] array,String className){
        Map<String,Object> params = new HashMap<>();
        for(int i=0;i< array.length;i++){
            for(int j=0;j<array[i].length;j++){
                String str1="question"+String.valueOf(i+1)+"a";
                String str2="question"+String.valueOf(i+1)+"b";
                String str3="question"+String.valueOf(i+1)+"c";
                String str4="question"+String.valueOf(i+1)+"d";
                String str5="question"+String.valueOf(i+1)+"e";
                switch (j){
                    case 0:
                        params.put(str1,array[i][j]);break;
                    case 1:
                        params.put(str2,array[i][j]);break;
                    case 2:
                        params.put(str3,array[i][j]);break;
                    case 3:
                        params.put(str4,array[i][j]);break;
                    case 4:
                        params.put(str5,array[i][j]);break;

                }
            }
        }
        int mass = 0;
        int rear = 0;
        for(int i=0;i< 20;i++){
            if(i<17){
                mass += array[i][4];
            }
            if(i>=17){
                rear += array[i][4];
            }
        }

        params.put("className",className);
        params.put("mass",mass/17);
        params.put("rear",rear/3);
        params.put("general",mass*75/17/100+rear*15/3/100);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String countDate = simpleDateFormat.format(new Date());
        params.put("countDate",countDate);
        //这里是我说的一行代码
       // exportWordUtil.exportWord("C:/Users/ODAACC/Desktop/guojinlong.docx","C:/Users/ODAACC/Desktop","aaa.docx",params,request,response);
        String pathName = className+".docx";
        exportWordUtil.exportWord("word/培训反应评估数据统计表.docx","C:/Users/ODAACC/Desktop",pathName,params,request,response);

        //exportWordUtil.exportWord("word/培训反应评估数据统计表.docx","C:/Users/ODAACC/Desktop","bbb.docx",params,request,response);

        System.out.println("export成功");

        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
        //map.put("time", Format.format(new Date()));
        map.put("me","JueYue");
        map.put("date", "2015-01-03");
        map.put("title","rsg");
        map.put("name", "dfh");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "/word/guojinlong.docx", map);
            FileOutputStream fos = new FileOutputStream("D:/simple.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
