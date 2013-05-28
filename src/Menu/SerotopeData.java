package Menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Serotope.GameStats;

//Singleton data structure that holds all the game data been highscores and achievements
public class SerotopeData {
	private String highscoreFile;
	private String achievementFile;
	private String curStatsFile;
	private static ArrayList<Highscore> highscores;
	private static ArrayList<Medal> medals;
	private static SerotopeData instance = null;
	
	public static final int GAMESPLAYEDID = 0;
	public static final int ENEMIESKILLEDID = 1;
	public static final int REGENERATIONSID = 2;
	public static final int MAXKILLS = 3;
	
	public SerotopeData(String hsfile, String achvfile, String csfile) {
		highscoreFile = hsfile;
		achievementFile = achvfile;
		curStatsFile = csfile;
		readInHighscores(hsfile);
		readInAchievements(achvfile, csfile);
	}

	//Returns the instance of the data or creates a new one
	public static SerotopeData getInstance(String hsfile, String achvfile, String csfile){
		if(instance == null){
			instance = new SerotopeData(hsfile,achvfile,csfile);
		}
		return instance;
	}

	//Reads the highscores from file and enters them into the data structure
	private void readInHighscores(String filename){
		BufferedReader reader = null;
		String nameLine, scoreLine;
		//Read in the highscores from the text file
		try {
			reader = new BufferedReader(new FileReader(filename));
			highscores = new ArrayList<Highscore>();
			while((nameLine = reader.readLine()) != null && (scoreLine = reader.readLine()) != null){
				highscores.add(new Highscore(nameLine,scoreLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	//Reads in the achievements from file and enters them into the data structure
	private void readInAchievements(String achvfile, String csfile) {
		BufferedReader achvReader = null;
		BufferedReader statsReader = null;
		String nameLine, scoreLine, bronzeLine, silverLine, goldLine, platinumLine;
		//Read in the achievements from the text file
		try {
			achvReader = new BufferedReader(new FileReader(achvfile));
			statsReader = new BufferedReader(new FileReader(csfile));
			medals = new ArrayList<Medal>();
			while(	(nameLine = achvReader.readLine()) != null &&
					(scoreLine = statsReader.readLine()) != null &&
					(bronzeLine = achvReader.readLine()) != null &&
					(silverLine = achvReader.readLine()) != null &&
					(goldLine = achvReader.readLine()) != null &&
					(platinumLine = achvReader.readLine()) != null){
				medals.add(new Medal(nameLine,scoreLine,bronzeLine,silverLine,goldLine,platinumLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(achvReader != null)
					achvReader.close();
				if(statsReader != null)
					statsReader.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	//Draws all the highscores in a ranked order from position (x,y)
	public void drawHighscores(int x, int y, int spacing, Graphics g){
		int scorePosY = y;
		int rank = 1;
		for(Highscore highscore : highscores){
			highscore.drawLine(x, scorePosY, rank, g);
			rank++;
			scorePosY += spacing;
		}
	}

	//Draw all medals from position (x,y)
	public void drawMedals(int x, int y, GameContainer gc, Graphics g) throws SlickException{
		int boxX;
		int boxY = y;
		int nameLength = gc.getWidth()/4;
		int boxSide = gc.getHeight()/12;
		int buffer = gc.getWidth()/50;
		g.setColor(Color.white);
		for(Medal medal : medals){
			boxX = x;
			g.drawRect(x, boxY, nameLength, boxSide);
			boxX += nameLength;
			for(int i=0;i<4;i++){
				g.drawRect(boxX, boxY, boxSide, boxSide);
				boxX += boxSide;
			}
			
			medal.drawLine(x, boxY, buffer, nameLength, boxSide, g);
			boxY += boxSide;
		}
	}
	
	//Updates each achievement using the GameStats passed to it
	public void updateAchievements(GameStats gameStats){
		int gamesPlayed = medals.get(GAMESPLAYEDID).getCurrent();
		medals.get(GAMESPLAYEDID).setCurrent(gamesPlayed+1);
		int enemiesKilled = medals.get(ENEMIESKILLEDID).getCurrent();
		medals.get(ENEMIESKILLEDID).setCurrent(enemiesKilled + gameStats.getEnemiesKilled());
		int regenerations = medals.get(REGENERATIONSID).getCurrent();
		medals.get(REGENERATIONSID).setCurrent(regenerations + gameStats.getGenerations());
		int maxKills = medals.get(MAXKILLS).getCurrent();
		if(gameStats.getEnemiesKilled()>maxKills){
			medals.get(MAXKILLS).setCurrent(gameStats.getEnemiesKilled());
		}
		for(Medal medal : medals)
			medal.updateMedal();
		writeToAchievements();
	}
	
	//Writes highscores to the file
	public void writeToHighscores(String name, int gameScore){
		BufferedWriter writer = null;
    	try{
			writer = new BufferedWriter(new FileWriter(highscoreFile));
			highscores.add(Play.rank, new Highscore(name,gameScore));
			if(highscores.size() > Utils.MAXHIGHSCORES)
				highscores.remove(highscores.size()-1);
			for(Highscore score : highscores){
				writer.write(score.getName());
				writer.newLine();
				writer.write(Integer.toString(score.getScore()));
				writer.newLine();
			}
    	} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(writer != null){
					writer.flush();
					writer.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
    	}
	}
	
	//Writes achievements to the file
	private void writeToAchievements(){
		BufferedWriter writer = null;
    	try{
			writer = new BufferedWriter(new FileWriter(curStatsFile));
			for(Medal medal : medals){
				writer.write(Integer.toString(medal.getCurrent()));
				writer.newLine();
			}
    	} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(writer != null){
					writer.flush();
					writer.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
    	}
	}
	
	//Compares the gamescore against the highscores and returns its ranked  position
	public int calcRank(int gameScore){
		int rank = 0;
		for(Highscore score : highscores){
    		if(gameScore > score.getScore())
    			break;
    		rank++;
    	}
		return rank;
	}
	
	public ArrayList<Highscore> getHighscore(){
		return highscores;
	}

}
