package br.edu.univas.gabriel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.gabriel.controller.exception.InvalidDataException;
import br.edu.univas.gabriel.controller.exception.ObjectNotFoundException;
import br.edu.univas.gabriel.dto.BilletDto;
import br.edu.univas.gabriel.model.Auditing;
import br.edu.univas.gabriel.model.Billet;
import br.edu.univas.gabriel.repository.AuditingRepository;
import br.edu.univas.gabriel.repository.BilletRepository;

@Service
public class BilletService {
	
	@Autowired
	private BilletRepository billetRepository;
	@Autowired
	private AuditingRepository auditingRepository;
	
	public List<BilletDto> getAllBillet(){
		List<Billet> billets = billetRepository.findAll();
		return billets.stream()
				.map(billet -> new BilletDto(billet.getNumber(), billet.getValue(), convertDateToString(billet.getDueDate()), billet.getReceivingEntity(), billet.getDrawee()))
				.collect(Collectors.toList());
	}
	
	public BilletDto getBilletById(String id){
		Billet billet = billetRepository.findOne(id);
		if(billet == null) {
			throw new ObjectNotFoundException("Id não encontrado");
		} else {
			return new BilletDto(billet.getNumber(), billet.getValue(), convertDateToString(billet.getDueDate()), billet.getReceivingEntity(), billet.getDrawee());
		}
	}


	public void create(BilletDto dto)  {
		validateBilletNumber(dto.getNumber());
		
		List<Auditing> auditings = new ArrayList<>();
		Auditing auditing = new Auditing("create");
		
		try {
			Billet billet = toBillet(dto);
			billet.setAuditing(auditings);
			billetRepository.save(billet);
		} catch (ParseException e) {
			throw new InvalidDataException("Data inválida");
		}
		
		auditingRepository.save(auditing);
	}
	
	public void delete(String id) {
		Auditing auditing = new Auditing("delete");
		Billet billet = billetRepository.findOne(id);
		if(billet == null) {
			throw new ObjectNotFoundException("Id não encontrado");
		} else {
			billetRepository.delete(billet);
		}
		auditingRepository.save(auditing);
	}
	
	public void update(String id, BilletDto dto) {
		Billet oldBillet = billetRepository.findOne(id);
		
		if(oldBillet == null) {
			throw new ObjectNotFoundException("Id não encontrado");
		} else {
			try {
				validateBilletNumberOnUpdate(dto.getNumber(), oldBillet.getId());
				
				Auditing auditing = new Auditing("update");
				List<Auditing> auditings = oldBillet.getAuditing();
				auditings.add(auditing);
				
				Billet billet = toBillet(dto);
				billet.setAuditing(auditings);
				billet.setId(oldBillet.getId());
				
				billetRepository.save(billet);
				
				auditingRepository.save(auditing);
			} catch (ParseException e) {
				throw new InvalidDataException("Data inválida");
			}
		}
	}
	
	
	private Billet toBillet(BilletDto dto) throws ParseException {
		Billet billet = new Billet();
		billet.setDrawee(dto.getDrawee());
		billet.setDueDate(convertDateToLong(dto.getDueDate()));
		billet.setNumber(dto.getNumber());
		billet.setReceivingEntity(dto.getReceivingEntity());
		billet.setValue(dto.getValue());
		return billet;
	}
	
	private long convertDateToLong(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		dateFormat.setLenient(false);
		Date date = dateFormat.parse(dateString);
		return date.getTime();
	}

	private String convertDateToString(long date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		dateFormat.setLenient(false);
        return dateFormat.format(date);
	}
	
	private void validateBilletNumber(int number) {
		Billet billet = billetRepository.findBilletByNumber(number);
		if(billet != null) {
			throw new InvalidDataException("O número do boleto já foi utilizado");
		}
	}
	
	private void validateBilletNumberOnUpdate(int number, String id) {
		Billet billet = billetRepository.findBilletByNumber(number);
		if(billet != null && !id.equals(billet.getId())) {
			throw new InvalidDataException("O número do boleto já foi utilizado");
		}
	}

}
