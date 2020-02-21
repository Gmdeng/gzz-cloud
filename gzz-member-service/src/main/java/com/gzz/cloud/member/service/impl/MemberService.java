package com.gzz.cloud.member.service.impl;

import com.google.common.collect.Lists;
import com.gzz.cloud.member.domain.Member;
import com.gzz.cloud.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MemberService implements IMemberService {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;

    private final AtomicLong counter = new AtomicLong();

    @Override
    public Member getMember(String no) {
        Member m = new Member(no, "Ricky");
        counter.incrementAndGet();
        m.setLevel(counter.intValue());
        m.setDesc("Server Port:" + serverPort + " ||||" + discoveryClient.getServices().toString());
        return m;
    }

    @Override
    public List<Member> getMemberList() {
        List<Member> list = Lists.newArrayList(
                new Member(UUID.randomUUID().toString(), "李小明"),
                new Member(UUID.randomUUID().toString(), "李小鹏"),
                new Member(UUID.randomUUID().toString(), "周正凯"),
                new Member(UUID.randomUUID().toString(), "黄轩"),
                new Member(UUID.randomUUID().toString(), "强东明")
        );
        return list;
    }
}
