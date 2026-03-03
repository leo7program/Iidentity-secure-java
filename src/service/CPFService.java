package service;

import model.Person;
import java.util.HashMap;
import java.util.Map;

public class CPFService {
    private static Map<String, Person> fakeDatabase = new HashMap<>();

    static {
        fakeDatabase.put("12345678909", new Person("12345678909", "Leonardo Ferrer"));
        fakeDatabase.put("98765432100", new Person("98765432100", "Maria Oliveira"));
    }

    public static boolean isValidCPF(String cpf) {

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++)
                sum += (cpf.charAt(i) - '0') * (10 - i);

            int digit1 = 11 - (sum % 11);
            digit1 = (digit1 >= 10) ? 0 : digit1;

            sum = 0;
            for (int i = 0; i < 10; i++)
                sum += (cpf.charAt(i) - '0') * (11 - i);

            int digit2 = 11 - (sum % 11);
            digit2 = (digit2 >= 10) ? 0 : digit2;

            return digit1 == (cpf.charAt(9) - '0') &&
                    digit2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }

    public static Person findPerson(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        return fakeDatabase.get(cpf);
    }
}
