
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class IDCard {
    String instituteName;
    String name;
    String dob;
    String department;
    String idNumber;
    String photoPath;
    String course;
    String logoPath;

    // Constructor
    public IDCard(String instituteName, String logoPath, String photoPath, String name, String course, String dob, String department, String idNumber) {
        this.instituteName = instituteName;
        this.logoPath = logoPath;
        this.photoPath = photoPath;
        this.name = name;
        this.course = course;
        this.dob = dob;
        this.department = department;
        this.idNumber = idNumber;
    }

    // Display the ID Card
    public void displayIDCard() {
        JFrame frame = new JFrame("ID Card");
        frame.setSize(350, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        /* Header with Logo*/
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(250, 30));
        /*Logo section*/
        JLabel logoLabel = new JLabel();
        if (logoPath != null && !logoPath.isEmpty()) {
            ImageIcon logoIcon = new ImageIcon(new ImageIcon(logoPath).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH));
            logoLabel.setIcon(logoIcon);
        }
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel headerTextLabel = new JLabel("INSTITUTE ID CARD", SwingConstants.CENTER);
        headerTextLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        headerTextLabel.setForeground(Color.WHITE);

        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(headerTextLabel, BorderLayout.CENTER);

        // Institute Name
        JLabel instituteLabel = new JLabel(instituteName.toUpperCase(), SwingConstants.CENTER);
        instituteLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        instituteLabel.setForeground(new Color(25, 25, 112));
        instituteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Photo Section
        JLabel photoLabel = new JLabel();
        if (photoPath != null && !photoPath.isEmpty()) {
            ImageIcon photoIcon = new ImageIcon(new ImageIcon(photoPath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            photoLabel.setIcon(photoIcon);
        } else {
            photoLabel.setText("No Photo");
            photoLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
            photoLabel.setForeground(Color.GRAY);
        }
        photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name
        JLabel nameLabel = new JLabel(name.toUpperCase(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        nameLabel.setForeground(new Color(220, 20, 60));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ID and Course
        JLabel idLabel = new JLabel("ID: " + idNumber, SwingConstants.CENTER);
        idLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel courseLabel = new JLabel("Course: " + course, SwingConstants.CENTER);
        courseLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        courseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DOB and Department
        JLabel dobLabel = new JLabel("DOB: " + dob, SwingConstants.CENTER);
        dobLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        dobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel departmentLabel = new JLabel("Department: " + department, SwingConstants.CENTER);
        departmentLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        departmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Footer
        JLabel footerLabel = new JLabel("Authorized by " + instituteName, SwingConstants.CENTER);
        footerLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(25, 25, 112));
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Save Button
        JButton saveButton = new JButton("Save as Image");
        saveButton.setFont(new Font("Verdana", Font.BOLD, 12));
        saveButton.setBackground(new Color(30, 144, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> saveIDCardAsImage(panel));

        // Add components to the panel
        panel.add(headerPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(instituteLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(photoLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(idLabel);
        panel.add(courseLabel);
        panel.add(dobLabel);
        panel.add(departmentLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(footerLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Save the ID card as an image
    public void saveIDCardAsImage(JPanel panel) {
        try {
            BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panel.paint(g2d);
            g2d.dispose();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save ID Card");
            fileChooser.setSelectedFile(new File("IDCard.png"));
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(image, "png", fileToSave);
                JOptionPane.showMessageDialog(null, "ID Card saved successfully!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving the ID Card: " + ex.getMessage());
        }
    }
}

public class IdCardGenerator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame inputFrame = new JFrame("ID Card Input Form");
            inputFrame.setSize(400, 500);
            inputFrame.setLayout(new GridLayout(9, 2, 5, 5));

            // Input Fields
            JTextField instituteField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField dobField = new JTextField();
            JTextField departmentField = new JTextField();
            JTextField courseField = new JTextField();
            JTextField idField = new JTextField();
            JButton logoButton = new JButton("Choose Logo");
            JButton photoButton = new JButton("Choose Photo");
            String[] logoPath = {""};
            String[] photoPath = {""};

            // File Choosers
            logoButton.addActionListener(e -> {
                JFileChooser logoChooser = new JFileChooser();
                if (logoChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    logoPath[0] = logoChooser.getSelectedFile().getAbsolutePath();
                }
            });

            photoButton.addActionListener(e -> {
                JFileChooser photoChooser = new JFileChooser();
                if (photoChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    photoPath[0] = photoChooser.getSelectedFile().getAbsolutePath();
                }
            });

            // Submit Button
            JButton submitButton = new JButton("Generate ID Card");
            submitButton.addActionListener(e -> {
                String instituteName = instituteField.getText();
                String name = nameField.getText();
                String dob = dobField.getText();
                String department = departmentField.getText();
                String course = courseField.getText();
                String idNumber = idField.getText();

                if (instituteName.isEmpty() || name.isEmpty() || dob.isEmpty() || department.isEmpty() || course.isEmpty()
                		|| idNumber.isEmpty() || logoPath[0].isEmpty() || photoPath[0].isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                } else {
                    IDCard idCard = new IDCard(instituteName, logoPath[0], photoPath[0], name, course, dob, department, idNumber);
                    idCard.displayIDCard();
                    inputFrame.dispose();
                }
            });

            // Add components to the frame
            inputFrame.add(new JLabel("Institute Name:"));
            inputFrame.add(instituteField);
            inputFrame.add(new JLabel("Name:"));
            inputFrame.add(nameField);
            inputFrame.add(new JLabel("Date of Birth (dd-mm-yyyy):"));
            inputFrame.add(dobField);
            inputFrame.add(new JLabel("Department:"));
            inputFrame.add(departmentField);
            inputFrame.add(new JLabel("Course:"));
            inputFrame.add(courseField);
            inputFrame.add(new JLabel("ID Number:"));
            inputFrame.add(idField);
            inputFrame.add(new JLabel("Select Logo:"));
            inputFrame.add(logoButton);
            inputFrame.add(new JLabel("Select Photo:"));
            inputFrame.add(photoButton);

            // Add Submit Button
            inputFrame.add(new JLabel("")); // Empty space for alignment
            inputFrame.add(submitButton);

            inputFrame.setVisible(true);
});
}
}

