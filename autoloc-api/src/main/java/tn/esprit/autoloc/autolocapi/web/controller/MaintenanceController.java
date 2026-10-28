package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.service.IGenericService;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances")
public class MaintenanceController extends GenericRestController<Maintenance, Long> {

    private final IMaintenanceService maintenanceService;

    @Override
    protected IGenericService<Maintenance, Long> getService() {
        return maintenanceService;
    }
}
