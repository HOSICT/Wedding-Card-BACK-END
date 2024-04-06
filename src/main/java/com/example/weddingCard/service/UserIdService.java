package com.example.weddingCard.service;

import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.repository.WecaUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserIdService {

    @Autowired
    public WecaUserRepository wecaUserRepository;

    @Transactional
    public WecaUser saveOrUpdateUserId(String userId) {
        return wecaUserRepository.findById(userId)
                .orElseGet(() -> {
                    WecaUser newUser = new WecaUser();
                    newUser.setUserId(userId);
                    return wecaUserRepository.save(newUser);
                });
    }

    public WecaUser findUserByUid(String userId) {
        return wecaUserRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
    }
}
