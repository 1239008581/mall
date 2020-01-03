package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.ReceivingAddressMapper;
import com.worm.user.domain.entity.ReceivingAddress;
import com.worm.user.service.ReceivingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ReceivingAddressServiceImpl extends BaseServiceImpl<ReceivingAddress, ReceivingAddressMapper> implements ReceivingAddressService {
}
