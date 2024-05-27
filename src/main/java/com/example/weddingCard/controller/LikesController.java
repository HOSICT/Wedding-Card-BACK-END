package com.example.weddingCard.controller;

import com.example.weddingCard.response.WecaResponse;
import com.example.weddingCard.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/likes")
    public ResponseEntity<WecaResponse> addLike(@RequestHeader("Uid") String userId, @RequestParam("template_id") int templateId) {
        likesService.addLike(templateId, userId);
        WecaResponse response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/likes")
    public ResponseEntity<WecaResponse> removeLike(@RequestHeader("Uid") String userId, @RequestParam("template_id") int templateId) {
        likesService.removeLike(templateId, userId);
        WecaResponse response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/likes")
    public ResponseEntity<Map<String, Object>> getLikesCount(@RequestParam("id") int templateId) {
        int count = likesService.getLikesCount(templateId);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", templateId);
        responseBody.put("count", count);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/mypage/likes")
    public ResponseEntity<Map<String, Object>> getLikedTemplates(@RequestHeader("Uid") String userId) {
        List<Integer> likedTemplates = likesService.getLikedTemplates(userId);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("template_id", likedTemplates);
        return ResponseEntity.ok(responseBody);
    }
}
