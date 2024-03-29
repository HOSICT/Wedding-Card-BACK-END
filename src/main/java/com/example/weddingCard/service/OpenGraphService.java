package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.dto.OpenGraphDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.OpenGraph;
import com.example.weddingCard.repository.OpenGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OpenGraphService {

    @Autowired
    private OpenGraphRepository openGraphRepository;

    @Transactional
    public void saveOpenGraph(InformationDTO informationDTO, Information information) {
        OpenGraph openGraph = dtoOpenGraphEntity(informationDTO.getOpenGraph(), information);
        openGraphRepository.save(openGraph);
    }

    private OpenGraph dtoOpenGraphEntity(OpenGraphDTO openGraphDTO, Information information) {
        List<OpenGraph> findWeddingIdOpenGraph = openGraphRepository.findByWeddingId(information);
        OpenGraph openGraph;
        if (findWeddingIdOpenGraph.isEmpty()) {
            openGraph = new OpenGraph();
        } else {
            openGraph = findWeddingIdOpenGraph.get(0);
        }
        openGraph.setWeddingId(information);
        openGraph.setTitle(openGraphDTO.getTitle());
        openGraph.setSubtitle(openGraphDTO.getSubtitle());
        return openGraph;
    }

    public List<OpenGraph> findOpenGraphByWeddingId(List<Information> information) {
        return openGraphRepository.findByWeddingIdIn(information);
    }
}
