package com.kh.app.adminEmp.controller;

import com.kh.app.adminEmp.service.AdminEmpService;
import org.springframework.http.ResponseEntity;
import com.kh.app.adminEmp.vo.AdminEmpVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("adminEmpMngr")
@RequiredArgsConstructor
public class AdminEmpController {

    private final AdminEmpService service;

    @GetMapping("list")
    public String adminEmpList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            Model model) {
        List<AdminEmpVo> voList = service.adminEmpList(page, size);
        int totalCount = service.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("voList", voList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "adminEmpMngr/adminEmpList";
    }

    @GetMapping("detail")
    public String adminEmpByNo(@RequestParam("no") String no, Model model) {
        AdminEmpVo vo = service.adminEmpByNo(no);
        model.addAttribute("vo", vo);
        return "adminEmpMngr/adminEmpDetail";
    }

    @PostMapping("search")
    public String adminEmpSearch(
            @RequestParam("empCategory") String empCategory,
            @RequestParam("searchBox") String searchBox,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            Model model) {

        List<AdminEmpVo> voList = service.adminEmpSearch(empCategory, searchBox, page, size);
        int totalCount = service.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("voList", voList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "adminEmpMngr/adminEmpList";
    }

//    // JSON 데이터를 반환하는 메서드 추가
//    @GetMapping("/listData")
//    public ResponseEntity<Map<String, Object>> getAdminEmpListData(
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "20") int size) {
//        List<AdminEmpVo> voList = service.adminEmpList(page, size);
//        int totalCount = service.getTotalCount();
//        int totalPages = (int) Math.ceil((double) totalCount / size);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("voList", voList);
//        response.put("currentPage", page);
//        response.put("totalPages", totalPages);
//
//        return ResponseEntity.ok(response);
//    }

}
