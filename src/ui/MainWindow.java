package ui;

import service.CPFService;
import model.Person;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {


    private JTextField cpfField;
    private JLabel resultLabel;
    private JLabel nameLabel;

    public MainWindow() {

        setTitle("IdentitySecure - Consulta Simulada");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(20, 20, 30));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Consulta de CPF", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(20, 20, 30));
        centerPanel.setLayout(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        cpfField = new JTextField();
        cpfField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton searchButton = new JButton("Consultar");
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setBackground(new Color(70, 130, 255));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(Color.WHITE);

        centerPanel.add(new JLabel("Digite o CPF:", SwingConstants.CENTER));
        centerPanel.add(cpfField);
        centerPanel.add(searchButton);
        centerPanel.add(resultLabel);
        centerPanel.add(nameLabel);

        add(centerPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> consultar());

        setVisible(true);
    }

    private void consultar() {

        String cpf = cpfField.getText();

        if (!CPFService.isValidCPF(cpf)) {
            resultLabel.setText("CPF inválido");
            resultLabel.setForeground(Color.RED);
            nameLabel.setText("");
            return;
        }

        Person person = CPFService.findPerson(cpf);

        if (person != null) {
            resultLabel.setText("CPF encontrado");
            resultLabel.setForeground(new Color(0, 200, 0));
            nameLabel.setText("Nome: " + person.getName());
        } else {
            resultLabel.setText("CPF válido, mas não cadastrado");
            resultLabel.setForeground(Color.ORANGE);
            nameLabel.setText("");
        }
    }
}
