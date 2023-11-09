package br.edu.atitus.atitusound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.atitusound.entities.GenericEntity;
import br.edu.atitus.atitusound.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity> {
	
	GenericRepository<TEntidade> getRespository();
	default void validateFindByName(Pageable pageable, String name)throws Exception{
		
	}
	
	default void validateSave(TEntidade entidade) throws Exception{
		if (entidade.getName() == null || entidade.getName().isEmpty())
			throw new Exception("Campo Name Inválido");
		if (entidade.getUuid() == null) {
			if(getRespository().existsByName(entidade.getName())) {
				throw new Exception("Já existe registro com esse nome");
			}
		}else {	
			if(!getRespository().existsById(entidade.getUuid()))
				throw new Exception("Registro não encontrado com este UUID");
			if (getRespository().existsByNameAndUuidNot(entidade.getName(), entidade.getUuid())) 
					throw new Exception("Já existe registro com esse nome");
		}
	}

	default TEntidade save(TEntidade entidade) throws Exception {
		validateSave(entidade);
		getRespository().save(entidade);
		return entidade;
	}

	default List<TEntidade> findAll() throws Exception {
		return getRespository().findAll();
	}

	default Page<List<TEntidade>> findByNameContainingIgnoreCase(Pageable pageable, String name) throws Exception {
		validateFindByName(pageable, name);
		return getRespository().findByNameContainingIgnoreCase(pageable, name);
		
	}
	default void validateFindById(UUID uuid) throws Exception{
		
	}
	default Optional<TEntidade> findById(UUID uuid) throws Exception {
		validateFindById(uuid);
		return getRespository().findById(uuid);	
	}
	
	default void validateDeleteById(UUID uuid)throws Exception{
		if (!getRespository().existsById(uuid)) {
			throw new Exception("Registro não encontrado com esse UUID");
		}
	}

	default void deleteById(UUID uuid) throws Exception {
		validateDeleteById(uuid);
		getRespository().deleteById(uuid);
	}

}
