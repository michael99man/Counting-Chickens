import java.util.Random;

public class Chicken {

    int level;
    int cost;
    boolean alive = true;
    Random r;

    // starting from nothing, generate a chicken of a given level and compute the total cost (min bet size of 100)
    public Chicken(int targetLevel) {
        r = new Random();
        int cost = 100;
        int level = 50;

        assert(level >= 50 && level <= 70);

        while(true){

            if(level == targetLevel) break;
            int roll = 1 + r.nextInt(100);

            // we win the fight
            if(roll <= level){
                level++;
                // we win 100 bucks
                cost -= 100;
            } else {
                // lose the fight and buy a new level 50 chicken
                cost += 200;
                level = 50;
            }
        }

        this.cost = cost;
        this.level = level;
    }

    public int fight(int bet){
        assert(alive);

        int roll = 1 + r.nextInt(100);

        if(roll <= level){
            level++;
            level = Math.min(70, level);
            return bet;
        } else {
            alive = false;
            return -1*bet;
        }
    }
}
