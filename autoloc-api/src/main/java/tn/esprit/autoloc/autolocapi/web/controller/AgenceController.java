
package tn.esprit.autoloc.autolocapi.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceController {

    @GetMapping
    public List<?> findAll() {
        return List.of();
    }
}
