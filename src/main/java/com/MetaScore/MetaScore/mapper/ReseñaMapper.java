package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.ReseñaDTO;
import com.MetaScore.MetaScore.model.Reseña;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, ContenidoMapper.class})
public interface ReseñaMapper {

    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "contenido", source = "contenido")
    ReseñaDTO toDto(Reseña reseña);

    List<ReseñaDTO> toDtoList(List<Reseña> resenas);
}



