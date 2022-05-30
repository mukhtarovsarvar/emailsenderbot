package com.company.GeneratePromoKod.service;

import com.company.GeneratePromoKod.dto.ProfileDTO;
import com.company.GeneratePromoKod.entity.ProfileEntity;
import com.company.GeneratePromoKod.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository repository;

    public ProfileDTO create(ProfileDTO dto) {

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setUserId(dto.getUserId());
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setStartedDate(entity.getStartedDate());
        return dto;
    }

    public void saveCheck(ProfileDTO dto) {
        ProfileDTO dto1 = get(dto.getUserId());

        if (dto1 == null) {
            create(dto);
        }

    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setUserId(entity.getUserId());
        dto.setId(entity.getId());
        dto.setStartedDate(entity.getStartedDate());
        return dto;
    }

    public ProfileDTO get(String userId) {
        Optional<ProfileEntity> optional = repository.findByUserId(userId);

        if (optional.isEmpty()) return null;

        return toDTO(optional.get());
    }

}
