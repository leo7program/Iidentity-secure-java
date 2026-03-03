package ui;

import service.CPFService;
import service.PortScannerService;
import model.Person;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JTextField cpfField;
    private JLabel resultLabel;
    private JLabel nameLabel;

    public MainWindow() {

        setTitle("IdentitySecure");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Criando sistema de abas
        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Consulta CPF", createCPFPanel());
        tabs.add("Análise de Portas", createPortPanel());
        tabs.add("Sistema", new SystemInfoPanel());

        add(tabs);

        setVisible(true);
    }

    // =========================
    // ABA 1 - CONSULTA CPF
    // =========================
    private JPanel createCPFPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(20, 20, 30));

        JLabel title = new JLabel("Consulta de CPF", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        panel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBackground(new Color(20, 20, 30));
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

        panel.add(centerPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> consultar());

        return panel;
    }

    // =========================
    // ABA 2 - SCANNER DE PORTAS
    // =========================
    private JPanel createPortPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel top = new JPanel();
        JTextField hostField = new JTextField(20);
        JButton scanButton = new JButton("Escanear");

        top.add(new JLabel("Host:"));
        top.add(hostField);
        top.add(scanButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        scanButton.addActionListener(e -> {

            String host = hostField.getText();
            int[] ports = {21, 22, 80, 443};

            StringBuilder result = new StringBuilder();
            result.append("Resultados para ").append(host).append("\n\n");

            for (int port : ports) {
                boolean open = PortScannerService.isPortOpen(host, port, 200);
                result.append("Porta ").append(port)
                        .append(open ? " ABERTA\n" : " fechada\n");
            }


            resultArea.setText(result.toString());
        });

        return panel;
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