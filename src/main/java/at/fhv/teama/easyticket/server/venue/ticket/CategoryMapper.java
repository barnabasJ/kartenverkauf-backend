package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.dto.CategoryDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import at.fhv.teama.easyticket.server.address.AddressMapper;
import at.fhv.teama.easyticket.server.person.PersonMapper;
import at.fhv.teama.easyticket.server.program.ProgramMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    uses = {ProgramMapper.class, PersonMapper.class, AddressMapper.class})
public interface CategoryMapper {

  CategoryDto categoryToCategoryDto(Category category, @Context MapperContext context);

  Category categoryDtoToCategory(CategoryDto category, @Context MapperContext context);
}
