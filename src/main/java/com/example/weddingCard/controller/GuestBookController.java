package com.example.weddingCard.controller;

import com.example.weddingCard.dto.GuestBookDTO;
import com.example.weddingCard.entity.GuestBook;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.InformationRepository;
import com.example.weddingCard.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GuestBookController {

    private final GuestBookService guestBookService;

    @Autowired
    private InformationRepository informationRepository;

    public GuestBookController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @PostMapping("/guestbook")
    public ResponseEntity<Map<String, Object>> uploadGuestBook(@RequestParam("wedding_id") Integer weddingId,
                                               @RequestBody GuestBookDTO guestBookRequest) {
        Information information = informationRepository.findById(weddingId)
                .orElseThrow(() -> new RuntimeException("Wedding ID not found: " + weddingId));
        GuestBook guestBook = guestBookService.saveGuestBook(guestBookRequest, information);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 200);
        responseBody.put("msg", "ok");
        Map<String, Object> data = new HashMap<>();
        data.put("id", information.getWeddingId().toString());
        responseBody.put("data", data);

        return ResponseEntity.ok(responseBody);
    }
}
