package com.gmul.toy.example.dao;

import com.gmul.toy.example.domain.AutoDebitInfo;
import com.gmul.toy.example.domain.AutoDebitRegister;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }
}
