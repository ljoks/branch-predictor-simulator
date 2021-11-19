import java.io.File;
import java.util.Scanner;

class sim {
	public static void main(String[] args) {
		Bimodal predictor = new Bimodal(4);

		try{
			File trace = new File("traces\\jpeg_trace.txt");

			Scanner scan = new Scanner(trace);
			while(scan.hasNextLine()) {

				String instr = scan.nextLine();
				String[] tokens = instr.split(" ");


				//tokens[1] = t|n
				if(tokens[1].equals("t")) predictor.predict(tokens[0], true);
				else predictor.predict(tokens[0], false);
			}

			scan.close();

			//format misprediction rate
			double rate = predictor.mispredictions/ (double) predictor.predictions * 100;
			// String rate = String.format("%.2f", predictor.mispredictions/(double)predictor.predictions);

			// print results
			System.out.println("OUTPUT");
			System.out.println("number of predictions:\t" + predictor.predictions);
			System.out.println("number of mispredictions:\t" + predictor.mispredictions);
			System.out.println("misprediction rate:\t" + String.format("%.2f", rate) + "%");
			predictor.print_final_content();

		} catch(Exception e) {
			System.out.println("an error occurred: ");
			e.printStackTrace();
		}
		


	}
}
