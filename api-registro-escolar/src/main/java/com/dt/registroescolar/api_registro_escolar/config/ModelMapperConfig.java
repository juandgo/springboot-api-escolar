package com.dt.registroescolar.api_registro_escolar.config;

import com.dt.registroescolar.api_registro_escolar.dto.PersonaDTO;
import com.dt.registroescolar.api_registro_escolar.dto.ProfesorDTO;
import com.dt.registroescolar.api_registro_escolar.entity.Persona;
import com.dt.registroescolar.api_registro_escolar.entity.Profesor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Primero se debe crear el TypeMap base
        mapper.createTypeMap(Persona.class, PersonaDTO.class);

        // Luego puedes usar includeBase
        mapper.typeMap(Profesor.class, ProfesorDTO.class)
                .includeBase(Persona.class, PersonaDTO.class);

        return mapper;
    }
}