package LifeStarter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * This class provides the user interface and control for the game Life.
 * 
 * @author Barbara Goldner based on work by Hal Perkins and Robert Carr
 * @author Modified by Andrew Nguyen (11/12/15)
 */
public class LifeGUI extends JPanel {
    // game instance variables
    private Life board;        // game board; has a State[][] and int generations.

    // GUI components
    private JLabel generationsLived;          
    private JButton resetButton, cycleButton, 
       startButton, stopButton;   // reset control and cycle control
    private boolean isRunning;  // if true, don't allow reset or LiveOneCycle
    private javax.swing.Timer myTimer;
    private Cell[][] cells;         // board cells for display
   
    // delay in millisec.  Smaller means faster
    private static final int TIMER_DELAY = 750;  
    
    /** Construct new Life game with a graphical user interface */
    public LifeGUI()    {
        // create and initialize game board and display representation
        board = new Life();                                  //has a State[][] and int generations.
        cells = new Cell[Life.NROWSCOLS][Life.NROWSCOLS];    //has State and int row, int col.

        addTimer();  // allows for continuous running
        
        // set layout for game display
        setLayout(new BorderLayout());
        
        // Create board cells and add to display
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(Life.NROWSCOLS, Life.NROWSCOLS));
        for (int row = 0; row < Life.NROWSCOLS; row++) {
            for (int col = 0; col < Life.NROWSCOLS; col++) {
                cells[row][col] = new Cell(State.ALIVE, row, col);
                boardPanel.add(cells[row][col]);
           }
        }
        add(boardPanel, BorderLayout.CENTER);


        // Set up 4 buttons
        // a reset button so it starts a new game when clicked
        // a cycle button to tell the Life automaton to live one cycle
        // start and stop buttons for continuous cycles
        resetButton = new JButton("New Game");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isRunning) return;  // don't do anything
                board.newGame();
                updateDisplay();
            }
        });
        
        cycleButton = new JButton("Live One Cycle");
        cycleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isRunning) return;  // don't do anything
                doOneStep();
            }
        });
        
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isRunning) return;
                isRunning = true;
                myTimer.start();
            }  
        });
        
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                myTimer.stop();
            }   
        });
        isRunning = false;

        // Put the buttons and the generation count display on the screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetButton);
        buttonPanel.add(cycleButton);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        generationsLived = new JLabel("     Generations Lived: " , JLabel.RIGHT);
        buttonPanel.add(generationsLived);
        add(buttonPanel, BorderLayout.SOUTH);

        // show initial display
        updateDisplay();
    }

    /** Update display to match game state. */
    public void updateDisplay() {
        // update count display
        generationsLived.setText("     Generations Lived: " + board.getGenerationCount());

        // update board display
        for (int row = 0; row < Life.NROWSCOLS; row++) {
            for (int col = 0; col < Life.NROWSCOLS; col++) {
                cells[row][col].setState(board.getCell(row,col));   //Set cell state to the same as the board state.
            }
        }
        repaint();
    }
    
    // post: creates a timer that calls the model's update
    //       method and repaints the display
    private void addTimer() {
        ActionListener updater = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doOneStep();
            }
        };
        myTimer = new javax.swing.Timer(TIMER_DELAY, updater);
        myTimer.setCoalesce(true);
    }

    // one step of the simulation
    private void doOneStep() {
           board.lifeCycle();
           updateDisplay();
    }

    /** Create new game and a window to display it */
    public static void test() {
        JFrame f = new JFrame("The Game of Life");     // top-level window
        LifeGUI l = new LifeGUI();
        f.getContentPane().add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,600);
        f.validate();
        f.setVisible(true);
        f.toFront();
    }
    
    public static void main(String[] args) {
          javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                test();
            }
        });
    }
}
