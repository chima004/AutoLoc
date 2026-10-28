package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements IClientService {

    public ClientServiceImpl(IClientRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Client client, boolean isNew) {
        if (client == null) {
            return false;
        }
        if (isNew && client.getIdClient() != null) {
            return false;
        }
        return Utils.isNotEmpty(client.getNom())
                && Utils.isNotEmpty(client.getPrenom())
                && Utils.isNotEmpty(client.getEmail())
                && Utils.isNotEmpty(client.getNumPermis())
                && client.getDateInscription() != null;
    }

    @Override
    protected String getEntityName() {
        return "Client";
    }

    @Override
    protected Long getId(Client entity) {
        return entity.getIdClient();
    }

    @Override
    protected void setFields(Client source, Client target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setEmail(source.getEmail());
        target.setTelephone(source.getTelephone());
        target.setNumPermis(source.getNumPermis());
        target.setDateInscription(source.getDateInscription());
    }
}
