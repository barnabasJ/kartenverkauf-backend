package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.server.MapperContext;
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
public interface TicketMapper {

  TicketDto ticketToTicketDto(Ticket ticket, @Context MapperContext context);

  Ticket ticketDtoToTicket(TicketDto ticket, @Context MapperContext context);
}
