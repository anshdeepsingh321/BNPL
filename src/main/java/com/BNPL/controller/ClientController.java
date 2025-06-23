package com.BNPL.controller;

import com.BNPL.dto.ClientDetailsDTO;
import com.BNPL.dto.ClientRequestDTO;
import com.BNPL.dto.ClientResponseDTO;
import com.BNPL.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Tag(name = "POST", description = "Post to register new client")
    @Operation(summary = "Post to register new client",
            description = "Return client details with his credit limit")
    @PostMapping
    public ResponseEntity < ClientResponseDTO > register(@RequestBody ClientRequestDTO request) {
        return ResponseEntity.ok(clientService.registerClient(request));
    }

    @Tag(name = "GET", description = "GET method to get client data by Id")
    @Operation(summary = "Get client data by id",
            description = "Return client details")
    @GetMapping("/{id}")
    public ResponseEntity < ClientDetailsDTO > getClientById(@PathVariable Integer id) {
        return ResponseEntity.of(Optional.of(clientService.getClientById(id)));
    }
}