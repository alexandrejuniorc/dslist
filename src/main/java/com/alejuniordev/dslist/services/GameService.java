package com.alejuniordev.dslist.services;

import com.alejuniordev.dslist.dtos.GameDTO;
import com.alejuniordev.dslist.dtos.GameMinDTO;
import com.alejuniordev.dslist.entities.Game;
import com.alejuniordev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // @Transactional -> Assegura que essa função não será utilizada para escrita
    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        List<GameMinDTO> dto = result.stream().map(item -> new GameMinDTO(item)).toList();

        return dto;
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id).get();
        GameDTO dto = new GameDTO(result);

        return dto;
    }
}
