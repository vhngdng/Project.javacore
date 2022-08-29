package service;

import java.util.Map;

import Controller.ControllerAdmin;
import Model.Transaction;
import Model.TransactionSending;
import repository.AdminRequestRepository;

public class AdminService {
   public AdminService() {

   }

   public void sendNotification() {

   }

   public static void sendAdminRequestRepository(Transaction transactionSending) {
      Map<Integer, TransactionSending> requestList = AdminRequestRepository.addRequest(transactionSending);
      ControllerAdmin.sendAdminVerify(requestList);
   }
}
