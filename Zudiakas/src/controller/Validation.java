package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String VALID_ID_REGEX ="^[0-9]{1,7}$";
    private static final String VALID_CREDENTIALS_REGEX ="^[A-Za-z0-9.-]{5,13}$";
    private static final String VALID_ADD_REGEX ="^[0-9a-zA-Z\\. ]+$";
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    //private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static boolean isValidID(String ID){
        Pattern IDPattern = Pattern.compile(VALID_ID_REGEX);
        Matcher IDMatcher = IDPattern.matcher(ID);
        return IDMatcher.find();
    }

    public static boolean isValidCredentials(String credentials){
        Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_REGEX);
        Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
        return credentialsMatcher.find();
    }

    public static boolean isValidRsnForAdd(String Pokemon){
        Pattern PokemonNamePattern = Pattern.compile(VALID_ADD_REGEX);
        Matcher PokemonNameMatcher = PokemonNamePattern.matcher(Pokemon);
        return PokemonNameMatcher.find();
    }

    public static boolean isValidEmail(String email){
        Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }
}
