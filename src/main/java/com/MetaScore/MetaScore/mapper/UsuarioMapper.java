package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.UsuarioDTO;
import com.MetaScore.MetaScore.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDto(Usuario usuario);
}


