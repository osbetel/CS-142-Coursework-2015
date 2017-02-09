package NguyenHW4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

/**
 * Creates the graphical user interface for the program FindADate. 
 * 
 * DO NOT MODIFY THIS CODE.  Feel free to read through it if you'd like.  I'd be happy to
 * discuss it with anyone via email or the message board.
 * 
 * @author CSC 142 
 * @version 1-23-07
 */
public class UserWindow {

    private FindADate owner;
    // private JTextField startDay, startMonth, startYear;
    private JSpinner startDay, startMonth, startYear;
    private JButton timeTravel;
    private JLabel displayArea;

    private static final String[] MONTHS = {"January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};

    /**
     * Construct a new user interface for the FindADate program.
     * @param source object that starts the program and responds to user input.
     */

    public UserWindow(FindADate source ) {
        owner = source;
        createAndShowGUI( );
    }

    /**
     * Replace existing data in the display area with the String value 
     * provided.
     * @param text the text to be shown
     */
    public void displayText( String text ) {
        displayArea.setText( text );
    }

    /**
     * Display an error pop-up window with the given text
     * @param text the text to be displayed in the error window
     */
    public void displayErrorWindow(String text) {
        JOptionPane.showMessageDialog(null, text, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Returns the day selected by the user.
     * @return the value from the day spinner
     */
    public int getDayInput( ) {
        return ((Integer)startDay.getValue()).intValue(); 
    }

    /**
     * Returns the name of the month selected by the user
     * @return the value from the month spinner 
     */
    public String getMonthInput( ) {
        return ((String)startMonth.getValue()); 
    }

    /**
     * Returns the year selected by the user.
     * @return the value from the year spinner
     */
    public int getYearInput( ) {
        return ((Integer)startYear.getValue()).intValue(); 
    }

    private void createAndShowGUI( ) {
        // create and show entire display 
        JFrame win = new JFrame( );
        win.setSize(400, 300);
        JPanel mainPanel = createMainPanel( );
        win.getContentPane().add( mainPanel, BorderLayout.CENTER );

        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        win.setTitle( "Find Me a Date!" );
        win. validate( );
        win.setVisible( true );
        win.toFront();
    }

    private JPanel createMainPanel( ) {
        Dimension d = new Dimension(80, 20);  // for the JLabels to line up
        Dimension d2 = new Dimension(100,20);
        Color c = new Color(153, 255, 153);   //a pale green
        Color back = Color.blue;

        // 3 JSpinners for input
        startDay = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        SpinnerListModel m = new SpinnerListModel(MONTHS);
        startMonth = new JSpinner(m);

        // make it wider
        //Tweak the spinner's formatted text field.
        JFormattedTextField ftf = getTextField(startMonth);
        if (ftf != null ) {
            ftf.setColumns(6); //specify more width than we need
            ftf.setHorizontalAlignment(JTextField.CENTER);
        }

        startYear = new JSpinner(new SpinnerNumberModel(2000, 1950, 2050, 1));
        //Make the year be formatted without a thousands separator.
        startYear.setEditor(new JSpinner.NumberEditor(startYear, "#"));

        // 3 labels
        JLabel dayLabel = new JLabel( " Day " );
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setPreferredSize(d);
        dayLabel.setOpaque(true);
        dayLabel.setBackground(c);

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthLabel.setPreferredSize(d);
        monthLabel.setOpaque(true);
        monthLabel.setBackground(c);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yearLabel.setPreferredSize(d);
        yearLabel.setOpaque(true);
        yearLabel.setBackground(c);

        // bundle label/spinners
        JPanel dayPanel = new JPanel( );
        dayPanel.add( dayLabel );
        dayPanel.add( startDay);
        dayPanel.setBackground(back);

        JPanel monthPanel = new JPanel( );
        monthPanel.add( monthLabel );
        monthPanel.add( startMonth);
        monthPanel.setBackground(back);

        JPanel yearPanel = new JPanel( );
        yearPanel.add( yearLabel );
        yearPanel.add( startYear);
        yearPanel.setBackground(back);

        // create display label and area
        JButton result = new JButton("Date in 30 days" );
        result.addActionListener(new GoListener());
        displayArea = new JLabel();
        displayArea.setOpaque(true);
        displayArea.setBackground(Color.white);
        displayArea.setPreferredSize(d2);

        //bundle these 2 up
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(result);
        bottomPanel.add(displayArea);
        bottomPanel.setBackground(back);

        // bundle both input area and display area
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(monthPanel);
        controlPanel.add(dayPanel);
        controlPanel.add(yearPanel);
        controlPanel.add(bottomPanel);
        controlPanel.setBackground(back);

        return controlPanel;
    }

    /**
     * Return the formatted text field used by the editor, or
     * null if the editor doesn't descend from JSpinner.DefaultEditor.
     */
    private JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                + spinner.getEditor().getClass()
                + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

    /* For those brave students who venture down here...
     * There classes are MEMBERS of the UserWindow class, that's why the keyword private
     * There are advantages to a design like this that I'd be happy to discuss
     */
    private class GoListener implements ActionListener {
        /* Responds to user hitting the enter key from the JTextFields
         * Need to validate user input that they are ints.  If so
         * send control to owner.takeAction() who will test for valid date
         * Otherwise, pop up error window
         */
        public void actionPerformed(ActionEvent e) {
            owner.respondToUser();  // callback to client

        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "Enter a numeric value for a day, month, and year in each box.\n"+
                "Once you've entered all 3 numbers, hit the enter key and you will\n"+
                "see the result. Note, the cursor must be within one of the input\n" +
                "boxes for action to happen.\n\n" +
                "You may change a value in any box and hit the enter key again for\n" +
                "a new answer.";
            JOptionPane.showMessageDialog(null, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}