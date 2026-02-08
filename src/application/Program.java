package application;

import model.entities.Contract;
import model.entities.Installments;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com os dados do contrato:");

        System.out.print("Numero: ");
        Integer number = sc.nextInt();

        System.out.print("Data (dd/mm/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);

        System.out.print("Valor do contrato: ");
        Double totalValue = sc.nextDouble();

        System.out.print("Entre com o numero de parcelas: ");
        Integer installmentNumber = sc.nextInt();

        Contract ct = new Contract(number,date,totalValue);

        ContractService cs = new ContractService(new PaypalService());
        cs.processContract(ct,installmentNumber);

        System.out.println("Parcelas: ");
        for (Installments installment : ct.getInstallments()) {
            System.out.println(installment);
        }
        sc.close();
    }
}
