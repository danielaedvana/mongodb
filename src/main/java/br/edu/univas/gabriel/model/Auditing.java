package br.edu.univas.gabriel.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Auditing {
	@Id
	private String id;
	private String operation;
	private long eventDate;
	
	public Auditing(String operation) {
		this.operation = operation;
		this.eventDate = Instant.now().toEpochMilli();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public long getEventDate() {
		return eventDate;
	}
	public void setEventDate(long eventDate) {
		this.eventDate = eventDate;
	}
}
