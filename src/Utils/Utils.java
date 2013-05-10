package Utils;
import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.opengl.Texture;

import Serotope.Bullet;
import Serotope.Creature;
import Serotope.DNA;
import Serotope.GameObject;
import Serotope.GameWorld;


public class Utils {
        public static final float SCALE = 100;
        
        public static final String BACKGROUND = "assets/image/Background.png";
        
        public static final String CREATUREIMAGE1 = "assets/image/Creature1.png";
        public static final String CREATUREIMAGE2 = "assets/image/Creature2.png";
        public static final String CREATUREIMAGE3 = "assets/image/Creature3.png";
        public static final String CREATUREIMAGE4 = "assets/image/Creature4.png";
        public static final String CREATUREIMAGE5 = "assets/image/Creature5.png";
        public static final String CREATUREIMAGE6 = "assets/image/Creature6.png";
        public static final String CREATUREIMAGE7 = "assets/image/Creature7.png";
        public static final String[] CREATUREIMAGES = {CREATUREIMAGE1,CREATUREIMAGE2,CREATUREIMAGE3,CREATUREIMAGE4,CREATUREIMAGE5,CREATUREIMAGE6,CREATUREIMAGE7};
        public static final String bulletImage1 = "assets/image/Bullet1.png";
        public static final String bulletImage2 = "assets/image/Bullet2.png";
		public static final String dnaImage = "assets/image/Dna.png";
        public static final String[] BULLETIMAGES = {bulletImage2,bulletImage1,bulletImage2};

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
        
        public static final int spawnTime = 300;

        // Modifiers - buffs caused by genes
		public static final int HEALTH_BUFF = 50;
		public static final float STAMINA_BUFF = 0.5f;
		public static final int SPEED_BUFF = 10;
		public static final float ACCELERATION_BUFF = 0.5f;
		public static final int ATTACK_SPEED_BUFF = 50;
		public static final int DAMAGE_BUFF = 5;


        
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
        public ArrayList<Creature> getCreatures(ContactEdge contacts) {
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