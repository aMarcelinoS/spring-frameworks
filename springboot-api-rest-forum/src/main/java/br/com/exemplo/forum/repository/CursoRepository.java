package br.com.exemplo.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
