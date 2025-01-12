package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.ContenidoDTO;
import com.MetaScore.MetaScore.model.Contenido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring", uses = {ReseñaMapper.class})
public interface ContenidoMapper {

    @Mapping(target = "reseñas", source = "reseñas")
    ContenidoDTO toDto(Contenido contenido);

    List<ContenidoDTO> toDtoList(List<Contenido> contenidos);
}


