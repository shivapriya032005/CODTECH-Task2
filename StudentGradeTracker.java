import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeTracker extends JFrame {
    private JTextField[] marksObtainedFields;
    private JTextField[] maxMarksFields;
    private JTextArea resultArea;
    private int numberOfSubjects;

    public StudentGradeTracker(int numSubjects) {
        numberOfSubjects = numSubjects;
        marksObtainedFields = new JTextField[numSubjects];
        maxMarksFields = new JTextField[numSubjects];

        setTitle("Student Grade Tracker");
        setSize(800, 600); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Changed to GridBagLayout for more control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < numSubjects; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            add(new JLabel("Marks Obtained in Subject " + (i + 1) + ":"), gbc);

            gbc.gridx = 1;
            marksObtainedFields[i] = new JTextField(10);
            add(marksObtainedFields[i], gbc);

            gbc.gridx = 2;
            add(new JLabel("Total Marks for Subject " + (i + 1) + ":"), gbc);

            gbc.gridx = 3;
            maxMarksFields[i] = new JTextField(10);
            add(maxMarksFields[i], gbc);
        }

        gbc.gridx = 3;
        gbc.gridy = numberOfSubjects;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = numberOfSubjects + 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        resultArea = new JTextArea(8, 40);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), gbc);

        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double totalMarksObtained = 0, totalMaxMarks = 0, avg, gpa;
            char grade;
            String note;

            for (int i = 0; i < numberOfSubjects; i++) {
                double marksObtained = Double.parseDouble(marksObtainedFields[i].getText());
                double maxMarks = Double.parseDouble(maxMarksFields[i].getText());

                totalMarksObtained += marksObtained;
                totalMaxMarks += maxMarks;
            }

            avg = (totalMarksObtained / totalMaxMarks) * 100;

            if (avg >= 90) {
                gpa = 10.0;
                grade = 'A';
                note = "Congratulations, you did Outstanding!!!";
            } else if (avg >= 80) {
                gpa = 9.0;
                grade = 'B';
                note = "Keep it up, you did superb!!";
            } else if (avg >= 70) {
                gpa = 8.0;
                grade = 'C';
                note = "You did a good job!!";
            } else if (avg >= 60) {
                gpa = 7.0;
                grade = 'D';
                note = "Good job, you have potential, try more.";
            } else if (avg >= 50) {
                gpa = 6.0;
                grade = 'E';
                note = "Yay! You have passed all the subjects.";
            } else {
                gpa = 5.0;
                grade = 'F';
                note = "OOPS, you have failed. Try better next time :/";
            }

            resultArea.setText(" ----Grade Summary---- \n");
            resultArea.append("Total Marks Obtained: " + totalMarksObtained + " / " + totalMaxMarks + "\n");
            resultArea.append("Percentage: " + avg + "%\n");
            resultArea.append("Grade Obtained: " + grade + "\n");
            resultArea.append("GPA: " + gpa + "\n");
            resultArea.append(note + "\n");
        }
    }

    public static void main(String[] args) {
        int numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of subjects:"));
        new StudentGradeTracker(numSubjects);
    }
}
