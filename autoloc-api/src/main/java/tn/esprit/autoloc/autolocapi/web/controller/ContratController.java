package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.service.IContratService;
import tn.esprit.autoloc.autolocapi.service.IGenericService;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats")
public class ContratController extends GenericRestController<Contrat, Long> {

    private final IContratService contratService;

    @Override
    protected IGenericService<Contrat, Long> getService() {
        return contratService;
    }
}
