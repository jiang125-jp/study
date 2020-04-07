package com.jp.test.jpa.dao;

import com.jp.test.jpa.bean.Sheep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheepDao extends JpaRepository<Sheep, Integer> {

}
