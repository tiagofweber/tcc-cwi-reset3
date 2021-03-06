package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    boolean existsByImovelId(Long idImovel);
    Page<Anuncio> findAllByAtivoTrue(Pageable pageable);
    Page<Anuncio> findAllByAnuncianteIdAndAtivoTrue(Long idAnunciante, Pageable pageable);
    Boolean existsByImovelIdAndAtivoTrue(Long id);
}
