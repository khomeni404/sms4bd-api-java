package net.sms4bd.api;

import java.util.Date;

/**
 * Copyright &copy; Soft Engine
 * <p>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 12:06 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public class SMSTest {

    public static void main(String[] args) {
        SMSTest.sendSMS("8801717659287", "Hello SMS", DateTime.getTimeUTC(new Date()));
    }

    public static void sendSMS(String number, String text, String time) {
        SMSClient client = new SMSClient("122114727", "1598100899", "www.sms4bd.net", "/HTTP.svc/SendSMS");
        SMSResponse result = client.sendSMS(number, text, Mask.KHOMENI, time, SMSType.ASCII);

        if (result.getStatusCode() == StatusCode.ACCEPTED) {
            System.out.println("Success : " + result.getStatusMessage());
            // Store log to database or do anything else
        } else {
            System.out.println("Fail : " + result.getStatusMessage());
            // Store log to database or do anything else
        }
    }

}
