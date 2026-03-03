package model;

public class Person {
    private String cpf;
    private String name;

    public Person(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }
}