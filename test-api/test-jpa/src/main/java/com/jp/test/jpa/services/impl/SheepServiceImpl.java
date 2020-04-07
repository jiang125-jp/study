package com.jp.test.jpa.services.impl;

import com.jp.test.jpa.bean.Sheep;
import com.jp.test.jpa.dao.SheepDao;
import com.jp.test.jpa.services.SheepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheepServiceImpl implements SheepService {

    private final SheepDao sheepDao;

    @Autowired
    public SheepServiceImpl(SheepDao sheepDao) {
        this.sheepDao = sheepDao;
    }

    @Override
    public List<Sheep> getSheepList() {
        return sheepDao.findAll();
    }
}
