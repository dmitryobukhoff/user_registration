package ru.dmitryobukhoff.validators;

import ru.dmitryobukhoff.dtos.ValidatorDto;
import ru.dmitryobukhoff.interfaces.Validator;
import ru.dmitryobukhoff.models.Person;

public class PersonValidator implements Validator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SIGNS = "!$&?*@^_-+=.,";
    private static final String LOGIN_ERROR_NO_CREATED =
            "Логин должен состоять только из латинских букв и цифр и иметь длину от 8 до 20 символов";
    private static final String LOGIN_ERROR_CREATED = "Такой логин уже существует.";
    private static final String EMAIL_ERROR_NO_CREATED =
            "Неправильный адрес электрнной почты";
    private static final String EMAIL_ERROR_CREATED = "Пользоваель с такой электронной почтой уже зарегесрирован.";
    private static final String PASSWORD_ERROR_NO_CREATED = "Пароль должен содержать: латинские буквы," +
            " цифры и специальные символы ('!','$','&','?','*','@','^','_','-','+','=','.',',')";
    public String MESSAGE = "";

    @Override
    public boolean isValid(Object object) {
        ValidatorDto validatorDto = new ValidatorDto();
        boolean isCompare = true;
        Person person = (Person) object;
        String login = person.getLogin();
        String email = person.getEmail();
        String password = person.getPassword();
        if(validatorDto.getPersonByLogin(login) != null){
            System.out.println("Логин есть");
            isCompare = false;
            MESSAGE = LOGIN_ERROR_CREATED;
        }else if(validatorDto.getPersonByEmail(email) != null){
            System.out.println("Почта есть");
            isCompare = false;
            MESSAGE = EMAIL_ERROR_CREATED;
        }else if(!isLoginValid(login)){
            System.out.println("Логин невалидный");
            isCompare = false;
            MESSAGE = LOGIN_ERROR_NO_CREATED;
        }else if(!isEmailValid(email)){
            System.out.println("Почта невалидная");
            isCompare = false;
            MESSAGE = EMAIL_ERROR_NO_CREATED;
        }else if(!isPasswordValid(password)){
            System.out.println("Пароль невалидный");
            isCompare = false;
            MESSAGE = PASSWORD_ERROR_NO_CREATED;
        }
        return isCompare;
    }

    private boolean isLoginValid(String login){
        boolean flag = true;
        if(login.length() < 8 || login.length() > 20){
            flag = false;
        }else{
            for(int i = 0; i < login.length(); i++){
                String letter = Character.toString(login.charAt(i));
                if(SIGNS.contains(letter))
                    flag = false;
            }
        }
        return flag;
    }

    private boolean isEmailValid(String email){
        boolean flag = false;
        for(int i = 0; i < email.length(); i++){
            String sign = Character.toString(email.charAt(i));
            if(sign.equals("@"))
                flag = true;
        }
        return flag;
    }

    private boolean isPasswordValid(String password){
        boolean containLetter = false;
        boolean containNumber = false;
        boolean containSign = false;
        for(int i = 0; i < password.length(); i++){
            String string = Character.toString(password.charAt(i));
            if(ALPHABET.contains(string))
                containLetter = true;
            else if(NUMBERS.contains(string))
                containNumber = true;
            else
                containSign = true;
        }
        return (containNumber && containLetter && containSign);
    }
}
