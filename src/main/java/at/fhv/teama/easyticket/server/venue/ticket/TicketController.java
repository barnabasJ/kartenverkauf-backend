package at.fhv.teama.easyticket.server.venue.ticket;

import at.fhv.teama.easyticket.dto.TicketDto;
import at.fhv.teama.easyticket.server.mapping.MapperContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static at.fhv.teama.easyticket.server.venue.ticket.TicketState.FREE;

@RestController
@Controller
@RequiredArgsConstructor
public class TicketController {
  private final TicketService ticketService;
  private final TicketRepository ticketRepo;
  private final TicketMapper ticketMapper;

  @PostMapping("/venue/ticket/buy")
  @Transactional
  public Set<TicketDto> buyTickets(@RequestBody Long[] ticketIds) {
    return buyTickets(
        ticketService.getTicketsByIds(ticketIds).stream()
            .map(t -> ticketMapper.ticketToTicketDto(t, new MapperContext()))
            .collect(Collectors.toSet()));
  }

  @Transactional
  public Set<TicketDto> buyTickets(Collection<TicketDto> tickets) {
    Set<TicketDto> unavailable = new HashSet<>();
    Set<Ticket> available = new HashSet<>();

    for (TicketDto ticketDto : tickets) {
      Ticket ticket = ticketRepo.findById(ticketDto.getId()).get();
      if (ticket.getState() != FREE) unavailable.add(ticketDto);
      else available.add(ticket);
    }

    // Check availability and return unavailable tickets
    if (!unavailable.isEmpty()) return unavailable;
    else {
      for (Ticket ticket : available) {
        ticketService.sellTicket(ticket);
      }
    }

    // All tickets available - none to return
    return new HashSet<>();
  }

  @Transactional
  public Set<TicketDto> reserveTickets(Collection<TicketDto> tickets) {
    Set<TicketDto> unavailable = new HashSet<>();
    Set<Ticket> available = new HashSet<>();

    for (TicketDto ticketDto : tickets) {
      Ticket ticket = ticketRepo.findById(ticketDto.getId()).get();
      if ((ticket.getState() != FREE) || ticketDto.getPerson() == null) unavailable.add(ticketDto);
      else available.add(ticket);
    }

    if (!unavailable.isEmpty()) return unavailable;
    else {
      for (Ticket ticket : available) {
        ticketService.reserveTicket(ticket);
      }
    }

    return new HashSet<>();
  }

  @Transactional
  public Boolean unreserveTickets(Collection<TicketDto> tickets) {
    if (tickets.isEmpty()) return false;
    for (TicketDto ticketDto : tickets) {
      Ticket ticket = ticketRepo.findById(ticketDto.getId()).get();
      ticketService.unreserveTicket(ticket);
    }
    return true;
  }

  @GetMapping("/venue/{id}/ticket")
  public Set<TicketDto> getTicketsForVenue(@PathVariable long id) {
    return ticketService.getTicketsForVenue(id).stream()
        .map(t -> ticketMapper.ticketToTicketDto(t, new MapperContext()))
        .collect(Collectors.toSet());
  }
}
