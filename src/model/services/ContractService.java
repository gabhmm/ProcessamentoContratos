package model.services;

import model.entities.Contract;
import model.entities.Installments;

import java.time.LocalDate;
import java.util.Date;

public class ContractService {

    private OnlinePaymentService paymentService;


    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public OnlinePaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer months) {

        Double basicInstallmentValue = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i ++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            Double interest = paymentService.interest(basicInstallmentValue, i);
            Double paymentFee = paymentService.paymentFee(basicInstallmentValue + interest);

            Double totalValue = basicInstallmentValue + interest + paymentFee;


            Installments installment = new Installments(dueDate,totalValue);
            contract.getInstallments().add(installment);
        }

    }

}
