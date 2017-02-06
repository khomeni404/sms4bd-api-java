package net.sms4bd.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Copyright &copy; Soft Engine
 * <p/>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 11:55 AM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */


public class SMSClient {


    private final String USER_AGENT = "Chrome/41.0.2228.0"; // "Mozilla/5.0";

    private String publicKey;
    private String privateKey;
    private String authority;
    private String path;
    public String query;
    public static final String SCHEME = "http";

    public SMSClient(String publicKey, String privateKey, String authority, String path) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.authority = authority;
        this.path = path;
    }

    public SMSResponse sendSMS(String cell, String text, Mask MASK, String date, SMSType SMS_TYPE) {
        query = "publickey=" + publicKey +
                "&privatekey=" + privateKey +
                "&sender=" + MASK.NAME +
                "&receiver=" + cell +
                "&message=" + text +
                "&delay=" + date +
                "&type=" + SMS_TYPE.CODE;
        //authority = authority+"/HTTP.svc/SendSMS?";

        String resultString = "";
        SMSResponse result = new SMSResponse();
        try {
          /*  public URI(String SCHEME,
                    String authority,
                    String path, String query, String fragment)*/

            URI uri = new URI(SCHEME, authority, path, query, null);
            URL obj = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");


            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + uri);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            resultString = response.toString();
            result.setStatusCode(Integer.valueOf(resultString.substring(0, 1)));
            result.setStatusMessage(resultString);
            // System.out.println(resultString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result.setStatusCode(Integer.valueOf(resultString.substring(0, 1)));
            result.setStatusMessage(resultString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        result.setCell(cell);
        result.setMessage(text);
        return result;
    }


    public String getUSER_AGENT() {
        return USER_AGENT;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
