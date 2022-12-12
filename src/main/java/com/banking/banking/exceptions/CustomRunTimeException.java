package com.banking.banking.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRunTimeException extends RuntimeException{

    private static final long serialVersionUID = 4825854136668962900L;

    public static String format(final String str,Object... values) {
        synchronized(str.intern()) {
            Pattern pattern = Pattern.compile("\\{[^}]*}", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(str);
            int index= 0;
            String output = str;
            while (matcher.find()) {
                StringBuilder stringBuilder= new StringBuilder(output);
                String value = "";
                if(values.length > index) {
                    value= String.valueOf(values[index]);
                }else {
                    break;
                }
                stringBuilder.replace(matcher.start(), matcher.end(), value);
                output = stringBuilder.toString();
                matcher = pattern.matcher(output);
                index++;
            }
            return output;
        }
    }

    public CustomRunTimeException(String message, String ...values) {
        super(format(message, values));
    }


}
