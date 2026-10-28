package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;
import tn.esprit.autoloc.autolocapi.service.IGenericService;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
@Tag(name = "Employés")
public class EmployeController extends GenericRestController<Employe, Long> {

    private final IEmployeService employeService;

    @Override
    protected IGenericService<Employe, Long> getService() {
        return employeService;
    }
}
