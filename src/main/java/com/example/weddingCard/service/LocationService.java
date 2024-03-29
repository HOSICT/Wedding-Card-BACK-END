package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.dto.LocationDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Location;
import com.example.weddingCard.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    public LocationRepository locationRepository;

    @Transactional
    public void saveLocation (InformationDTO informationDTO, Information information) {
        Location location = dtoLocationEntity(informationDTO.getLocation(), information);
        locationRepository.save(location);
    }

    private Location dtoLocationEntity(LocationDTO locationDTO, Information information) {
        List<Location> findWeddingIdLocation = locationRepository.findByWeddingId(information);
        Location location;
        if (findWeddingIdLocation.isEmpty()) {
            location = new Location();
        } else {
            location = findWeddingIdLocation.get(0);
        }
        location.setWeddingId(information);
        location.setWeddingHall(locationDTO.getWeddingHall());
        location.setAddress(locationDTO.getAddress());
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());

        return location;
    }

    public List<Location> findLocationByWeddingId(List<Information> information) {
        return locationRepository.findByWeddingIdIn(information);
    }
}
