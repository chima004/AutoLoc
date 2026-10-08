package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AgenceTests {

    // Étape 2 : renommé en basicAgenceRepository
    @Autowired
    AgenceRepositoryMock basicAgenceRepository;

    // Étape 3 : nouvel attribut
    @Autowired
    IAgenceRepository fullAgenceRepository;

    // Étapes 4, 5, 6 : paramètre repository, suffixe, méthode privée sans @Test
    private void addAgence(CrudRepository<Agence, Long> repository) {
        int suffixe = (int) System.currentTimeMillis();

        Agence agence = new Agence();
        agence.setNom("Agence Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("123 TUN " + suffixe);
        v1.setMarque("Peugeot");
        v1.setModele("208");
        v1.setCategorie(CategorieVehicule.ECONOMIQUE);
        v1.setTarifJournalier(new BigDecimal("80.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("456 TUN " + suffixe);
        v2.setMarque("Renault");
        v2.setModele("Clio");
        v2.setCategorie(CategorieVehicule.ECONOMIQUE);
        v2.setTarifJournalier(new BigDecimal("75.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setAgence(agence);

        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        repository.save(agence);
    }

    // Étape 7 : deux tests d'ajout
    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // Étape 10 : chargement avec affichage console et type de dépôt
    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        StringBuilder sb = new StringBuilder();
        sb.append("==============================\n");
        sb.append("Type de dépôt : ").append(typeDepot).append("\n");
        sb.append("==============================\n\n");

        int nbAgences = 0;

        for (Agence a : repository.findAll()) {
            nbAgences++;
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

        sb.append("Nombre total d'agences : ").append(nbAgences).append("\n");

        System.out.println(sb);   // affichage dans la console
    }

    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "Basic (CrudRepository)");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "Full (JpaRepository)");
    }

    // ===== PARTIE II : Questions 11 à 14 =====
    @Test
    void loadSortedAgences() {
        // Q12 : toutes les agences triées par id décroissant
        Sort sort = Sort.by(Sort.Direction.DESC, "idAgence");
        List<Agence> agences = fullAgenceRepository.findAll(sort);

        // Q13 : affichage sans les détails des véhicules
        StringBuilder sb = new StringBuilder();
        sb.append("===== Agences triées par id décroissant =====\n");
        for (Agence a : agences) {
            sb.append("Agence id : ").append(a.getIdAgence())
                    .append(" - Nom : ").append(a.getNom())
                    .append(" - Ville : ").append(a.getVille())
                    .append("\n");
        }

        System.out.println(sb);
    }

    // ===== PARTIE II : Questions 15 à 18 =====
    @Test
    void loadPagedAgences() {
        while (fullAgenceRepository.count() < 2) {
            addAgence(fullAgenceRepository);
        }

        Page<Agence> premierePage = fullAgenceRepository.findAll(
                PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "idAgence")));
        assertEquals(2, premierePage.getSize(), "La pagination doit contenir deux agences par page.");
        assertFalse(premierePage.getContent().isEmpty(), "Aucune agence trouvée.");

        int nombreTotalPages = premierePage.getTotalPages();
        System.out.println("Nombre total de pages : " + nombreTotalPages);

        for (int numeroPage = 0; numeroPage < nombreTotalPages; numeroPage++) {
            Page<Agence> page = fullAgenceRepository.findAll(
                    PageRequest.of(numeroPage, 2, Sort.by(Sort.Direction.DESC, "idAgence")));
            assertFalse(page.getContent().isEmpty(), "La page " + (numeroPage + 1) + " est vide.");
            for (int i = 1; i < page.getContent().size(); i++) {
                assertTrue(page.getContent().get(i - 1).getIdAgence() > page.getContent().get(i).getIdAgence(),
                        "Les agences de la page ne sont pas triées par id décroissant.");
            }

            System.out.println("Page en cours : " + (page.getNumber() + 1));
            System.out.println("Nombre d'agences dans cette page : " + page.getNumberOfElements());
            System.out.println("Agences de cette page :");
            for (Agence a : page.getContent()) {
                System.out.println("   id=" + a.getIdAgence()
                        + ", nom=" + a.getNom()
                        + ", ville=" + a.getVille()
                        + ", adresse=" + a.getAdresse());
            }
            System.out.println();
        }
    }
}

@Repository
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}