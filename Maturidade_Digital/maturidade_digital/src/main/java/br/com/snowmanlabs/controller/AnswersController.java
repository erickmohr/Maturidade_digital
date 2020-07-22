package br.com.snowmanlabs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.snowmanlabs.domain.Answers;
import br.com.snowmanlabs.repository.AnswersRepository;
import br.com.snowmanlabs.service.AnswersService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dmd")
public class AnswersController {
	
	@Autowired
	private AnswersService answersService;
	
	@Autowired
	private AnswersRepository answersRepository;
	
	@ApiOperation(value = "Cadastra respostas.")
	@PostMapping(value = "/answers")
	public ResponseEntity<Answers> newAnswers(@RequestBody Answers answers) {
		try {
			answersService.saveAnswers(answers);
			return ResponseEntity.ok(answers);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	}
			
	@ApiOperation(value = "Retorna lista de todas as respostas.")
	@GetMapping(value = "/getAnswers")
	public ResponseEntity<List<Answers>> getAnswers() {
		return ResponseEntity.ok().body(answersRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna resposta por Id.")
	@GetMapping("/getAnswersId/{id}")
	public ResponseEntity<Answers> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok().body(answersRepository.findById(id).get());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Atualiza resposta.")
	@PutMapping("/updateAnswer")
	public Answers updateAnswers(@RequestBody Answers answers) {
		return answersRepository.save(answers);
	}
	
	@ApiOperation(value="Deleta resposta.")
	@DeleteMapping("/deleteAnswer")
	public ResponseEntity<String> deleteAnswers(@RequestBody Answers answers) {
		try {
			answersRepository.delete(answers);
		} catch (Exception e) {
			return new ResponseEntity<>("Questão a ser excluido não foi encontrado!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Questão excluida com sucesso!", HttpStatus.OK);
	}
	
}

 