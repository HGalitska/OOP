package xmlWork;

import candy.Candy;

import java.util.Comparator;

public class Sorter implements Comparator<Candy> {
    @Override
    public int compare(Candy a, Candy b) {
        if (a.getEnergy() > b.getEnergy()) return 1;
        if (a.getEnergy() < b.getEnergy()) return -1;
        return 0;
    }

}
