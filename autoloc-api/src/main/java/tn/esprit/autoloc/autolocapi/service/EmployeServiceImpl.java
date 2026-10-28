package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employe, Long> implements IEmployeService {

    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Employe employe, boolean isNew) {
        if (employe == null) {
            return false;
        }
        if (isNew && employe.getIdEmploye() != null) {
            return false;
        }
        return Utils.isNotEmpty(employe.getNom())
                && Utils.isNotEmpty(employe.getPrenom())
                && employe.getRole() != null;
    }

    @Override
    protected String getEntityName() {
        return "Employe";
    }

    @Override
    protected Long getId(Employe entity) {
        return entity.getIdEmploye();
    }

    @Override
    protected void setFields(Employe source, Employe target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setRole(source.getRole());
        target.setAgence(source.getAgence());
    }
}
