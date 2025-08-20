package com.alejuniordev.dslist.services;

import com.alejuniordev.dslist.dtos.GameListDTO;
import com.alejuniordev.dslist.entities.GameList;
import com.alejuniordev.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        List<GameListDTO> dto = result.stream().map(item -> new GameListDTO(item)).toList();

        return dto;
    }
}
