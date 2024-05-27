package com.example.weddingCard.service;

import com.example.weddingCard.entity.Like;
import com.example.weddingCard.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Autowired
    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Transactional
    public void addLike(int templateId, String userId) {
        Like like = new Like();
        like.setTemplateId(templateId);
        like.setUserId(userId);
        likesRepository.save(like);
    }

    @Transactional
    public void removeLike(int templateId, String userId) {
        likesRepository.deleteByTemplateIdAndUserId(templateId, userId);
    }

    public int getLikesCount(int templateId) {
        return likesRepository.countByTemplateId(templateId);
    }

    public List<Integer> getLikedTemplates(String userId) {
        List<Like> likes = likesRepository.findByUserId(userId);
        return likes.stream().map(Like::getTemplateId).collect(Collectors.toList());
    }
}
