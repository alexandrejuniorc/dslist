package com.alejuniordev.dslist.repositories;

import com.alejuniordev.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}
