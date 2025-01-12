package com.MetaScore.MetaScore.mapper;

import com.MetaScore.MetaScore.dto.UsuarioDTO;
import com.MetaScore.MetaScore.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReseñaMapper.class})
public interface UsuarioMapper {

    @Mapping(target = "role", source = "role", qualifiedByName = "roleToString")
    UsuarioDTO toDto(Usuario usuario);

    List<UsuarioDTO> toDtoList(List<Usuario> usuarios);

    // Conversión explícita de Role a String
    @Named("roleToString")
    static String roleToString(Usuario.Role role) {
        return role != null ? role.name() : null;
    }
}



