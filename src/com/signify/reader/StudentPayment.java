package com.signify.reader;

import java.util.Scanner;
import java.util.UUID;

import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.Student;
import com.signify.collection.UserSession;

public class StudentPayment {
    private String mainPrompt = "1. ONLINE PAYMENT\n2. OFFLINE PAYMENT\n";
    private String onlinePrompt = "\n1. PAY THROUGH CARD\n2. PAY THROUGH NETBANKING \n3. GO BACK";
    private String offlinePrompt = "\n1. PAY THROUGH CASH\n2. PAY THROUGH CHEQUE\n3. GO BACK";
    private String wrongInput = "Wrong Input , Try Again";

    public Payment requestPayment(double totalFee) {
        Scanner sc = new Scanner(System.in);
        int mode = 0;
        Student student = (Student) UserSession.user;
        String studentId = student.getStudentId();

        while (true) {
            System.out.println(mainPrompt);
            try {
                mode = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(wrongInput);
                continue;
            }

            online: if (mode == 1) {
                System.out.println(onlinePrompt);
                int onlineMode;
                try {
                    onlineMode = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(wrongInput);
                    continue;
                }

                if (onlineMode == 1) {
                    // card payment
                    System.out.print("Enter Card Number : ");
                    String cardNumber = sc.nextLine();
                    System.out.print("Enter Card Type : ");
                    String cardType = sc.nextLine();
                    String referenceId = UUID.randomUUID().toString();

                    Payment online = new OnlinePayment(1, cardNumber, cardType, "NA", "NA", "NA");

                    online.setAmount(totalFee);
                    online.setMode("ONLINE");
                    online.setStatus(1);
                    online.setStudentId(studentId);
                    online.setReferenceId(referenceId);

                    return online;
                } else if (onlineMode == 2) {
                    // net banking payment
                    System.out.print("Enter Mode of transfer : ");
                    String transferMode = sc.nextLine();
                    System.out.print("Enter Account Number : ");
                    String accountNumber = sc.nextLine();
                    System.out.print("Enter IFSC Code : ");
                    String ifsc = sc.nextLine();
                    String referenceId = UUID.randomUUID().toString();

                    Payment online = new OnlinePayment(0, "NA", "NA", accountNumber, ifsc, transferMode);

                    online.setAmount(totalFee);
                    online.setMode("ONLINE");
                    online.setStatus(1);
                    online.setStudentId(studentId);
                    online.setReferenceId(referenceId);

                    return online;
                } else {
                    System.out.println(wrongInput);
                    break online;
                }

            } else if (mode == 2) {
                System.out.println(offlinePrompt);
                int offlineMode;
                try {
                    offlineMode = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(wrongInput);
                    continue;
                }

                if (offlineMode == 1) {
                    // cash payment
                    String referenceId = UUID.randomUUID().toString();

                    Payment offline = new OfflinePayment(1, 0, "NA");

                    offline.setAmount(totalFee);
                    offline.setMode("OFFLINE");
                    offline.setStatus(1);
                    offline.setStudentId(studentId);
                    offline.setReferenceId(referenceId);

                    return offline;
                } else if (offlineMode == 2) {
                    // cheque payment
                    System.out.print("Enter Cheque Number: ");
                    int chequeNumber;
                    try {
                        chequeNumber = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Cheque Number");
                        break online;
                    }
                    System.out.println("Enter Bank Name");
                    String bankName = sc.nextLine();

                    Payment offline = new OfflinePayment(0, chequeNumber, bankName);

                    offline.setAmount(totalFee);
                    offline.setMode("OFFLINE");
                    offline.setStatus(1);
                    offline.setStudentId(studentId);

                    return offline;
                } else {
                    System.out.println(wrongInput);
                    break online;
                }
            }
        }
    }
}
