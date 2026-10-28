package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.service.IGenericService;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Réservations")
public class ReservationController extends GenericRestController<Reservation, Long> {

    private final IReservationService reservationService;

    @Override
    protected IGenericService<Reservation, Long> getService() {
        return reservationService;
    }
}
