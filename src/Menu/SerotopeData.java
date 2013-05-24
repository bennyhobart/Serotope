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
	public static final int MAXGENESID = 3;
	
	public SerotopeData(String hsfile, String achvfile, String csfile) {
		highscoreFile = hsfile;
		achievementFile = achvfile;
		curStatsFile = csfile;
		readInHighscores(hsfile);
		readInAchievements(achvfile, csfile);
	}

	public static SerotopeData getInstance(String hsfile, String achvfile, String csfile){
		if(instance == null){
			instance = new SerotopeData(hsfile,achvfile,csfile);
		}
		return instance;
	}

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
	
	public void drawHighscores(int x, int y, int spacing, Graphics g){
		//Draw each highscore on the page in their ranked order
		int scorePosY = y;
		int rank = 1;
		for(Highscore highscore : highscores){
			highscore.drawLine(x, scorePosY, rank, g);
			rank++;
			scorePosY += spacing;
		}
	}

	public void drawMedals(int x, int y, GameContainer gc, Graphics g) throws SlickException{
		//Draw each highscore on the page in their ranked order
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
	
	public void updateAchievements(GameStats gameStats){
		int gamesPlayed = medals.get(GAMESPLAYEDID).getCurrent();
		medals.get(GAMESPLAYEDID).setCurrent(gamesPlayed+1);
		int enemiesKilled = medals.get(ENEMIESKILLEDID).getCurrent();
		medals.get(ENEMIESKILLEDID).setCurrent(enemiesKilled + gameStats.getEnemiesKilled());
		int regenerations = medals.get(REGENERATIONSID).getCurrent();
		medals.get(REGENERATIONSID).setCurrent(regenerations + gameStats.getGenerations());
		int maxGenes = medals.get(MAXGENESID).getCurrent();
		if(gameStats.getMaxNumGenes()>maxGenes){
			medals.get(MAXGENESID).setCurrent(gameStats.getMaxNumGenes());
		}
		for(Medal medal : medals)
			medal.updateMedal();
		writeToAchievements();
	}
	
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