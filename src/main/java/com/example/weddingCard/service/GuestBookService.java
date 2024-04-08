package com.example.weddingCard.service;

import com.example.weddingCard.dto.GuestBookDTO;
import com.example.weddingCard.entity.GuestBook;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GuestBookService {

    @Autowired
    public GuestBookRepository guestBookRepository;

    @Transactional
    public GuestBook saveGuestBook(GuestBookDTO guestBookDTO, Information information) {
        GuestBook guestBook = dtoGuestBookEntity(guestBookDTO, information);
        Integer maxCommentId = guestBookRepository.findMaxCommentIdByWeddingId(information.getWeddingId());
        guestBook.setCommentId(maxCommentId + 1);

        guestBook = guestBookRepository.save(guestBook);

        return guestBook;
    }

    public List<GuestBook> findByWeddingId(Information weddingId) {
        return guestBookRepository.findByWeddingId(weddingId);
    }

    public void deleteByWeddingIdAndCommentId(Integer weddingId, Integer commentId) {
        guestBookRepository.deleteByWeddingIdAndCommentId(weddingId, commentId);
    }

    private GuestBook dtoGuestBookEntity(GuestBookDTO guestBookDTO, Information information) {
        GuestBook guestBook = new GuestBook();

        guestBook.setWeddingId(information);
        guestBook.setName(guestBookDTO.getName());
        guestBook.setDate(adjustDate(guestBookDTO.getDate()));
        guestBook.setContent(guestBookDTO.getContent());
        guestBook.setManagementPassword(guestBookDTO.getManagementPassword());

        return guestBook;
    }

    private LocalDateTime adjustDate(LocalDateTime localDateTime) {
        String getDateToString = localDateTime.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(getDateToString, formatter);
        LocalDateTime adjustedDate = dateTime.withSecond(0).withNano(0);

        return adjustedDate;
    }
}
