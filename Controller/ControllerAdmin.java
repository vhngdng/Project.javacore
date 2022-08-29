package Controller;

import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import Model.Admin;
import Model.Transaction;
import View.AdminAccessView;
import View.GuestAccessView;

public class ControllerAdmin {
    public Admin admin;


    public static void sendAdminVerify(Map<Integer, Transaction> requestList) {
        JSONObject jsonObject = new JSONObject();
        JSONObject[] listJson = new JSONObject[requestList.size()];
        Set<Map.Entry<Integer, Transaction>> s = requestList.entrySet();
        int count = 0;

        /* 
        
        
        
        Đoạn này cần viết lại, get attribute of requestList then convert them to Json





        */
        
        for (Map.Entry<Integer, Transaction> setList : s) {
            jsonObject.put("requestCount", setList.getKey());
            jsonObject.put("transactionSending", setList.getValue());
            listJson[count++] = jsonObject; 
        }


    }

    
}
