package br.com.snowmanlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import br.com.snowmanlabs.domain.Answers;
import br.com.snowmanlabs.exceptions.ValidateException;
import br.com.snowmanlabs.repository.AnswersRepository;

@Service
public class AnswersService {
	
	@Autowired
	private AnswersRepository answersRepository;

	@Transactional
	public Answers saveAnswers(Answers answers) throws ValidateException{
		
		if (answers.getQuestions().getId() == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			
		}else if("conheco totalmente".equals(answers.getOptions())){
			answers.setValue(1.25);
		}else if("conheco".equals(answers.getOptions())){
			answers.setValue(2.5);
		}else if("conheco parcialmente".equals(answers.getOptions())){
			answers.setValue(3.75);
		}else if("conheco pouco".equals(answers.getOptions())){
			answers.setValue(5);
		}else if("completamente desconhecido".equals(answers.getOptions())){
			answers.setValue(6.25);
		}
		 
		return answersRepository.save(answers);
	}	
	
}
		
		
	