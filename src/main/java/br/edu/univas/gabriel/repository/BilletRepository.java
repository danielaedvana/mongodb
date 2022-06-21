package br.edu.univas.gabriel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.univas.gabriel.model.Billet;

public interface BilletRepository extends MongoRepository<Billet, String> {
	
	Billet findBilletByNumber(int number);
}
