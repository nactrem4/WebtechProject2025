/** findet SpringbootTest nicht
package RestcontrollerTest;

import com.example.demo.persistence.TastaturEntität;
import com.example.demo.persistence.TastaturRepository;
import com.example.demo.web.api.TastaturCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TastaturRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TastaturRepository tastaturRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        // Datenbank vor jedem Test leeren
        tastaturRepository.deleteAll();
    }

    @Test
    void testCreateAndGetTastatur() throws Exception {
        TastaturCreateRequest createRequest = new TastaturCreateRequest();
        createRequest.setTastaturName("TestBoard");
        createRequest.setModell("TB-123");
        createRequest.setSwitches("Cherry MX Red");
        createRequest.setKeycaps("PBT");
        createRequest.setBeschreibung("Eine coole Tastatur");

        // Tastatur anlegen
        mockMvc.perform(post("/tastaturen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.tastaturName").value("TestBoard"));

        // Alle Tastaturen abfragen (sollte 1 enthalten)
        mockMvc.perform(get("/tastaturen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tastaturName").value("TestBoard"));
    }

    @Test
    void testSearchTastatur() throws Exception {
        // Zwei Tastaturen anlegen
        TastaturEntität t1 = new TastaturEntität();
        t1.setTastaturName("Mechanisch");
        tastaturRepository.save(t1);

        TastaturEntität t2 = new TastaturEntität();
        t2.setTastaturName("Membran");
        tastaturRepository.save(t2);

        // Suche nach "mech" (Teil-Suche, case-insensitive)
        mockMvc.perform(get("/tastaturen").param("q", "mech"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tastaturName", containsStringIgnoringCase("mech")));
    }

    @Test
    void testUpdateAndDeleteTastatur() throws Exception {
        TastaturEntität t = new TastaturEntität();
        t.setTastaturName("OldName");
        t = tastaturRepository.save(t);

        TastaturCreateRequest updateRequest = new TastaturCreateRequest();
        updateRequest.setTastaturName("NewName");
        updateRequest.setModell("NewModel");

        // Update
        mockMvc.perform(put("/tastaturen/" + t.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tastaturName").value("NewName"))
                .andExpect(jsonPath("$.modell").value("NewModel"));

        // Delete
        mockMvc.perform(delete("/tastaturen/" + t.getId()))
                .andExpect(status().isOk());

        // Prüfen, dass Tastatur nicht mehr existiert
        mockMvc.perform(get("/tastaturen/" + t.getId()))
                .andExpect(status().isNotFound());
    }
}
 */