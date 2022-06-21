package br.edu.univas.gabriel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.univas.gabriel.model.Auditing;

public interface AuditingRepository extends MongoRepository<Auditing, String> {

}
