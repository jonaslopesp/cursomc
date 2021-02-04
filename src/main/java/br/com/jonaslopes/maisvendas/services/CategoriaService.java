package br.com.jonaslopes.maisvendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonaslopes.maisvendas.domain.Categoria;
import br.com.jonaslopes.maisvendas.repositories.CategoriaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		 Optional<Categoria> obj = repo.findById(id);
		 
  		 return obj.orElseThrow(() -> new ObjectNotFoundException(
  		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
