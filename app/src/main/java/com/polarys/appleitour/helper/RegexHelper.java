package com.polarys.appleitour.helper;
import java.util.regex.*;
public class RegexHelper {
    public final static String RGX_NUMBER = "^(?=.*[0-9])";
    public final static String RGX_NUMBER_ERROR = "A senha precisa conter um número";
    public final static String RGX_LOWERCASE = "(?=.*[a-z])";
    public final static String RGX_LOWERCASE_ERROR = "A senha precisa ter uma letra minuscula";
    public final static String RGX_UPPERCASE = "(?=.*[A-Z])(?=.*\\W)";
    public final static String RGX_UPPERCASE_ERROR = "A senha precisa ter uma letra maiúscula";
    public final static String RGX_SPECIALCHARACTER = "(?!.* )";
    public final static String RGX_SPECIALCHARACTER_ERROR = "A senha precisa ter um caracter especial";
    public final static String RGX_PASSWORDSIZE = ".{8,64}";
    public final static String RGX_PASSWORDSIZE_ERROR = "A senha precisa ter entre 8 e 64 caractéres";
    public final static String SUCCESS = "SUCCESS";

    public boolean verifyUserName(String username){
        final String REGEX_USERNAME=  "(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,30}";
        return verifyRegex(REGEX_USERNAME,username);
    }
    public boolean verifyEmail(String email){
        return true;
     //   final String REGEX_EMAIL =  "([-!#-'*+/-9=?A-Z^-~]+(\\.[-!#-'*+/-9=?A-Z^-~]+)*|\"([]!#-[^-~ \\t]|(\\\\[\\t -~]))+\")@([0-9A-Za-z]([0-9A-Za-z-]{0,61}[0-9A-Za-z])?(\\.[0-9A-Za-z]([0-9A-Za-z-]{0,61}[0-9A-Za-z])?)*|\\[((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|IPv6:((((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){6}|::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){5}|[0-9A-Fa-f]{0,4}::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){4}|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):)?(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){3}|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,2}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){2}|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,3}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,4}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::)((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3})|(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3})|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,5}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3})|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,6}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::)|(?!IPv6:)[0-9A-Za-z-]*[0-9A-Za-z]:[!-Z^-~]+)])";
     //   return verifyRegex(REGEX_EMAIL,email);
    }
    public String verifyPassword(String password){
        String REGEX_PASSWORD = RGX_NUMBER;
        if(!verifyRegex(REGEX_PASSWORD,password))
            return RGX_NUMBER_ERROR;
        REGEX_PASSWORD += RGX_LOWERCASE;
        if(!verifyRegex(REGEX_PASSWORD,password))
            return RGX_LOWERCASE_ERROR;
        REGEX_PASSWORD += RGX_UPPERCASE;
        if(!verifyRegex(REGEX_PASSWORD,password))
            return RGX_UPPERCASE_ERROR;
        REGEX_PASSWORD += RGX_SPECIALCHARACTER;
        if(!verifyRegex(REGEX_PASSWORD,password))
            return RGX_SPECIALCHARACTER_ERROR;
        REGEX_PASSWORD += RGX_PASSWORDSIZE;
        if(!verifyRegex(REGEX_PASSWORD,password))
            return RGX_PASSWORDSIZE_ERROR;
        return SUCCESS;
    }
    private boolean verifyRegex(String regex,String compare){
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(compare).matches();
    }
}
