import java.util.Arrays;

import FormatIO.*;
public class Genome {
	private String[] genomeArray;
	private String cuttingPoint;
	private String lastGenomeWord;
	
	//constructor method to create the genomeArray
	public Genome (String aFilePath, String aFileType, String aCuttingPoint) {
		
		//read the file from disk
		FileIn fin = new FileIn(aFilePath + aFileType + aCuttingPoint);
		
		//Use replaceAll with RegEx code to remove leading [" and trailing "].
		//NB: the files should end with a new line, otherwise the program hangs in
		//	some sort of infinite loop.
		String genome = fin.readLine().replaceAll("\\[\"", "").replaceAll("\"\\]", "");

		//Add the entries of the String genome to String array genomeArray using .slit("<delimiter>") 
		genomeArray = genome.split("\", \"");

		cuttingPoint = aCuttingPoint;
		
		//close file
		fin.close();
	}
	
	//accessor methods
	public String[] getGenomeArray() { return genomeArray;}
	public String getCuttingPoint() { return cuttingPoint;}
	//public String getLastGenomeWord() { return lastGenomeWord;}
	
	//find the last word in the genome sequence by comparing last letters to cutting point
	public String getLastGenomeWord()
	{
		//assume the match is true
		boolean match = true;
		String arrayItem = "";
		
		//need to check every item in array if the match is true or not
		for(int i = 0; i < genomeArray.length; i++)
		{
			//convert array item to string
			arrayItem = genomeArray[i];
			//for each character from right of this arrayItem
			for (int j = 1; j <= cuttingPoint.length(); j++)
			{
				//	check if last character from this arrayItem matches last char on cuttingPoint
				if (cuttingPoint.charAt(cuttingPoint.length() - j) == arrayItem.charAt(arrayItem.length() - j))
					//char match, try next char from end
				{
					match = true;
				} else
					//no match, we have a winner, exit for loop.
				{
					match = false;
					break;
				}
			}//end inner for loop
			
			if (!match)
			{
				return arrayItem;//return to main
			}
		}//end outer for loop
		
		//the case if they all match, then we can't tell which one is the last.
		return null;
	}
	
}