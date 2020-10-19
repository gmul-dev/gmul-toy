package com.gmul.toy.example.dao;


import com.gmul.toy.example.domain.AutoDebitInfo;
import com.gmul.toy.example.domain.AutoDebitRegister;

public interface AutoDebitInfoRepository {
    
    AutoDebitInfo findOne(String userId);

    void save(AutoDebitInfo newInfo);
}
