package com.blaser.springcloud.service.impl;

import com.blaser.springcloud.entity.Payment;
import com.blaser.springcloud.mapper.PaymentMapper;
import com.blaser.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Blaser
 * @create: 2022-03-21 18:44
 **/

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper mapper;

    @Override
    public Payment get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public int add(Payment pay) {
        return mapper.insert(pay);
    }
}
