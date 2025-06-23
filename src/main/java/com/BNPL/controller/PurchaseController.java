package com.BNPL.controller;

import com.BNPL.dto.PurchaseRequestDTO;
import com.BNPL.dto.PurchaseResponseDTO;
import com.BNPL.service.ClientService;
import com.BNPL.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @Tag(name = "POST", description = "Post to make new purchase")
    @Operation(summary = "Post to register new purchase",
            description = "Return purchase id")
    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> purchase(@RequestBody PurchaseRequestDTO request) {
        return ResponseEntity.ok(purchaseService.registerPurchase(request));
    }
}
