package com.anubis.albion.repository;

import com.anubis.albion.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, UUID> {
}
