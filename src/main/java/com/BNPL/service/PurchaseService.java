package com.BNPL.service;

import com.BNPL.dto.PurchaseRequestDTO;
import com.BNPL.dto.PurchaseResponseDTO;
import com.BNPL.entities.Client;
import com.BNPL.entities.CreditLine;
import com.BNPL.entities.Installment;
import com.BNPL.entities.Loan;
import com.BNPL.repository.ClientRepository;
import com.BNPL.repository.CreditLineRepository;
import com.BNPL.repository.InstallmentRepository;
import com.BNPL.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

    private final ClientRepository clientRepo;
    private final CreditLineRepository creditRepo;

    private final InstallmentRepository installmentRepo;
    private final LoanRepository loanRepo;

    public PurchaseService(ClientRepository clientRepo, CreditLineRepository creditRepo, LoanRepository loanRepo,
                           InstallmentRepository installmentRepo) {
        this.clientRepo = clientRepo;
        this.creditRepo = creditRepo;
        this.loanRepo = loanRepo;
        this.installmentRepo = installmentRepo;
    }

    public PurchaseResponseDTO registerPurchase(PurchaseRequestDTO request) {
        Client client = clientRepo.findById(request.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        CreditLine credit = Optional.of(creditRepo.findByClientId(Math.toIntExact(client.getId())))
                .orElseThrow(() -> new IllegalArgumentException("No credit line found"));

        if (request.amount() > credit.getAvailable()) {
            logger.error("Insufficient credit line");
            throw new IllegalArgumentException("Insufficient credit line");
        }

        String scheme = assignScheme(client);
        double rate = scheme.equals("Scheme 1") ? 0.13 : 0.16;
        double commission = request.amount() * rate;
        double total = request.amount() + commission;

        Loan loan = new Loan();
        loan.setClient(client);
        loan.setAmount(request.amount());
        loan.setCommission(commission);
        loan.setTotalAmount(total);
        loan.setPurchaseDate(LocalDate.now());
        loan.setScheme(scheme);
        loan = loanRepo.save(loan);

        logger.info("Loan information saved");

        List < Installment > installments = InstallmentCalculator.calculate(total, loan.getPurchaseDate(), loan.getId());
        installmentRepo.saveAll(installments);

        logger.info("Installment information saved");

        credit.setAvailable(credit.getAvailable() - request.amount());
        creditRepo.save(credit);

        logger.info("Credit limit updated after purchase");

        return new PurchaseResponseDTO(loan.getId());
    }

    private String assignScheme(Client client) {
        if (client.getName().toUpperCase().startsWith("C") ||
                client.getName().toUpperCase().startsWith("L") ||
                client.getName().toUpperCase().startsWith("H")) {
            return "Scheme 1";
        } else if (client.getId() > 25) {
            return "Scheme 2";
        }
        return "Scheme 2";
    }
}