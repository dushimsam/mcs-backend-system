package com.example.mount_carmel_school.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class GenerateRandomString {


    public String generateDateBasedString(){
        String dateStub = String.format("%tM%<tS%<tL", new Date());
        dateStub = dateStub.substring(0,5);
        return dateStub;
    }


    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * This  method will generate the Customer Code ...
     * * */


    public String generateStringV2(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        String generatedString = buffer.toString();
        String dateStub = String.format("%tM%<tS%<tL", new Date());

        dateStub = dateStub.substring(0,3);
        return generatedString+"-"+dateStub;
    }
}
