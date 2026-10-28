package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;

@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement, Long> implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Equipement equipement, boolean isNew) {
        if (equipement == null) {
            return false;
        }
        if (isNew && equipement.getIdEquipement() != null) {
            return false;
        }
        return Utils.isNotEmpty(equipement.getLibelle());
    }

    @Override
    protected String getEntityName() {
        return "Equipement";
    }

    @Override
    protected Long getId(Equipement entity) {
        return entity.getIdEquipement();
    }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        target.setLibelle(source.getLibelle());
    }
}
