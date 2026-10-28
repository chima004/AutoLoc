package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IGenericService;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Véhicules")
public class VehiculeController extends GenericRestController<Vehicule, Long> {

    private final IVehiculeService vehiculeService;

    @Override
    protected IGenericService<Vehicule, Long> getService() {
        return vehiculeService;
    }
}
