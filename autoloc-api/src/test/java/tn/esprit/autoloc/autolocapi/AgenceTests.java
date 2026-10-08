package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class AgenceTests {

    @Autowired
    AgenceRepositoryMock agenceRepository;

    @Test
    void addAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("123 TUN 1");
        v1.setMarque("Peugeot");
        v1.setModele("208");
        v1.setCategorie(CategorieVehicule.ECONOMIQUE);
        v1.setTarifJournalier(new BigDecimal("80.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("456 TUN 2");
        v2.setMarque("Renault");
        v2.setModele("Clio");
        v2.setCategorie(CategorieVehicule.ECONOMIQUE);
        v2.setTarifJournalier(new BigDecimal("75.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setAgence(agence);

        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        agenceRepository.save(agence);
    }

    @Test
    void loadAgence() {
        StringBuilder sb = new StringBuilder();

        for (Agence a : agenceRepository.findAll()) {
            sb.append("Agence id : ").append(a.getIdAgence()).append("\n");
            sb.append("Nom : ").append(a.getNom()).append("\n");
            sb.append("Nombre de véhicules : ").append(a.getVehicules().size()).append("\n");

            for (Vehicule v : a.getVehicules()) {
                sb.append("   Véhicule id : ").append(v.getIdVehicule())
                        .append(" - Immatriculation : ").append(v.getImmatriculation())
                        .append("\n");
            }
            sb.append("\n");
        }

        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}
