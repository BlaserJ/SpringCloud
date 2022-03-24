package com.blaser.springcloud.service;

import com.blaser.springcloud.entity.Payment;

public interface PaymentService {

    Payment get(Long id);

    int add(Payment pay);
}
