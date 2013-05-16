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
        
        //ui
        public static Image DAMAGEBACKGROUND;
        public static Image HEALTHBACKGROUND;
        public static Image SPEEDBACKGROUND;
        public static Image MACHINEGUNICON;
        public static Image ROCKETICON;
        public static Image SHOTGUNICON;
        public static Image SINGLESHOTICON;
        public static Image[] ATTACKTYPEICONS;
        public static final int SINGLESHOT=0;
        public static final int SHOTGUN=1;
        public static final int MACHINEGUN=2;
        public static final int ROCKET=3;
        public static Image TRAITBORDER;
		public static Image FILLER;
        
        //creatures
        public static Image CREATUREIMAGE1;
        public static Image CREATUREIMAGE2;
        public static Image CREATUREIMAGE3;
        public static Image CREATUREIMAGE4;
        public static Image CREATUREIMAGE5;
        public static Image CREATUREIMAGE6;
        public static Image CREATUREIMAGE7;
        public static Image[] CREATUREIMAGES = {};
        
        //traits
        public static Image ACCELERATION_ICON;
        public static Image ACCELERATION_TAG;
        public static Image ATTACKSPEED_ICON;
        public static Image ATTACKSPEED_TAG;
        public static Image DAMAGE_ICON;
        public static Image DAMAGE_TAG;
        public static Image HANDLING_ICON;
        public static Image HANDLING_TAG;
        public static Image HEALTH_ICON;
        public static Image HEALTH_TAG;
        public static Image LIFESPAN_ICON;
        public static Image LIFESPAN_TAG;
        public static Image SHIELD_ICON;
        public static Image SHIELD_TAG;
        public static Image SPEED_ICON;
        public static Image SPEED_TAG;
       
        //bullets
        public static Image bulletImage1;
        public static Image bulletImage2;
        public static Image rocketImage;
		public static Image dnaImage;
        public static Image[] BULLETIMAGES;
		
		//particles
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
	        DAMAGEBACKGROUND = new Image("assets/image/UI/Damage.png");
	        HEALTHBACKGROUND = new Image("assets/image/UI/Health.png");
	        SPEEDBACKGROUND = new Image("assets/image/UI/Speed.png");
	        MACHINEGUNICON = new Image("assets/image/UI/MachineGun.png");
	        ROCKETICON = new Image("assets/image/UI/RocketIcon.png");
	        SHOTGUNICON = new Image("assets/image/UI/ShotgunIcon.png");
	        SINGLESHOTICON = new Image("assets/image/UI/SingleShotIcon.png");
	        ATTACKTYPEICONS = new Image[]{SINGLESHOTICON,SHOTGUNICON,MACHINEGUNICON,ROCKETICON};
	        TRAITBORDER = new Image("assets/image/UI/TraitBorder.png");
	        FILLER = new Image("assets/image/UI/filler.png");
	        ACCELERATION_ICON = new Image("assets/image/UI/traits/acceleration.png");
	        ACCELERATION_TAG = new Image("assets/image/traits/acceleration.png");
	        ATTACKSPEED_ICON = new Image("assets/image/UI/traits/attackspeed.png");
	        ATTACKSPEED_TAG = new Image("assets/image/traits/attackspeed.png");
	        DAMAGE_ICON = new Image("assets/image/UI/traits/damage.png");
	        DAMAGE_TAG = new Image("assets/image/traits/damage.png");
	        HANDLING_ICON = new Image("assets/image/UI/traits/handling.png");
	        HANDLING_TAG = new Image("assets/image/traits/handling.png");
	        HEALTH_ICON = new Image("assets/image/UI/traits/health.png");
	        HEALTH_TAG = new Image("assets/image/traits/health.png");
	        LIFESPAN_ICON = new Image("assets/image/UI/traits/lifespan.png");
	        LIFESPAN_TAG = new Image("assets/image/traits/lifespan.png");
	        SHIELD_ICON = new Image("assets/image/UI/traits/shield.png");
	        SHIELD_TAG = new Image("assets/image/traits/shield.png");
	        SPEED_ICON = new Image("assets/image/UI/traits/speed.png");
	        SPEED_TAG = new Image("assets/image/traits/speed.png");
		}

        public static final int bullet1Width = 16;
        //creature default stats
        //Move Variables
        public static final float TOP_SPEED=6;
        public static final float ACCELERATION=1f;
        public static final float HANDLING=1;
        
        public static final float SPRINT_TIME = 1000;
        public static final float SPRINT_RESTITUTION = 0.5f;
        public static final float SPRINT_MODIFIER=1.5f;
        //Health Variables
        public static final int HEALTH=600;
        public static final float STAMINA=20;
        public static final boolean SHIELD=false;
		public static final float SHIELDRECHARGERATE = 0.05f;
		public static int SHIELD_COOLDOWN = 5000;

        //Damage Variables
        public static final int DAMAGE=20;
        public static final int COOLDOWN=300;
        public static final int ATTACK_TYPE=0;
        public static final float BULLET_VELOCITY = 8;
		public static final float BULLET_VELOCITY_INHERITENCE = 0.3f;

        
        //AttackType Variables
        public static final int NUMSHOTGUNBULLETS = 6;
        public static final double MACHINEGUNSPEED = 4;
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
        
        public static final int spawnTime = 1200;

        // Modifiers - buffs caused by genes
		public static final int HEALTH_BUFF = 2;
		public static final int STAMINA_BUFF = 2;
		public static final int SPEED_BUFF = 2;
		public static final int ACCELERATION_BUFF = 2;
		public static final int ATTACK_SPEED_BUFF = 2;
		public static final int DAMAGE_BUFF = 2;
		public static final int HANDLING_BUFF = 2;

		public static final int DEFAULT_ATTACK = 0;
		public static final int SHOTGUN_BULLETS = 1;
		public static final int MACHINE_GUN_BULLETS = 2;
		public static final int ROCKET_BULLETS = 3;

		public static final int NUMPARTICLESEXPLOSION = 5;

		public static final float PARTICLESPEED = 0.8f;

		public static final int ICON_SPACING = 10;





        
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