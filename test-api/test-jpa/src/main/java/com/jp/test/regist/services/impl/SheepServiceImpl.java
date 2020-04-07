package com.jp.test.regist.services.impl;

import com.jp.test.regist.bean.Sheep;
import com.jp.test.regist.dao.SheepDao;
import com.jp.test.regist.services.SheepService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheepServiceImpl implements SheepService {

    private final SheepDao sheepDao;

    public SheepServiceImpl(SheepDao sheepDao) {
        this.sheepDao = sheepDao;
    }

    @Override
    public List<Sheep> getSheepList() {
        return sheepDao.findAll();
    }
}
