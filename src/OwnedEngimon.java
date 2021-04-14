import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OwnedEngimon extends Engimon{
    private ArrayList<String> parentName;
    private String status;
    private Position position;
    private static final Map<String, String> percakapan;

    static {
        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("ikan", "halo saya api");
        temp.put("kambing", "halo saya air");
        temp.put("beruang","halo saya listrik");
        temp.put("kelelawar","halo saya tanah");
        temp.put("kadal","halo saya es");
        temp.put("siamang","halo saya kebakaran");
        temp.put("mammoth", "halo saya LDR");
        temp.put("kecoa", "halo saya subur");

        percakapan = Collections.unmodifiableMap(temp);
    }

    public OwnedEngimon(String name, String species, int life, int level){
        super(name, species, life, level);
        this.status = "owned";
        this.parentName = new ArrayList<String>(2);
        this.position = new Position();
    }

//    public OwnedEngimon(String _name, String _species, int life, int level){
//        super(life, level);
//        this.name = _name;
//        this.species = _species;
//        this.status = "owned";
//        this.parentName = new ArrayList<String>(2);
//        this.position = new Position();
//    }

    public ArrayList<String> getParentName() {
        return parentName;
    }

    public void setParentName(String parentName1, String parentName2) {
        this.parentName.set(0,parentName1);
        this.parentName.set(1,parentName2);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int _x, int _y) {
        this.position.setX(_x);
        this.position.setY(_y);
    }

    public void interact(){
        System.out.println("[" + this.name + "]: " + percakapan.get(this.species));
    }

    public void moveActive(int _x, int _y){
        if (_x != 29){
            this.position.setX(_x+1);
            this.position.setY(_y);
        } else if (_y != 0){
            this.position.setX(_x);
            this.position.setY(_y-1);
        } else {
            this.position.setX(_x);
            this.position.setY(_y+1);
        }
    }

    public void displayDetail(){
        System.out.println("=====================Info Engimon=====================");
        System.out.println("Nama                   : " + this.name);
        System.out.println("Species                : " + this.species);
        System.out.println("Skill                  : ");

        for (int i = 0; i< this.skills.size(); i++){
            System.out.printf("    " + i+1 + ". ");
            this.skills.get(i).displaySkill();
            System.out.println();
        }
        System.out.printf("Element                : ");
        for (int i = 0; i< this.elements.size(); i++){
            System.out.printf(i+1 + ". " + this.elements.get(i));
            if (i != this.elements.size() - 1){
                System.out.printf(" | ");
            }
        }
        System.out.println();
        System.out.println("Level                  : " + this.level);
        System.out.println("Experience             : " + this.experience);
        System.out.println("Cumulative Experience  : " + this.cumulativeExperience);
        System.out.println("Abi                    : " + this.getParentName().get(0));
        System.out.println("Mami                   : " + this.getParentName().get(1));
        System.out.println("Status                 : " + this.getStatus());
        System.out.println("=======================================================");

    }

    public boolean fight(Engimon enemy){
        float myPower = 0;
        myPower += enemy.getStrongestEl() * enemy.getLevel();
        for (int i = 0; i < this.skills.size(); i++) {
            myPower += (this.skills.get(i).getBasePower() * this.skills.get(i).getMasteryLevel());
        }

        float enemyPower = 0;
        enemyPower += enemy.getStrongestEl() * enemy.getLevel();
        for (int i = 0; i < enemy.getNSkill(); i++) {
            enemyPower += enemy.getSkill().get(i).getBasePower() * enemy.getSkill().get(i).getMasteryLevel();
        }
        System.out.println("======================Info Battle======================");
        System.out.println("My Power       : " + myPower);
        System.out.println("Enemy Power    : " + enemyPower);

        return myPower > enemyPower;
    }
}
