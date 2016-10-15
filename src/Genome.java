import FormatIO.*;
public class Genome {
	private String[] genomeArray;
	
	//constructor method to create the genomeArray
	public Genome (String aFilePath, String aFileType, String aCuttingPoint) {
		
		//read the file from disk
		FileIn fin = new FileIn(aFilePath + aFileType + aCuttingPoint);
		
		 //Use replaceAll with RegEx code to remove leading [" and trailing "].
		 //NB: the files should end with a new line, otherwise the program hangs in
		 //	some sort of infinite loop. Open them in text editor first an append a
		 //	carriage return.

		String genome = fin.readLine().replaceAll("\\[\"", "").replaceAll("\"\\]", "");

		//Add the entries of the String genome to String array genomeArray using .slit("<delimiter>") 
		genomeArray = genome.split("\", \"");
		
		fin.close();
	}
	
	//accessor methods
	public String[] getGenomeArray() { return genomeArray;}
}
