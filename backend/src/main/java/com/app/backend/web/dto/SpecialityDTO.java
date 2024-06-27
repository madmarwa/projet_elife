package com.app.backend.web.dto;

import com.app.backend.dao.entities.Speciality;
import lombok.Builder;

@Builder
public record SpecialityDTO(
    String id,
    String name
) {
    public static SpecialityDTO toSpecialityDTO(Speciality speciality) {
        return SpecialityDTO.builder()
            .id(speciality.getId())
            .name(speciality.getName())
            .build();
    }

    public static Speciality fromSpecialityDTO(SpecialityDTO specialityDTO) {
        return Speciality.builder()
            .id(specialityDTO.id())
            .name(specialityDTO.name())
            .build();
    }
}
