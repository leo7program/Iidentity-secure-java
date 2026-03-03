package ui;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;

public class SystemInfoPanel extends JPanel {

    public SystemInfoPanel() {

        setBackground(new Color(18, 18, 28));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Informações do Sistema", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 28));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        content.add(createCard("Processador",
                "Arquitetura: " + System.getProperty("os.arch") +
                        "\nNúcleos: " + Runtime.getRuntime().availableProcessors()));
        content.add(Box.createVerticalStrut(15));

        content.add(createCard("Memória",
                "Total: " + getTotalMemory() + " MB" +
                        "\nLivre: " + getFreeMemory() + " MB"));
        content.add(Box.createVerticalStrut(15));

        content.add(createCard("Sistema Operacional",
                "Nome: " + System.getProperty("os.name") +
                        "\nVersão: " + System.getProperty("os.version")));
        content.add(Box.createVerticalStrut(15));

        content.add(createCard("Rede",
                "IP Local: " + getIP() +
                        "\nUsuário: " + System.getProperty("user.name")));

        add(content, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String content) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(35, 35, 55));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(new Color(70, 130, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);

        return panel;
    }

    private long getTotalMemory() {
        return Runtime.getRuntime().totalMemory() / (1024 * 1024);
    }

    private long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

    private String getIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "Não disponível";
        }
    }
}