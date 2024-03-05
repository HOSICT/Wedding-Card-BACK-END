package com.example.weddingCard.service;

import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.repository.WecaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIdService {

    private final WecaUserRepository wecaUserRepository;

    @Autowired
    public UserIdService(WecaUserRepository wecaUserRepository) {
        this.wecaUserRepository = wecaUserRepository;
    }

    @Transactional
    public WecaUser saveOrUpdateUserId(String userId) {
        return wecaUserRepository.findById(userId)
                .orElseGet(() -> {
                    WecaUser newUser = new WecaUser();
                    newUser.setUserId(userId);
                    return wecaUserRepository.save(newUser);
                });
    }
}
