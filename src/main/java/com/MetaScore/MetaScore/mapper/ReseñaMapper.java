package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.ReseñaDTO;
import com.MetaScore.MetaScore.model.Reseña;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, ContenidoMapper.class})
public interface ReseñaMapper {

    ReseñaMapper INSTANCE = Mappers.getMapper(ReseñaMapper.class);

    @Mapping(target = "contenidoId", source = "contenido.id")
    ReseñaDTO toDto(Reseña reseña);
}

