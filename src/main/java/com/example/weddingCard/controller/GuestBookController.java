package com.example.weddingCard.controller;

import com.example.weddingCard.dto.GuestBookDTO;
import com.example.weddingCard.entity.GuestBook;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.InformationRepository;
import com.example.weddingCard.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping(("/guestbook"))
    public ResponseEntity<Map<String, Object>> getGuestBooks(@RequestParam("wedding_id") Information weddingId) {
        List<GuestBook> guestBooks = guestBookService.findByWeddingId(weddingId);
        List<GuestBookDTO> guestBookDTOs = guestBooks.stream()
                .map(guestBook -> {
                    GuestBookDTO dto = new GuestBookDTO();
                    dto.setName(guestBook.getName());
                    dto.setDate(guestBook.getDate());
                    dto.setContent(guestBook.getContent());
                    dto.setManagementPassword(guestBook.getManagementPassword());
                    return dto;
                })
                .collect(Collectors.toList());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("guestbook", guestBookDTOs);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping(("/guestbook"))
    public ResponseEntity<Map<String, Object>> deleteGuestBook(@RequestParam("wedding_id") Integer weddingId,
                                                               @RequestParam("id") Integer commentId) {
        guestBookService.deleteByWeddingIdAndCommentId(weddingId, commentId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("msg", "ok");

        return ResponseEntity.ok(response);
    }

}
