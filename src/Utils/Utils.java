package Utils;
import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Serotope.Bullet;
import Serotope.Creature;
import Serotope.DNA;
import Serotope.GameObject;
import Serotope.GameWorld;


public class Utils {
        public static final float SCALE = 100;
        
        public static final String BACKGROUND = "assets/image/Background.png";
        
        public static Image CREATUREIMAGE1;
        public static Image CREATUREIMAGE2;
        public static Image CREATUREIMAGE3;
        public static Image CREATUREIMAGE4;
        public static Image CREATUREIMAGE5;
        public static Image CREATUREIMAGE6;
        public static Image CREATUREIMAGE7;
        public static Image[] CREATUREIMAGES = {};
        public static Image bulletImage1;
        public static Image bulletImage2;
        public static Image rocketImage;
		public static Image dnaImage;
        public static Image[] BULLETIMAGES;
		public static final int[] BULLETSIZES = {8,16,8,32};
		public static Image BLUESPECKIMAGE;
		public static Image BROWNSPECKIMAGE;
		public static Image GREENSPECKIMAGE;
		public static Image ORANGESPECKIMAGE;
		public static Image PINKSPECKIMAGE;
		public static Image PURPLESPECKIMAGE;
		public static Image REDSPECKIMAGE;
		public static Image WHITESPECKIMAGE;
		public static Image YELLOWSPECKIMAGE;
		public static Image BLACKSPECKIMAGE;
		public static Image[] PARTICLES; 
		public static void initImages() throws SlickException {
			 BLUESPECKIMAGE = new Image("assets/image/particles/bluespeck.png");
			 BROWNSPECKIMAGE = new Image("assets/image/particles/brownspeck.png");
			 GREENSPECKIMAGE = new Image("assets/image/particles/greenspeck.png");
			 ORANGESPECKIMAGE = new Image("assets/image/particles/orangespeck.png");
			 PINKSPECKIMAGE = new Image("assets/image/particles/pinkspeck.png");
			 PURPLESPECKIMAGE = new Image("assets/image/particles/purplespeck.png");
			 REDSPECKIMAGE = new Image("assets/image/particles/redspeck.png");
			 WHITESPECKIMAGE = new Image("assets/image/particles/whitespeck.png");
			 YELLOWSPECKIMAGE = new Image("assets/image/particles/yellowspeck.png");
			 BLACKSPECKIMAGE = new Image("assets/image/particles/blackspeck.png");
			 bulletImage1 = new Image("assets/image/Bullet1.png");
	          bulletImage2 = new Image("assets/image/Bullet2.png");
	          rocketImage = new Image("assets/image/Rocket.png");
			  dnaImage =new Image( "assets/image/Dna.png");
	          CREATUREIMAGE1 = new Image("assets/image/Creature1.png");
	         CREATUREIMAGE2 = new Image("assets/image/Creature2.png");
	         CREATUREIMAGE3 =new Image( "assets/image/Creature3.png");
	         CREATUREIMAGE4 = new Image("assets/image/Creature4.png");
	         CREATUREIMAGE5 =new Image( "assets/image/Creature5.png");
	         CREATUREIMAGE6 =new Image( "assets/image/Creature6.png");
	         CREATUREIMAGE7 =new Image( "assets/image/Creature7.png");
	        bulletImage1 =new Image(  "assets/image/Bullet1.png");
	        bulletImage2 =new Image(  "assets/image/Bullet2.png");
	        rocketImage =new Image(  "assets/image/Rocket.png");
			dnaImage =new Image(  "assets/image/Dna.png");
			CREATUREIMAGES = new Image[]{CREATUREIMAGE1,CREATUREIMAGE2,CREATUREIMAGE3,CREATUREIMAGE4,CREATUREIMAGE5,CREATUREIMAGE6,CREATUREIMAGE7};
	        BULLETIMAGES = new Image[]{bulletImage2,bulletImage1,bulletImage2,rocketImage};
	        PARTICLES = new Image[]{BLUESPECKIMAGE,PURPLESPECKIMAGE,REDSPECKIMAGE,BLACKSPECKIMAGE};
		}

        public static final int bullet1Width = 16;
        //creature default stats
        //Move Variables
        public static final float topSpeed=10;
        public static final float acceleration=1f;
        public static final float handling=1;
        
        public static final float sprintTime = 1000;
        public static final float sprintRestitution = 0.5f;
        public static final float sprintModifier=1.5f;
        //Health Variables
        public static final int health=100;
        public static final float stamina=1;
        public static final boolean shield=false;
        //Damage Variables
        public static final int damage=10;
        public static final int cooldown=100;
        public static final int attackType=0;
        public static final float BULLETVELOCITY = 8;
        
        //AttackType Variables
        public static final int NUMSHOTGUNBULLETS = 6;
        public static final double MACHINEGUNSPEED = 10;
        public static final double MACHINEGUNSPRAY = Math.PI/8;
        public static final float MACHINEGUNBULLETSPEED = 0.7f;
		public static final float ROCKETLAUNCHERDAMAGE = 4f;
		public static final float ROCKETLAUNCHERSPEED = 0.8f;
		public static final float ROCKETEXPLOSIONRADIUS = 2;
		public static final float ROCKETEXPLOSIONDAMAGE = 0.4f;
        
        //Body Utils
        //clockwise to the vector between a and b
        public static Vec2 tangentialVector(Vec2 a,Vec2 b) {
                Vec2 dir=vectorBetween(a,b);
                dir.set(dir.y, -dir.x);
                return dir;
        }
        //returns a unit vector directed from a to be
        public static Vec2 vectorBetween(Vec2 a, Vec2 b) {
                Vec2 dir = new Vec2(b.sub(a));
                dir.normalize();
                return dir;
        }
        public static float lengthBetween(Vec2 a, Vec2 b) {
                return a.sub(b).length();
                
        }
        //rotate vector by angle -pi<a<pi rotates clockwise
        public static Vec2 rotateVector(Vec2 a,double d) {
                float x = a.x;
                float y = a.y;
                double cs = Math.cos(d);
                double sn = Math.sin(d);
                Vec2 newvec = new Vec2() ;
                newvec.x=(float) (x*cs-y*sn);
                newvec.y=(float) (x*sn+y*cs);
                return newvec;
        }

        
        //AI Querys and Defines
        public static final float AISEEK = 2;
        public static final float WANDERRADIUS = 5f;
        public static final float WANDERDISTANCE =4f;
        public static final float WANDERJITTER = 0.1f;
        
        public static final int spawnTime = 600;

        // Modifiers - buffs caused by genes
		public static final int HEALTH_BUFF = 50;
		public static final float STAMINA_BUFF = 0.5f;
		public static final int SPEED_BUFF = 10;
		public static final float ACCELERATION_BUFF = 0.5f;
		public static final int ATTACK_SPEED_BUFF = 50;
		public static final int DAMAGE_BUFF = 5;

		public static final int defaultAttack = 0;
		public static final int shotgunBullets = 1;
		public static final int machineGunBullets = 2;

		public static final int NUMPARTICLESEXPLOSION = 5;

		public static final float PARTICLESPEED = 0.8f;







        
        public ArrayList<Bullet> getBullets(ArrayList<GameObject> gameObjects) {
                ArrayList<Bullet> creatures = new ArrayList<Bullet>();
                GameObject target;
                for(int i=0;i<gameObjects.size();i++) {
                        target = gameObjects.get(i);
                        if(target instanceof Bullet) {
                                creatures.add((Bullet) target);
                        }
                }
                return creatures;
        }
        public ArrayList<Bullet> getBullets(ContactEdge contacts) {
                ArrayList<Bullet> bullets = new ArrayList<Bullet>();
                GameObject target;
                while(contacts!=null) {
                        target = (GameObject) contacts.other.getUserData();
                        if(target instanceof Bullet) {
                                bullets.add((Bullet) target);
                        }
                        contacts=contacts.next;
                }
                return bullets;
        }
        public static ArrayList<Creature> getCreatures(ContactEdge contacts) {
                ArrayList<Creature> creatures = new ArrayList<Creature>();
                GameObject target;
                while(contacts!=null) {
                        target = (GameObject) contacts.other.getUserData();
                        if(target instanceof Creature) {
                                creatures.add((Creature) target);
                        }
                        contacts=contacts.next;
                }
                return creatures;
        }
        public static ArrayList<DNA> getDNA(ArrayList<GameObject> gameObjects) {
        	ArrayList<DNA> DNAList = new ArrayList<DNA>();
            GameObject target;
            for(int i=0;i<gameObjects.size();i++) {
                    target = gameObjects.get(i);
                    if(target instanceof DNA) {
                            DNAList.add((DNA) target);
                    }
            }
            return DNAList;
        }
        public static ArrayList<Creature> getCreatures(ArrayList<GameObject> gameObjects) {
                ArrayList<Creature> creatures = new ArrayList<Creature>();
                GameObject target;
                for(int i=0;i<gameObjects.size();i++) {
                        target = gameObjects.get(i);
                        if(target instanceof Creature) {
                                creatures.add((Creature) target);
                        }
                }
                return creatures;
        }
        public static ArrayList<GameObject> getGameObjectsAABB(AABB zone) {
                ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
                QueryCallbackHelper query = new QueryCallbackHelper();
                GameWorld.getGameWorld().getPhysicsWorld().queryAABB(query, zone);
                GameObject target;
                for(int i=0;i<query.fixtures.size();i++) {
                        target=(GameObject) query.fixtures.get(i).getBody().getUserData();
                        gameObjects.add(target);
                }
                return gameObjects;
        }
        public static Vec2 randomUnitVector() {
                float random1 = GameWorld.getRandomGenerator().nextFloat()*2-1;
                float random2 = GameWorld.getRandomGenerator().nextFloat()*2-1;
                Vec2 vector = new Vec2(random1,random2);
                vector.normalize();
                return vector;
        }
		public static ArrayList<DNA> getDNA(ContactEdge contacts) {
			ArrayList<DNA> DNAList = new ArrayList<DNA>();
            GameObject target;
            while(contacts!=null) {
                    target = (GameObject) contacts.other.getUserData();
                    if(target instanceof DNA) {
                            DNAList.add((DNA) target);
                    }
                    contacts=contacts.next;
            }
            return DNAList;
		}
}