package com.BNPL.service;

import com.BNPL.dto.*;
import com.BNPL.entities.Client;
import com.BNPL.entities.CreditLine;
import com.BNPL.entities.Loan;
import com.BNPL.repository.ClientRepository;
import com.BNPL.repository.CreditLineRepository;
import com.BNPL.repository.InstallmentRepository;
import com.BNPL.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepo;

    private final CreditLineRepository creditRepo;

    private final LoanRepository loanRepo;

    private final InstallmentRepository installmentRepo;

    public ClientService(ClientRepository clientRepo, CreditLineRepository creditRepo, LoanRepository loanRepo,
                         InstallmentRepository installmentRepo) {
        this.clientRepo = clientRepo;
        this.creditRepo = creditRepo;
        this.loanRepo = loanRepo;
        this.installmentRepo = installmentRepo;
    }

    public ClientResponseDTO registerClient(ClientRequestDTO request) {
        int age = Period.between(request.birthDate(), LocalDate.now()).getYears();
        if (age < 18 || age > 65)
            throw new IllegalArgumentException("Client not eligible due to age");

        double creditAmount = age <= 25 ? 3000 : age <= 30 ? 5000 : 8000;

        Client client = new Client();
        client.setName(request.name());
        client.setBirthDate(request.birthDate());
        client = clientRepo.save(client);

        CreditLine credit = new CreditLine();
        credit.setAmount(creditAmount);
        credit.setAvailable(creditAmount);
        credit.setClient(client);
        creditRepo.save(credit);

        return new ClientResponseDTO(client.getId(), creditAmount);
    }

    public ClientDetailsDTO getClientById(Integer id) {
        Client clientDetails = clientRepo.findById(id);
        CreditLine clientCreditDetails = creditRepo.findByClientId(id);
        List<Loan> loanDetails = loanRepo.findAllByClientId(id);

        List<ClientLoanDetailsDTO> clientAllLoansDetails = new ArrayList<>();
        loanDetails.forEach(loan -> clientAllLoansDetails.add(new ClientLoanDetailsDTO(loan.getAmount(),
                loan.getCommission(), loan.getTotalAmount(),
                loan.getPurchaseDate(), loan.getScheme(), installmentRepo.findAllByLoanId(loan.getId().intValue()))));

        return new ClientDetailsDTO(clientDetails.getName(),
                clientDetails.getBirthDate(), new CreditDetailsResponseDTO(clientCreditDetails.getAmount(),
        clientCreditDetails.getAvailable()), clientAllLoansDetails);
    }
}
