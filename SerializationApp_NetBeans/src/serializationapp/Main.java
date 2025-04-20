
package serializationapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame {
    private JTextArea textArea;

    public Main() {
        setTitle("Aplikasi Serialization & File");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new GridLayout(1, 4));

        JButton simpanSerBtn = new JButton("Simpan .ser");
        JButton muatSerBtn = new JButton("Muat .ser");
        JButton simpanTxtBtn = new JButton("Simpan .txt");
        JButton muatTxtBtn = new JButton("Muat .txt");

        panelBawah.add(simpanSerBtn);
        panelBawah.add(muatSerBtn);
        panelBawah.add(simpanTxtBtn);
        panelBawah.add(muatTxtBtn);

        add(panelBawah, BorderLayout.SOUTH);

        // Event Buttons
        simpanSerBtn.addActionListener(e -> simpanSer());
        muatSerBtn.addActionListener(e -> muatSer());
        simpanTxtBtn.addActionListener(e -> simpanTxt());
        muatTxtBtn.addActionListener(e -> muatTxt());
    }

    private void simpanSer() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(fileChooser.getSelectedFile()))) {
                oos.writeObject(textArea.getText());
                JOptionPane.showMessageDialog(this, "Berhasil disimpan sebagai .ser");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
            }
        }
    }

    private void muatSer() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fileChooser.getSelectedFile()))) {
                String data = (String) ois.readObject();
                textArea.setText(data);
                JOptionPane.showMessageDialog(this, "Berhasil dimuat dari .ser");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Gagal memuat: " + e.getMessage());
            }
        }
    }

    private void simpanTxt() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileChooser.getSelectedFile()))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Berhasil disimpan sebagai .txt");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
            }
        }
    }

    private void muatTxt() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(fileChooser.getSelectedFile()))) {
                textArea.read(reader, null);
                JOptionPane.showMessageDialog(this, "Berhasil dimuat dari .txt");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal memuat: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
