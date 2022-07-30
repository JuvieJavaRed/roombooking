package com.coindirect.recruitment.utility;

public class UtilityMethods {

    public static boolean validatedInput(String row, String column){
        boolean isValid = true;
        if(row.isEmpty() || row.length() == 0){
            isValid = false;
        }else if(column.isEmpty() || column.length() == 0){
            isValid = false;
        }
        else if(!isNumeric(row) || isNumeric(column)){
            isValid = false;
        }
        return isValid;
    }

    public static boolean isNumeric(String rowOrColumnPassed) {
        if (rowOrColumnPassed == null) {
            return false;
        }
        try {
            int parsedNumber = Integer.parseInt(rowOrColumnPassed);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
