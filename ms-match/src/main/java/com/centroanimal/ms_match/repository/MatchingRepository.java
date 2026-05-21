package com.centroanimal.ms_match.repository;

import com.centroanimal.ms_match.model.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {

    List<Matching> findByIdUsuario(Long idUsuario);

    List<Matching> findByEspeciePreferida(String especiePreferida);

    List<Matching> findByTamanoPreferido(String tamanoPreferido);

    List<Matching> findByEdadPreferida(Integer edadPreferida);
}