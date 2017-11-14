package com.choice.controller;

import com.choice.common.ServerResponse;
import com.choice.entity.DishCatelog;
import com.choice.service.DishCatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("dishCatelog")
public class DishCatelogController {

    @Autowired
    public DishCatelogService dishCatelogService;
    @RequestMapping("/")
    @ResponseBody
    public ServerResponse queryAllDishCatelog(){
        return dishCatelogService.queryAllDishCatelog();
    }
}
