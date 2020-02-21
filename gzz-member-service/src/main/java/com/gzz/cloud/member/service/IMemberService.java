package com.gzz.cloud.member.service;

import com.gzz.cloud.member.domain.Member;

import java.util.List;

public interface IMemberService {
    Member getMember(String no);

    List<Member> getMemberList();
}
