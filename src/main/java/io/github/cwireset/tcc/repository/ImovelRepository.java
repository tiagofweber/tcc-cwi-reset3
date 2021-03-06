package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    Page<Imovel> findAllByAtivoTrue(Pageable pageable);
    Page<Imovel> findAllByProprietarioIdAndAtivoTrue(Long idProprietario, Pageable pageable);
    Imovel findByIdEqualsAndAtivoTrue(Long id);
    boolean existsByIdAndAtivoTrue(Long id);
}
