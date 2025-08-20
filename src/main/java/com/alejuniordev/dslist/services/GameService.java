package com.alejuniordev.dslist.services;

import com.alejuniordev.dslist.dtos.GameMinDTO;
import com.alejuniordev.dslist.entities.Game;
import com.alejuniordev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        List<GameMinDTO> dto = result.stream().map(item -> new GameMinDTO(item)).toList();

        return dto;
    }
}
