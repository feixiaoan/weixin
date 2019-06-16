package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.entity.Pxclass;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.PxclassMapper;
import com.gjl.weixin.utils.DataUtil;
import com.gjl.weixin.utils.R;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pxclass")
public class PxclassController {

    @Autowired
    private PxclassMapper pxclassMapper;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/findAllClass")
    public R findAllClass(String pageNum, String pageSize,Pxclass pxclass){
        if(pageNum == null){
            pageNum = "1";
        }
        if(pageSize == null){
            pageSize = "3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Pxclass> list = pxclassMapper.findAllClass(pxclass);
        PageInfo pageInfo = new PageInfo<Pxclass>(list, 3);

        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有培训班");
    }
    @RequestMapping("/findAll")
    public R findAll(String pageNum, String pageSize){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }

        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Pxclass> list = pxclassMapper.findAll();
        PageInfo pageInfo = new PageInfo<Pxclass>(list, 3);

        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有培训班");
    }
    @RequestMapping("/findClassById")
    public R findClassById(String id){

        List<Pxclass> list = pxclassMapper.findPxclassById(id);
        if(list.size()>0){
            return R.ok(list.get(0));
        }
        return R.error("没有根据此Id培训班");
    }
    @RequestMapping("/insert")
    public R insert(Pxclass pxclass){
        if(pxclass.getClassName().trim()== null
                || pxclass.getCategory().trim()==null
                || pxclass.getDay().trim()==null
                || pxclass.getNumber().trim()==null
                || pxclass.getProfessionPerson().trim()==null
                || pxclass.getClassPerson().trim()==null
                ||pxclass.getClassName().trim() == ""
                || pxclass.getCategory().trim()==""
                || pxclass.getDay().trim()==""
                || pxclass.getNumber().trim()==""
                || pxclass.getProfessionPerson().trim()==""
                || pxclass.getClassPerson().trim()==""){
            return R.error("有没有添加的内容，请重新填写");
        }
        if(pxclass.getStartTime() !=null&& pxclass.getEndTime() != null){
            Boolean isValidCreateTime = DataUtil.isValidDate(pxclass.getStartTime());
            Boolean isValidEndTime = DataUtil.isValidDate(pxclass.getEndTime());
            if(!isValidCreateTime||!isValidEndTime){
                return R.error("日期格式不对");
            }
            if(!DataUtil.isBefore(pxclass.getStartTime(),pxclass.getEndTime())){
                return R.error("开始时间在结束时间之前");
            }
            if(!pxclass.getDay() .equals(DataUtil.Cdate(pxclass.getEndTime(),pxclass.getStartTime()))){
                return R.error("日期天数计算错误");
            }
        }

        //Pxclass pxclass = new Pxclass();
        int i= pxclassMapper.insertSelective(pxclass);
        if(i>0){
            return R.ok("成功");
        }
        return R.error("插入失败");
    }
    @RequestMapping("/delete")
    public R delete(String id){
        logger.debug("进入 delete 方法");
        int list = pxclassMapper.deleteById(id);
        if(list>0){
            return R.ok(list);
        }
        return R.error("删除失败");
    }

    @RequestMapping("/save")
    public R save(Pxclass pxclass){
        if(pxclass.getClassName().trim()== null
                || pxclass.getCategory().trim()==null
                || pxclass.getDay().trim()==null
                || pxclass.getNumber().trim()==null
                || pxclass.getProfessionPerson().trim()==null
                || pxclass.getClassPerson().trim()==null
                ||pxclass.getClassName().trim() == ""
                || pxclass.getCategory().trim()==""
                || pxclass.getDay().trim()==""
                || pxclass.getNumber().trim()==""
                || pxclass.getProfessionPerson().trim()==""
                || pxclass.getClassPerson().trim()==""){
            return R.error("有没有添加的内容，请重新填写");
        }
        if(pxclass.getStartTime() !=null&& pxclass.getEndTime() != null){
            Boolean isValidCreateTime = DataUtil.isValidDate(pxclass.getStartTime());
            Boolean isValidEndTime = DataUtil.isValidDate(pxclass.getEndTime());
            if(!isValidCreateTime||!isValidEndTime){
                return R.error("日期格式不对");
            }
            if(!DataUtil.isBefore(pxclass.getStartTime(),pxclass.getEndTime())){
                return R.error("开始时间在结束时间之前");
            }
            if(!pxclass.getDay() .equals(DataUtil.Cdate(pxclass.getEndTime(),pxclass.getStartTime()))){
                return R.error("日期天数计算错误");
            }
        }
        logger.debug("进入 save 方法");
        int list = pxclassMapper.save(pxclass);
        if(list>0){
            return R.ok(list);
        }
        return R.error("更新失败");
    }


}
