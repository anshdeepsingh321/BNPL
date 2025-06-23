package com.BNPL;

import com.BNPL.dto.ClientRequestDTO;
import com.BNPL.dto.ClientResponseDTO;
import com.BNPL.service.ClientService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ClientServiceTest {
    private ClientService clientService;

    @Test
    void testRegisterValidClient() {
        ClientRequestDTO req = new ClientRequestDTO("Alice", LocalDate.of(1993, 5, 1));
        ClientResponseDTO resp = clientService.registerClient(req);
        assertNotNull(resp);
        assertTrue(resp.assignedCreditAmount() > 0);
    }
}
