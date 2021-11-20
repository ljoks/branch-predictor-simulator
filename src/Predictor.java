public abstract class Predictor {
    int predictions;
    int mispredictions;
    int max; // max number the counter can reach
    int taken; // predict taken if >= this

    abstract void predict(String pc, boolean actual_outcome);
    abstract void print_final_content();
}
