package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.ContenidoDTO;
import com.MetaScore.MetaScore.model.Contenido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReseñaMapper.class})
public interface ContenidoMapper {

    ContenidoMapper INSTANCE = Mappers.getMapper(ContenidoMapper.class);

    ContenidoDTO toDto(Contenido contenido);
}

