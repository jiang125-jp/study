package com.jp.test.regist.dao;

import com.jp.test.regist.bean.Sheep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheepDao extends JpaRepository<Sheep, Integer> {

}
