package br.edu.univas.gabriel.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Billet {

	@Id
	private String id;
	private int number;
	private double value;
	private long dueDate;
	private String receivingEntity;
	private String drawee;
	private List<Auditing> auditing;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public long getDueDate() {
		return dueDate;
	}
	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}
	public String getReceivingEntity() {
		return receivingEntity;
	}
	public void setReceivingEntity(String receivingEntity) {
		this.receivingEntity = receivingEntity;
	}
	public String getDrawee() {
		return drawee;
	}
	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}
	public List<Auditing> getAuditing() {
		return auditing;
	}
	public void setAuditing(List<Auditing> auditing) {
		this.auditing = auditing;
	}
}
