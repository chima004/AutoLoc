package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long> implements IReservationService {

    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Reservation reservation, boolean isNew) {
        if (reservation == null) {
            return false;
        }
        if (isNew && reservation.getIdReservation() != null) {
            return false;
        }
        return reservation.getDateDebut() != null
                && reservation.getDateFin() != null
                && reservation.getStatut() != null;
    }

    @Override
    protected String getEntityName() {
        return "Reservation";
    }

    @Override
    protected Long getId(Reservation entity) {
        return entity.getIdReservation();
    }

    @Override
    protected void setFields(Reservation source, Reservation target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setStatut(source.getStatut());
        target.setVehicule(source.getVehicule());
        target.setClient(source.getClient());
        target.setContrat(source.getContrat());
    }
}
