
/**
 * SE1021 - 032
 * Winter 2016
 * Lab 5
 * Name: Ian Guswiler
 * Created: 1/14/2016
 */

import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that manages the GUI for Barnyard Simon.
 * <p> Barnyard Simon is a game similar to the electronic Simon memory game
 * marketed by the Milton Bradley Company.
 * <p> The game has four colored buttons (red, green, blue, and yellow).  The
 * buttons are activated in a randomly chosen sequence.  The player must then
 * mimic the sequence in order to continue playing the game.  The sequence begins
 * with just one button and increases in length by one each time the player
 * successfully mimics the sequence.
 * <p> Traditionally, the activation of each button results in the button being
 * lit and a tone (unique for each button) being played for a short amount of time.
 * Barnyard Simon differs in that the sounds associated with each button are
 * barnyard animal sounds.
 * @author Ian Guswiler
 * @version 20160113_5
 */
public class BarnyardSimon extends JFrame {

    /**
     * This class listens solely to the start button component of the GUI.
     * @author Ian Guswiler
     * @version 20160113_5
     */
    private class StartGame implements ActionListener {

        /**
         * Initializes and starts a game of Barnyard Simon.
         * <p> This method disables the start button and sets the text to "Playing."  It
         * also creates a new sequence and calls playSequence to initiate game play.
         *
         * @param event The event that caused this method to be called
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            btnStart.setEnabled(false);
            btnStart.setText("Playing");
            rGen = new Random();
            sequence = new ArrayList<>();
            playSequence();
        }

    }

    /**
     * This class listens solely for game play buttons to be pressed.  Once pressed,
     * this class determines which button was pressed and then advances game play
     * appropriately.
     * @author FIX
     * @version 20160113_5
     */
    private class HandleGameButton implements ActionListener {

        /**
         * Handles an action event caused by one of the four colored buttons in Barnyard
         * Simon.
         * <p> The method first determines if the correct button was pressed.
         * <p> If not, the game has ended and the method must ensure that the high score
         * is updated (if necessary) and resets the label on the start button and enables
         * it.
         * <p> If the correct button is pressed, then the button is activated and the
         * current count of how many buttons in the sequence the player has pressed
         * correctly must be incremented.  If the player has pressed all of the buttons in
         * the sequence correctly, the method must ensure that the sequence with one
         * additional entry be played.
         *
         * @param event The event that caused this method to be called
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == buttons[sequence.get(countCorrect)]){
                buttons[sequence.get(countCorrect)].activate();
                countCorrect++;
            }else if(event.getSource() != buttons[sequence.get(countCorrect)]){
                btnStart.setEnabled(true);
                btnStart.setText("Start");
                if(countCorrect > highScore && countCorrect < sequence.size() - 1){
                    highScore = countCorrect;
                }else if(sequence.size() - 1 > highScore){
                    highScore = sequence.size() - 1;
                }
                countCorrect = 0;
                scoreReport.setText("Current: "+ countCorrect + "\nHigh: " + highScore);
            }
            if(countCorrect == sequence.size()){
                scoreReport.setText("Current: "+ countCorrect + "\nHigh: " + highScore);
                countCorrect = 0;
                playSequence();
            }

        }

    }

    private JButton btnStart;

    /**
     * The buttons used for game play.
     */
    private PlayButton buttons[];

    /**
     * The player's current location in the sequence.
     */
    private int countCorrect;
    private static final int HEIGHT = 220;
    private int highScore;
    private Random rGen;
    private JTextArea scoreReport;

    /**
     * The sequence in which the buttons are activated.
     */
    private List<Integer> sequence;

    /**
     * Auto-generated serial version ID
     */
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 180;

    /**
     * Constructor.  Creates game buttons, initializes attributes and the GUI layout.
     */
    public BarnyardSimon() {
        setTitle("Barnyard Simon");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        scoreReport = new JTextArea();
        scoreReport.setText("Current: "+ countCorrect + "\nHigh: " + highScore);
        scoreReport.setEditable(false);

        btnStart = new JButton("Start");
        btnStart.addActionListener(new StartGame());

        buttons = new PlayButton[4];
        buttons[0] = new PlayButton(Color.red, "cluck", new HandleGameButton());
        buttons[1] = new PlayButton(Color.green, "neigh", new HandleGameButton());
        buttons[2] = new PlayButton(Color.blue, "moo", new HandleGameButton());
        buttons[3] = new PlayButton(Color.yellow, "oink", new HandleGameButton());

        for(PlayButton button: buttons){
            add(button);
        }

        add(scoreReport);
        add(btnStart);


    }

    /**
     * Enables/Disables all of the game play buttons.
     *
     * @param enable True to enable, false to disable
     */
    private void enableButtons(boolean enable) {
        for(PlayButton button: buttons){
            button.setEnabled(enable);
        }
    }

    /**
     * Plays the current sequence of buttons.
     * <p> Adds one additional randomly selected button to the sequence than then
     * iterates through the sequence activating each button in turn.
     * <p> All buttons should be disabled while the sequence is playing and the game
     * play buttons should be enabled before returning from this method.
     */
    public void playSequence() {
        sequence.add(rGen.nextInt(4));
        enableButtons(false);
        sequence.forEach(integer -> buttons[integer].activate());
        enableButtons(true);
    }

}