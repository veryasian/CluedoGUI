package cluedo.gui;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * The canvas being used to draw the dice.
 * @author Casey and Linus
 *
 */
public class DiceCanvas extends JPanel{
	
	private int diceOne = 0;
	private int diceTwo = 0;
	
	private static final int DICE_SIZE = 40;
	
	private int diceXPos = 0;
	private int diceYPos = 0;
	
	private BufferedImage dOne;
	private BufferedImage dTwo;
	private BufferedImage dThree;
	private BufferedImage dFour;
	private BufferedImage dFive;
	private BufferedImage dSix;
	private BufferedImage dNull;
	
	/**
	 * Dumb constructor, just creates the canvas with the default values
	 * @param value
	 */
	public DiceCanvas(){
		
	}
	
	/**
	 * Construct a DiceCanvas with the given x and y values
	 * @param value
	 */
	public DiceCanvas(int firstVal, int secondVal){
		assert firstVal > 0 || firstVal < 6;
		assert secondVal > 0 || secondVal < 6;
		this.diceOne = firstVal;
		this.diceTwo = secondVal;
	}
	
	/**
	 * Sets the value of the first dice.
	 * @param value
	 */
	public void setDiceOne(int value){
		this.diceOne = value;
	}
	
	/**
	 * Sets the value of the second dice.
	 * @param value
	 */
	public void setDiceTwo(int value){
		this.diceTwo = value;
	}
	
	/**
	 * Resets the dice roll.
	 */
	public void resetDice(){
		this.diceOne = 0;
		this.diceTwo = 0;
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(105,45);
	}
	
	/**
	 * Paints the dice onto the canvas.
	 */
	@Override
	public void paint(Graphics g){
		
		try {
			dOne = ImageIO.read(new File("one.png"));
			dTwo = ImageIO.read(new File("two.png"));
			dThree = ImageIO.read(new File("three.png"));
			dFour = ImageIO.read(new File("four.png"));
			dFive = ImageIO.read(new File("five.png"));
			dSix = ImageIO.read(new File("six.png"));
			dNull = ImageIO.read(new File("null.png"));
		} catch (IOException e) {
			System.err.println("One of the images is not right");
			e.printStackTrace();
		}
		
		switch(diceOne){
		case 1:
			g.drawImage(dOne, diceXPos, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 2:
			g.drawImage(dTwo, diceXPos, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 3:
			g.drawImage(dThree, diceXPos, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 4:
			g.drawImage(dFour, diceXPos, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 5:
			g.drawImage(dFive, diceXPos, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 6:
			g.drawImage(dSix, diceXPos, diceYPos, DICE_SIZE,DICE_SIZE, null);
			break;
		default:
			g.drawImage(dNull, diceXPos, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		}
		
		switch(diceTwo){
		case 1:
			g.drawImage(dOne, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 2:
			g.drawImage(dTwo, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 3:
			g.drawImage(dThree, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 4:
			g.drawImage(dFour, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 5:
			g.drawImage(dFive, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		case 6:
			g.drawImage(dSix, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE,DICE_SIZE, null);
			break;
		default:
			g.drawImage(dNull, diceXPos+DICE_SIZE, diceYPos, DICE_SIZE, DICE_SIZE, null);
			break;
		}
	}
}
