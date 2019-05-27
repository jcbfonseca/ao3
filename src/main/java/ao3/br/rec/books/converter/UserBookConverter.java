package ao3.br.rec.books.converter;

import ao3.br.rec.books.dto.UserBookDTO;
import ao3.br.rec.books.entity.UserBook;

public class UserBookConverter {
	public static UserBook dtoToEntity(UserBookDTO dto) {
		
		UserBook ub = new UserBook();
		if(dto.getBook() != null) {
			ub.setBook(BookConverter.dtoToEntity(dto.getBook()));
		}
		if(dto.getUser() != null) {
			//ub.setUser(UserConverter.dtoToEntity(dto.getUser()));
		}
		
		ub.setEndDate(dto.getEndDate());
		ub.setStartDate(dto.getStartDate());
		ub.setUser_id(dto.getUser_id());
		ub.setBook_id(dto.getBook_id());
		ub.setOpinion(dto.getOpinion());
		ub.setProgress(dto.getProgress());
		ub.setStar(dto.getStar());
		
		return ub;
	}

	public static UserBookDTO entityToDto(UserBook ub) {
		System.out.println("UserBookConverter.entityToDto(" + ub + ")");
		UserBookDTO dto = new UserBookDTO();
		
		if(ub.getBook() != null) {
			dto.setBook(BookConverter.entityToDto(ub.getBook()));
		}
		if(ub.getUser() != null) {
			//dto.setUser(UserConverter.entityToDto(ub.getUser()));
		}
		
		dto.setEndDate(ub.getEndDate());
		dto.setStartDate(ub.getStartDate());
		dto.setUser_id(ub.getUser_id());
		dto.setBook_id(ub.getBook_id());
		dto.setOpinion(ub.getOpinion());
		dto.setProgress(ub.getProgress());
		dto.setStar(ub.getStar());
		
		return dto;
	}
}
