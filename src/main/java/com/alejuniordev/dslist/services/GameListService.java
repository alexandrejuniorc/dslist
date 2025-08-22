package com.alejuniordev.dslist.services;

import com.alejuniordev.dslist.dtos.GameListDTO;
import com.alejuniordev.dslist.entities.GameList;
import com.alejuniordev.dslist.projections.GameMinProjection;
import com.alejuniordev.dslist.repositories.GameListRepository;
import com.alejuniordev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        List<GameListDTO> dto = result.stream().map(item -> new GameListDTO(item)).toList();

        return dto;
    }

    @Transactional(readOnly = false)
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        // Busca todos os jogos da lista específica ordenados por posição
        List<GameMinProjection> gamesInList = gameRepository.searchByList(listId);

        // Remove o jogo da posição de origem e guarda em uma variável
        GameMinProjection gameToMove = gamesInList.remove(sourceIndex);

        // Insere o jogo removido na nova posição (destino)
        gamesInList.add(destinationIndex, gameToMove);

        // Calcula a menor posição entre origem e destino
        // (essa será a posição inicial do loop de atualização)
        int startPositionToUpdate = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;

        // Calcula a maior posição entre origem e destino
        // (essa será a posição final do loop de atualização)
        int endPositionToUpdate = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        // Atualiza no banco de dados todas as posições que foram afetadas pela movimentação
        // Percorre do índice mínimo até o máximo e salva a nova posição de cada jogo
        for (int i = startPositionToUpdate; i <= endPositionToUpdate; i++) {
            gameListRepository.updateBelongingPosition(listId, gamesInList.get(i).getId(), i);
        }
    }
}
