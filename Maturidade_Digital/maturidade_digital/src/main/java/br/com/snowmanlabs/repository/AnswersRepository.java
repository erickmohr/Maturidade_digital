package br.com.snowmanlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.snowmanlabs.domain.Answers;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Integer>{
	

}


