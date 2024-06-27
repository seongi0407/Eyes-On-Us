package com.kh.app.adminDept.controller;

import com.kh.app.adminDept.service.AdminDeptService;
import com.kh.app.adminDept.vo.DivisionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("adminDeptMngr")
@RequiredArgsConstructor
public class AdminDeptController {

    private final AdminDeptService service;

    @GetMapping("list")
    public String adminDeptList() {
        return "adminDeptMngr/adminDeptList";
    }//method

    @GetMapping("listData")
    @ResponseBody
    public Map<String, Object> adminDeptListData(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        List<DivisionVo> voList = service.adminDeptList(page, size);
        int totalCount = service.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        Map<String, Object> result = new HashMap<>();
        result.put("voList", voList);
        result.put("currentPage", page);
        result.put("totalPages", totalPages);
        return result;
    }

    @PostMapping("search")
    public String adminDeptSearch() {
        return "adminDeptMngr/adminDeptList";
    }//method

    @PostMapping("searchData")
    @ResponseBody
    public Map<String, Object> adminDeptSearchData(
            @RequestParam("empCategory") String empCategory,
            @RequestParam("searchBox") String searchBox,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        List<DivisionVo> voList = service.adminDeptSearchData(empCategory, searchBox, page, size);
        int totalCount = service.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        Map<String, Object> result = new HashMap<>();
        result.put("voList", voList);
        result.put("currentPage", page);
        result.put("totalPages", totalPages);
        return result;
    }

    @GetMapping("enrollDiv")
    public String enrollDivForm() {
        return "adminDeptMngr/adminDeptEnrollDiv";
    }//method

    @PostMapping("enrollDiv")
    public String enrollDiv(
            @RequestParam("deptCategory") String deptCode,
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("enrollDate") String enrollDate,
            @RequestParam("stateCategory") String state) {

        DivisionVo divisionVo = new DivisionVo();
        divisionVo.setDeptCode(deptCode);
        divisionVo.setName(name);
        divisionVo.setCode(code);
        divisionVo.setEnrollDate(enrollDate);
        divisionVo.setState(state);

        // 서비스에 데이터 전달
        service.enrollDiv(divisionVo);

        return "redirect:/adminDeptMngr/list";
    }//method

    @PostMapping("enrollDivData")
    @ResponseBody
    public Map<String, Object> enrollDivData(
            @RequestParam("deptCategory") String deptCode,
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("enrollDate") String enrollDate,
            @RequestParam("stateCategory") String state){

        DivisionVo divisionVo = new DivisionVo();
        divisionVo.setDeptCode(deptCode);
        divisionVo.setName(name);
        divisionVo.setCode(code);
        divisionVo.setEnrollDate(enrollDate);
        divisionVo.setState(state);

        // 서비스에 데이터 전달
        List<DivisionVo> voList = service.enrollDivData(divisionVo);
        Map<String, Object> result = new HashMap<>();
        result.put("voList", voList);

        return result;
    }

    @GetMapping("edit")
    public String adminDeptEditForm(@RequestParam("code") String code, Model model){
        DivisionVo vo = service.getDivisionByCode(code);
        model.addAttribute("vo", vo);
        return "adminDeptMngr/adminDeptEdit";
    }

    @PostMapping("edit")
    public String adminDeptEdit(
            @RequestParam("deptCode") String deptCode,
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("enrollDate") String enrollDate,
            @RequestParam("modifyDate") String modifyDate,
            @RequestParam("state") String state){

        DivisionVo divisionVo = new DivisionVo();
        divisionVo.setDeptCode(deptCode);
        divisionVo.setName(name);
        divisionVo.setCode(code);
        divisionVo.setEnrollDate(enrollDate);
        divisionVo.setModifyDate(modifyDate);
        divisionVo.setState(state);

        service.updateDivision(divisionVo);

        return "redirect:/adminDeptMngr/list";
    }

}


