package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Agence;

public interface IAgenceService extends IGenericService<Agence, Long> {

    boolean hasVehicules(Long id);
}
