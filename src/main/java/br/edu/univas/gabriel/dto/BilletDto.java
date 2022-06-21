package br.edu.univas.gabriel.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BilletDto {

	@NotNull(message = "O Número do boleto não pode ser null.")
	private int number;
	
	@NotNull(message = "O valor do boleto não pode ser null.")
	private double value;
	
	@NotNull(message = "A data de vencimento não pode ser null.")
	@NotEmpty(message = "A data de vencimento não pode ser vazia.")
	private String dueDate;
	
	@NotNull(message = "A entidade recebedora não pode ser null.")
	@NotEmpty(message = "A entidade recebedora não pode ser vazia.")
	private String receivingEntity;
	
	@NotNull(message = "O sacador não pode ser null.")
	@NotEmpty(message = "O sacador não pode ser vazio.")
	private String drawee;

	public BilletDto() {
	}

	public BilletDto(int number, double value, String dueDate, String receivingEntity, String drawee) {
		this.number = number;
		this.value = value;
		this.dueDate = dueDate;
		this.receivingEntity = receivingEntity;
		this.drawee = drawee;
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

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
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
}
