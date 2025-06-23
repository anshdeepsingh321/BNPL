package com.BNPL;

import com.BNPL.dto.PurchaseRequestDTO;
import com.BNPL.dto.PurchaseResponseDTO;
import com.BNPL.service.PurchaseService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class PurchaseServiceTest {
    private PurchaseService purchaseService;

    @Test
    void testPurchaseWithinCredit() {
        PurchaseRequestDTO req = new PurchaseRequestDTO(1L, 1000.0);
        PurchaseResponseDTO resp = purchaseService.registerPurchase(req);
        assertNotNull(resp);
    }
}
