package com.example.weddingCard.service;

import com.example.weddingCard.dto.ContentsDTO;
import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.Contents;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentsService {

    @Autowired
    private ContentsRepository contentsRepository;

    @Transactional
    public void saveContents(InformationDTO informationDTO, Information information) {
        Contents contents = dtoContentsEntity(informationDTO.getContents(), information);
        contentsRepository.save(contents);
    }

    private Contents dtoContentsEntity(ContentsDTO contentsDTO, Information information) {
        List<Contents> findWeddingIdContents = contentsRepository.findByWeddingId(information);
        Contents contents;
        if (findWeddingIdContents.isEmpty()) {
            contents = new Contents();
        } else {
            contents = findWeddingIdContents.get(0);
        }
        contents.setWeddingId(information);
        contents.setBgm(contentsDTO.getBgm());
        contents.setVideoId(contentsDTO.getVideoId());
        contents.setLiveUrl(contentsDTO.getLiveUrl());
        return contents;
    }
}
