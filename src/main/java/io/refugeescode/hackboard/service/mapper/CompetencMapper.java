package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Competency;
import io.refugeescode.hackboard.service.dto.CompetencyDto;
import org.checkerframework.checker.units.qual.C;
import org.mapstruct.factory.Mappers;

public interface CompetencMapper {
    CompetencMapper INSTANCE = Mappers.getMapper(CompetencMapper.class);
    CompetencyDto CompetencToCompetencDto(Competency competency);
}
