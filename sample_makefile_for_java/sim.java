import java.io.File;
import java.util.Scanner;

class sim {
	public static void main(String[] args) {
		// Gshare predictor = new Gshare(11, 5);
		try{
			Predictor predictor;
			File trace;

			if(args[0].equals("smith")) {
				predictor = new Smith(Integer.parseInt(args[1]));
				trace = new File(args[2]);
			} else if(args[0].equals("bimodal")) {
				predictor = new Bimodal(Integer.parseInt(args[1]));
				trace = new File(args[2]);
			} else {
				predictor = new Gshare(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				trace = new File(args[3]);
			}
			// File trace = new File("traces\\jpeg_trace.txt");

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

			// remove the directories from the filepath string (whether windows or linux machine)
			args[args.length-1] = args[args.length-1].substring(args[args.length-1].lastIndexOf('\\') + 1);
			args[args.length-1] = args[args.length-1].substring(args[args.length-1].lastIndexOf('/') + 1);

			// print results
			System.out.println("COMMAND");
			System.out.print("./sim ");
			for(String arg : args) System.out.print(arg + " ");
			System.out.println("\nOUTPUT");
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
