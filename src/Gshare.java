public class Gshare extends Predictor {
    int m;
    int n;
    int ghr;
    int[] counters;

    public Gshare(int m, int n) {
        this.m = m;
        this.n = n;
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
        // remove last two bits and get lowermost m bits
        int m_bits = (int) (dec / 4) % counters.length;

        // m-n MSB bits
        int m_n_bits = (int) (m_bits/Math.pow(2,n));

        // n LSB of PC
        int n_bits = (int) (dec / 4) % ((int) Math.pow(2,n));

        // xor GHR and n_bits
        int xor = n_bits ^ ghr;

        // concat xor and m-n MSB bits of PC
        int index = m_n_bits << n;
        index = index + xor;

        // make a prediction
        boolean prediction = false;
        if(counters[index] >= taken) prediction = true;

        // update counter based on actual outcome
        if(actual_outcome == true && counters[index] < max) counters[index]++;
        else if(actual_outcome == false && counters[index] > 0) counters[index]--;

        //update mispredictions
        if(prediction != actual_outcome) this.mispredictions++;

        //update GHR
        ghr = ghr >> 1;
        // if the actual outcome was true, insert 1 as MSB
        if(actual_outcome) ghr = ghr + (int) Math.pow(2,n-1);
        // otherwise inserting zero is effectively adding 0;
    }

    void print_final_content() {
        System.out.println("FINAL GSHARE CONTENTS");
        for(int i = 0; i < counters.length; i++) {
            System.out.println(i + "\t" + counters[i]);
        }
    }
}
