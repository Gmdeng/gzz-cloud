package com.gzz.cloud.member.controller;

import com.gzz.cloud.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员信息
 */
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @RequestMapping("/getInfo/{no}")
    public Object getMemberInfo(@PathVariable("no") String no) {
        return memberService.getMember(no);
    }

    @RequestMapping("/getList")
    public Object getMemberList() {
        return memberService.getMemberList();
    }
}
