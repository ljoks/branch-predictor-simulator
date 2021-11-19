import java.lang.Math;

public class Smith {
    int bits;
    int counter;
    int max; // max number the counter can reach
    int taken; // predict taken if >= this
    int predictions;
    int mispredictions;

    public Smith(int bits) {
        this.bits = bits;
        this.counter = this.taken = (int) Math.pow(2, bits-1);
        this.max = (int) Math.pow(2, bits) - 1;
        this.predictions = 0;
        this.mispredictions = 0;
    }

    void predict(String pc, boolean actual_outcome) {
        this.predictions++;

        // make a prediction
        boolean prediction = false;
        if(counter >= taken) prediction = true;

        // update counter based on actual outcome
        if(actual_outcome == true && counter < max) counter++;
        else if(actual_outcome == false && counter > 0) counter--;

        //update mispredictions
        if(prediction != actual_outcome) this.mispredictions++;
    }

    void print_final_content() {
        System.out.print("FINAL COUNTER CONTENT:\t" + counter);
    }
}
