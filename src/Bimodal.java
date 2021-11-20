import java.lang.Math;

public class Bimodal extends Predictor {
    int m;
    int[] counters;

    public Bimodal(int m) {
        this.m = m;
        this.predictions = 0;
        this.mispredictions = 0;
        this.counters = new int[(int) Math.pow(2,m)];
        this.max = 7;
        this.taken = 4;

        for(int i = 0; i < counters.length; i++){
            counters[i] = 4;
        }
    }

    void predict(String pc, boolean actual_outcome) {
        this.predictions++;

        Long dec = Long.decode("0x" + pc);
        int index = (int) (dec / 4) % counters.length;

        // make a prediction
        boolean prediction = false;
        if(counters[index] >= taken) prediction = true;

        // update counter based on actual outcome
        if(actual_outcome == true && counters[index] < max) counters[index]++;
        else if(actual_outcome == false && counters[index] > 0) counters[index]--;

        //update mispredictions
        if(prediction != actual_outcome) this.mispredictions++;
    }

    void print_final_content() {
        System.out.println("FINAL BIMODAL CONTENTS");
        for(int i = 0; i < counters.length; i++) {
            System.out.println(i + "\t" + counters[i]);
        }
    }
}
