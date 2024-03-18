package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.dto.SharingDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Sharing;
import com.example.weddingCard.repository.SharingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharingService {

    @Autowired
    private SharingRepository sharingRepository;

    public void saveSharing(InformationDTO informationDTO, Information information) {
        Sharing sharing = dtoSharingEntity(informationDTO.getSharing(), information);
        sharingRepository.save(sharing);
    }

    private Sharing dtoSharingEntity(SharingDTO sharingDTO, Information information) {
        List<Sharing> findWeddingIdSharing = sharingRepository.findByWeddingId(information);
        Sharing sharing;
        if (findWeddingIdSharing.isEmpty()) {
            sharing = new Sharing();
        } else {
            sharing = findWeddingIdSharing.get(0);
        }
        sharing.setWeddingId(information);
        sharing.setTitle(sharingDTO.getTitle());
        sharing.setSubtitle(sharingDTO.getSubtitle());
        return sharing;
    }
}
