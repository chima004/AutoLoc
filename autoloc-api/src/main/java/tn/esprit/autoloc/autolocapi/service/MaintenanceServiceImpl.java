package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;

@Service
public class MaintenanceServiceImpl extends GenericServiceImpl<Maintenance, Long> implements IMaintenanceService {

    public MaintenanceServiceImpl(IMaintenanceRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Maintenance maintenance, boolean isNew) {
        if (maintenance == null) {
            return false;
        }
        if (isNew && maintenance.getIdMaintenance() != null) {
            return false;
        }
        return maintenance.getDateDebut() != null;
    }

    @Override
    protected String getEntityName() {
        return "Maintenance";
    }

    @Override
    protected Long getId(Maintenance entity) {
        return entity.getIdMaintenance();
    }

    @Override
    protected void setFields(Maintenance source, Maintenance target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setDescription(source.getDescription());
        target.setVehicule(source.getVehicule());
    }
}
