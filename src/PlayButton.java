/*
 *
 */


import edu.msoe.se1021.lab4.WavPlayer;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

/**
 * Class to describe a button used in the Barnyard Simon game. The class manages
 * the button along with its color and sound.
 * @author FIX
 * @version 20160113_5
 */
public class PlayButton extends JButton {

    /**
     * Base filename for the audio to be played when the button is activated.
     */
    private String baseFilename;

    /**
     * Color of the button when activated.
     */
    private Color color;

    /**
     * Color of all buttons when inactive.
     */
    private static final Color INACTIVE_COLOR = Color.LIGHT_GRAY;

    /**
     * Auto-generated serial version ID
     */
    private static final long serialVersionUID = 2L;

    /**
     * Constructor.  Initializes the GUI component, sets the color and,
     * if listener is not null, instructs the GUI component to notify listener
     * whenever an ActionEvent occurs.
     *
     * @param color The desired color of the button when activated
     * @param baseName The base filename of the .wav file to be played when the
     * button is activated.  The name passed should not include the .wav extension.
     * @param listener The object to be notified when the button is activated (an
     * ActionEvent occurs)
     */
    public PlayButton(Color color, String baseName, ActionListener listener) {

    }

    /**
     * Causes the button to activate.
     * <p> The method must change the background color of the GUI component, repaint
     * the component, play the audio associated with the button, and then revert the 
     * background color to the INACTIVE_COLOR after the audio has finished playing,
     * and repaint the component once again.
     */
    public void activate() {

    }

}