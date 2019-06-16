package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.common.GlobalContext;
import com.gjl.weixin.common.GlobalError;
import com.gjl.weixin.entity.Pxclass;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.entity.SysParam;
import com.gjl.weixin.mapper.PxclassMapper;
import com.gjl.weixin.mapper.SysParamMapper;
import com.gjl.weixin.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysParam")
public class SysParamController {

    @Autowired
    private SysParamMapper sysParamMapper;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @RequestMapping("/findAllSysParam")
    public R findAllSysParam(String pageNum, String pageSize,SysParam sysParam){
        if(pageNum ==null){
            pageNum = "1";
        }
        if(pageSize == null){
            pageSize = "3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),3);
        List<SysParam> list = sysParamMapper.findAllSysParam(sysParam);
        PageInfo pageInfo = new PageInfo<SysParam>(list, 3);
        List<SysParam> list1 = pageInfo.getList();
        if(list1.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("查询错误");
    }

    @RequestMapping("/findSysParam")
    public R findSysParam(String pageNum, String pageSize, Model model){
        logger.debug("进入findSysParam() 方法");
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }

        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));

        List<SysParam> list=sysParamMapper.findSysParamAll();
        PageInfo pageInfo = new PageInfo<SysParam>(list, 3);

        List<SysParam> list1 = pageInfo.getList();
        if(list1.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("查询错误");
    }

    //根据用户id删除
    @GetMapping("/delete")
    public R deleteById(String id){
        logger.debug("进入 deleteById 方法");
        int list = sysParamMapper.deleteById(id);
        if(list>0){
            return R.ok(list);
        }
        return R.error("删除失败");
    }

    //更新用户
    @PostMapping("/save")
    public R save(SysParam sysParam){
        logger.debug("进入 save 方法");
        if(checkValidate(sysParam).getCode() == 1){
            return R.error(GlobalError.ERROR_SYSPARAM_VALUE);
        }
        int i  = sysParamMapper.save(sysParam);
        if(i>0){
            return R.ok(i);
        }
        return R.error("更新失败");
    }

    public R checkValidate(SysParam sysParam){

        if(GlobalContext.SYS_ADDACCOUNT_FLAG == sysParam.getSyscode()){
            if(sysParam.getSysvalue() != "1" && sysParam.getSysvalue() != "0"){
                return R.error(GlobalError.ERROR_SYSPARAM_VALUE);
            }
        }
        return R.ok();
    }

    @PostMapping("/findSysParamById")
    public R findSysParamById(String id){
        logger.debug("进入 findSysParamById 方法");
        List<SysParam> list = sysParamMapper.findSysParamById(id);
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error(GlobalError.ERROR_SYS_PARAM);
    }

    @RequestMapping("/findSysParamByCode")
    public R findSysParamByCode(String sysCode){
        logger.debug("进入 findSysParamById 方法");
        List<SysParam> list = sysParamMapper.findSysParamByCode(sysCode);
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error(GlobalError.ERROR_EVALUATE_DATA);
    }
}
