package com.alkemy.disneyWorld.mapper;

import com.alkemy.disneyWorld.dto.GenreDTO;
import com.alkemy.disneyWorld.dto.MovieDTO;
import com.alkemy.disneyWorld.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    @Autowired
    private MovieMapper movieMapper;

    public GenreEntity genreDTO2Entity(GenreDTO genreDTO, boolean loadMovies) {

        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(genreDTO.getImage());
        genreEntity.setName(genreDTO.getName());

        if(loadMovies){
            genreEntity.setMovies(movieMapper.moviesDTOList2EntityList(genreDTO.getMovies(), false));
        }


        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity genre, boolean loadMovies) {

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setImage(genre.getImage());
        genreDTO.setName(genre.getName());


        if (loadMovies) {
            List<MovieDTO> movieDTOList = movieMapper.movieEntityList2DTOList(genre.getMovies(), false);
            genreDTO.setMovies(movieDTOList);
        }

        return genreDTO;
    }

    public List<GenreDTO> genreEntityListDTOList(List<GenreEntity> genres, boolean loadMovies) {

        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (GenreEntity genre : genres) {
            genreDTOList.add(genreEntity2DTO(genre, loadMovies));
        }

        return genreDTOList;
    }
}
