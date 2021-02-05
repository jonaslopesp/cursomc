package br.com.jonaslopes.maisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jonaslopes.maisvendas.domain.Categoria;
import br.com.jonaslopes.maisvendas.repositories.CategoriaRepository;
import br.com.jonaslopes.maisvendas.services.expeptions.DataIntegrityException;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		 Optional<Categoria> obj = repo.findById(id);
		 
  		 return obj.orElseThrow(() -> new ObjectNotFoundException(
  		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Categoria que possui produtos!");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
}
