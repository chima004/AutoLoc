package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AgenceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crudAgence() throws Exception {
        mockMvc.perform(get("/agences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        mockMvc.perform(get("/agences/{id}", 999999L))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/agences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"idAgence":99,"nom":"Agence Invalide","ville":"Tunis","adresse":"Centre ville"}
                                """))
                .andExpect(status().isBadRequest());

        String createdJson = mockMvc.perform(post("/agences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nom":"Agence MVC","ville":"Sfax","adresse":"Route de l'aeroport"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idAgence", notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = createdJson.replaceAll(".*\"idAgence\"\\s*:\\s*(\\d+).*", "$1");

        mockMvc.perform(get("/agences/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Agence MVC")));

        mockMvc.perform(put("/agences/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nom":"Agence MVC Modifiee","ville":"Sfax","adresse":"Route de l'aeroport"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Agence MVC Modifiee")));

        mockMvc.perform(put("/agences/{id}", 999999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nom":"Agence MVC Modifiee","ville":"Sfax","adresse":"Route de l'aeroport"}
                                """))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/agences/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/agences/{id}", id))
                .andExpect(status().isNotFound());
    }
}
