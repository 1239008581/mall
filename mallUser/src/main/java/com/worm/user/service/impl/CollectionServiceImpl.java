package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.CollectionMapper;
import com.worm.user.domain.entity.Collection;
import com.worm.user.service.CollectionService;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl extends BaseServiceImpl<Collection,CollectionMapper> implements CollectionService {
}
