public class Fighter {

    public static final int WEALTH_CEILING = 500000;

    public static void main(String[] args) {
        testBasicStrategy(10000);
    }

    public static void testBasicStrategy(int nTrials){
        int initialWealth = 50000;

        // test bet sizes in 1k increments up to 10k
        for(int betSize = 1000; betSize<= 3000; betSize+=100) {
            int hitMin = 0;

            long totalWealth = 0;
            for (int i = 0; i < nTrials; i++ ) {
                int diff = runBasicStrategy(initialWealth, betSize);
                totalWealth += diff;

                if (diff == 0) hitMin++;
            }

            System.out.printf("EV of basic strategy with bet size %d: %f\n", betSize, totalWealth / 1.0 / nTrials);
            System.out.printf("Busts: %f percent of the time\n", hitMin * 100.0 / nTrials);
        }
    }


    // continuously generate level 51 chickens and bet 10,000 on them (or their higher values)
    public static int runBasicStrategy(int wealth, int defaultBetSize){
        Chicken cock = new Chicken(51);
        wealth -= cock.cost;


        while (wealth >= 0 && wealth <= WEALTH_CEILING){
            int bet = (int) Math.min(defaultBetSize * (1.00 + 0.5*(cock.level - 51)), wealth);
            wealth += cock.fight(bet);

            // busted
            if(wealth == 0){
                break;
            }

            if(!cock.alive){
                cock = new Chicken(51);
                if(cock.cost > wealth) {
                    // couldn't afford new chicken
                    wealth = 0;
                } else {
                    wealth -= cock.cost;
                }
            }
        }

        //System.out.println("Ended with wealth: " + wealth);
        return wealth;
    }
}
