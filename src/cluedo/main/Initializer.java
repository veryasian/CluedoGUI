package cluedo.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import cluedo.assets.*;
import cluedo.assets.Character;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.Envelope;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
/**
 * Class that represents the Board. Contains fields and methods regarding setting up the board.
 * @author Casey & Linus
 *
 */
public class Initializer {
	/**Lists that hold components of the board */
	private static Room[] rooms = new Room[9];
	private static Weapon[] weapons = new Weapon[6];
	private static List<Card> cards = new ArrayList<>();
	private static Character[] characters = new Character[6];
	public static List<String> characterNames = new ArrayList<>();
	private static Player[] players = new Player[6];
	private static Envelope envelope = new Envelope();
	private static RoomCard[] roomCards = new RoomCard[9];
	private static WeaponCard[] weaponCards = new WeaponCard[6];
	private static CharacterCard[] characterCards = new CharacterCard[6];

	/*Initialise Rooms NB: not all rooms have weapons.  */
	public Room kitchen = new Room(CluedoGameController.kitchenCard, "Kitchen", 0, 1, 6, 6, true);
	public Room diningrm = new Room(CluedoGameController.diningCard, "Dining Room", 0, 9, 8, 7, false);
	public Room ballRm = new Room(CluedoGameController.ballCard, "Ball Room", 8, 1, 8, 7, false);
	public Room conservatory = new Room(CluedoGameController.conservatoryCard, "Conservatory", 18, 1, 7, 5, true);
	public Room billRm = new Room(CluedoGameController.billiardCard, "Billiard Room", 19, 8, 6, 5, false);
	public Room lib = new Room(CluedoGameController.libraryCard, "Library", 18, 14, 7, 5, false);
	public Room study = new Room(CluedoGameController.studyCard, "Study", 17, 21, 8, 4, true);
	public Room hall = new Room(CluedoGameController.hallCard, "Hall", 9, 18, 6, 7, false);
	public Room lounge = new Room(CluedoGameController.loungeCard, "Lounge", 0, 19, 7, 6, true);

	/**
	 * Construct a new Board
	 */
	public Initializer(){
		initializeWeapons();
		initializeRooms();
		initializeCharacters();
		fillList();
		initializeEnvelope();
	}
	
	/**
	 * Returns the envelope object.
	 * @return
	 */
	public static Envelope getEnvelope(){
		return envelope;
	}

	/**
	 * Initializes the weapons list.
	 */
	private void initializeWeapons(){
		/*Fill the arraylist with weapons*/
		weapons[0] = new Weapon(CluedoGameController.candleCard, "Candlestick");
		weapons[1] = new Weapon(CluedoGameController.daggerCard, "Dagger");
		weapons[2] = new Weapon(CluedoGameController.leadPipeCard, "Lead Pipe");
		weapons[3] = new Weapon(CluedoGameController.revolverCard, "Revolver");
		weapons[4] = new Weapon(CluedoGameController.ropeCard, "Rope");
		weapons[5] = new Weapon(CluedoGameController.spannerCard, "Spanner");

		/*Shuffle it so that a weapon will be in a random room each time. */
		Collections.shuffle(Arrays.asList(weapons)); //shuffle it
	}

	/**
	 * Initializes the rooms list.
	 */
	private void initializeRooms(){
		/*Add rooms to rooms list*/
		rooms[0] = kitchen;
		rooms[1] = diningrm;
		rooms[2] = ballRm;
		rooms[3] = conservatory;
		rooms[4] = billRm;
		rooms[5] = lib;
		rooms[6] = study;
		rooms[7] = hall;
		rooms[8] = lounge;

		/*Set connecting rooms*/
		kitchen.setRoom(study);
		conservatory.setRoom(lounge);
		study.setRoom(kitchen);
		lounge.setRoom(conservatory);
		Collections.shuffle(Arrays.asList(rooms)); //shuffle it
		Collections.shuffle(Arrays.asList(weapons));
		for(int i = 0; i < weapons.length; i++){
			Room r = rooms[i];
			Weapon w = weapons[i];
			r.addWeapon(w);
			w.addRoom(r);
		}
	}

	/**
	 * Initializes the characters list.
	 */
	private void initializeCharacters(){
		/*Fill the ArrayList with people.. */
		characters[0] = new Character(CluedoGameController.missScarlett, "Miss Scarlet", new Color(255, 77, 77), new Position(7, 24));
		characters[1] = new Character(CluedoGameController.colonelMustard, "Colonel Mustard", new Color(255, 255, 77), new Position(0, 17));
		characters[2] = new Character(CluedoGameController.mrsWhite, "Mrs. White", Color.white, new Position(9, 0));
		characters[3] = new Character(CluedoGameController.theRevGreen, "The Reverend Green", new Color(0, 204, 0), new Position(14, 0));
		characters[4] = new Character(CluedoGameController.mrsPeacock, "Mrs. Peacock", new Color(153, 0, 204), new Position(23, 6));
		characters[5] = new Character(CluedoGameController.profPlum, "Professor Plum", new Color(0, 102, 204), new Position(23, 19));
	
		/*Allows a List of the characters Names to be used/modified */
		for(Character c : characters){
			characterNames.add(c.name());
		}
	}

	/**
	 * Put all cards in cards list.
	 */
	private void fillList(){
		/*Fill the cards arrayList with Room Cards */
		for(int i = 0; i < rooms.length; i++){
			Room r = rooms[i];
			Card c = new RoomCard(r, r.getImage());
			cards.add(c);
			roomCards[i] = (RoomCard) c;
		}

		/*Fill the cards arrayList with Weapon Cards */
		for(int i = 0; i < weapons.length; i++){
			Weapon w = weapons[i];
			Card c = new WeaponCard(w, w.getImage());
			cards.add(c);
			weaponCards[i] = (WeaponCard) c;
		}

		/*Fill the cards ArrayList with Player Cards */
		for(int i = 0; i < characters.length; i++){
			Character c = characters[i];
			Card cd = new CharacterCard(c, c.getImage());
			cards.add(cd);
			characterCards[i] = (CharacterCard) cd;
		}
		Collections.shuffle(cards); 
	}

	/**
	 * Initializes the envelope list.
	 */
	private void initializeEnvelope(){
		RoomCard roomCard = null;
		CharacterCard characterCard = null;
		WeaponCard weaponCard = null;

		for(Card c : cards){
			if(roomCard == null){
				if(c instanceof RoomCard){
					roomCard = (RoomCard) c;
				}
			}else if(weaponCard == null){
				if(c instanceof WeaponCard){
					weaponCard = (WeaponCard) c;
				}
			}else if(characterCard == null){
				if(c instanceof CharacterCard){
					characterCard = (CharacterCard) c;
				}
			}

			if(roomCard != null && characterCard != null && weaponCard != null){
				break;
			}
		}
		envelope.add(weaponCard);
		envelope.add(characterCard);
		envelope.add(roomCard);
		
		/*Finally, remove these cards from their arrayList */
		cards.remove(roomCard);
		cards.remove(weaponCard);
		cards.remove(characterCard);
	}

	/**
	 * Store character in room and room in character.
	 */
	public void setCharacters(){
		Collections.shuffle(Arrays.asList(rooms));
		for(int i = 0; i < characters.length; i++){
			Character c = characters[i];
			if(c.player() == null){
				Player p = new Player("none");
				c.setPlayer(p);
				p.setCharacter(c);
				Room rm = rooms[i];
				rm.addCharacter(c);
				c.addRoom(rm);
				CluedoGameController.cluedoCanvas.moveToRoom(p, rm);
			}
		}
	}

	/**
	 * Distribute cards to players.
	 * @param currentPlayers
	 */
	public void distributeCards(List<Player> currentPlayers){
		Collections.shuffle(Arrays.asList(roomCards)); 
		Collections.shuffle(Arrays.asList(weaponCards)); 
		Collections.shuffle(Arrays.asList(characterCards)); 
		for(int i = 0, j = 0; j < roomCards.length; i++, j++){
			if(i == currentPlayers.size()){
				i = 0;
			}
			Player currentPlayer = currentPlayers.get(i);
			currentPlayer.addCard(roomCards[j]);
		}
		for(int i = 0, j = 0; j < weaponCards.length; i++, j++){
			if(i == currentPlayers.size()){
				i = 0;
			}
			Player currentPlayer = currentPlayers.get(i);
			currentPlayer.addCard(weaponCards[j]);
		}
		for(int i = 0, j = 0; j < characterCards.length; i++, j++){
			if(i == currentPlayers.size()){
				i = 0;
			}
			Player currentPlayer = currentPlayers.get(i);
			currentPlayer.addCard(characterCards[j]);
		}
	}

	/**
	 * Returns the list of RoomCards
	 * @return
	 */
	public static RoomCard[] getRoomCards(){
		return Initializer.roomCards;
	}

	/**
	 * Returns the list of WeaponCards
	 * @return
	 */
	public static WeaponCard[] getWeaponCards(){
		return Initializer.weaponCards;
	}

	/**
	 * Returns the list of CharacterCards
	 * @return
	 */
	public static CharacterCard[] getCharacterCards(){
		return Initializer.characterCards;
	}

	/**
	 * Returns the rooms
	 * @return
	 */
	public static Room[] getRooms(){
		return Initializer.rooms;
	}
	
	/**
	 * Returns the list of characters
	 * @return
	 */
	public static Character[] getCharacters(){
		return characters;
	}
}
