package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;
import tn.esprit.autoloc.autolocapi.service.IGenericService;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
@Tag(name = "Équipements")
public class EquipementController extends GenericRestController<Equipement, Long> {

    private final IEquipementService equipementService;

    @Override
    protected IGenericService<Equipement, Long> getService() {
        return equipementService;
    }
}
