package com.denisalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.denisalves.cursomc.domain.Categoria;
import com.denisalves.cursomc.repositories.CategoriaRepository;
import com.denisalves.cursomc.services.exceptions.DataIntegrityException;
import com.denisalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository; 

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id))	;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
 		} 
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que esteja veiculada a produtos");
		}
	}
}
