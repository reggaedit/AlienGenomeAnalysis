import java.util.Arrays;
import java.util.Arrays.*;

public class AlienAnalysis {
	public static void main(String[] args)
	{
		//define the path to the directory containing the files
		String filePath = "/home/dave/programming/java/workspace/alien_data/";
		String fileType = "1k_digest_";
		
		//the Genome format takes three strings for writing the file, the last ones being the cutting points.
		Genome genOne = new Genome(filePath,fileType,"BC");
		Genome genTwo = new Genome(filePath,fileType,"DE");
		Genome genThree = new Genome(filePath,fileType,"DFAD");
		Genome genFour = new Genome(filePath,fileType,"EDA");
		
		String[] genomeArrayOne = genOne.getGenomeArray();
		String[] genomeArrayTwo = genTwo.getGenomeArray();
		String[] genomeArrayThree = genThree.getGenomeArray();
		String[] genomeArrayFour = genFour.getGenomeArray();
		System.out.println(genomeArrayThree[0]);
		System.out.println(genomeArrayThree[1]);
		//System.out.println(genOne.getLastGenomeWord());
		//System.out.println(genTwo.getLastGenomeWord());
		//System.out.println(genThree.getLastGenomeWord());
		//System.out.println(genFour.getLastGenomeWord());
		
		compareItems(genOne,genTwo,genThree,genFour);
		
		System.exit(0);
	}
	
	//helper method to compare two items
	private static void compareItems(Genome genOne, Genome genTwo, Genome genThree, Genome genFour)
	{
		
		//Define the endpiece to be analysed 
		String endPiece = genThree.getLastGenomeWord();
		String[] genomeArrayOne = genOne.getGenomeArray();
		
		//Define two strings to be built up
		//sequence one is always the end piece
		String sequenceOne = endPiece;
		String sequenceTwo = "";
		int startLocationModifier = 0;
		
		//assume the match is false
		
		String arrayItem = "";
		boolean isMatch = false;
		
		//debugging
		System.out.println("The length of genomeArrayOne is: " + genomeArrayOne.length);
		
		//Iterate through every item in the genomeArrayOne
		for (int i = 0; i < genomeArrayOne.length; i++)
		{
			//check if the sequence matches the end of the endPiece using isMatchWithEndPiece() helper 
			isMatch = isMatchWithEndPiece(sequenceOne.length() - startLocationModifier,sequenceOne,genomeArrayOne[i]);
			if(isMatch)
			{
				//debugging
				System.out.println("The length of sequenceTwo + length of ArrayOneItem is " + (sequenceTwo.length() + genomeArrayOne[i].length()));
				System.out.println("The length of sequence one is: "+ sequenceOne.length());
				System.out.println("i is: " + i);
				//check if the new SequenceTwo is going to be shorter than the endPiece
				if ((sequenceTwo.length() + genomeArrayOne[i].length()) < sequenceOne.length())
				{
					//sequenceOne = ;
					sequenceTwo = genomeArrayOne[i] + sequenceTwo;
					startLocationModifier += genomeArrayOne[i].length();
				} else if ((int) sequenceTwo.length() + genomeArrayOne[i].length() == sequenceOne.length())
				{
					System.out.println("You're fooked mate. The two comparison strings equal each other. Try using a different set of sequences to compare.");
				} else
				{
					//basically need to swap over the values of sequenceOne and -Two so that sequenceOne remains the long one.
					String placeholder = sequenceOne;
					sequenceOne = genomeArrayOne[i] + sequenceTwo;
					sequenceTwo = placeholder;
					startLocationModifier += genomeArrayOne[i].length();
				}//end length check loop.
				
				//check if we have reached the end, and start from beginning till there are no more matches.
				if (i == genomeArrayOne.length - 1)
				{
					i = 0;
				}
			}
			
			//debugging
			System.out.println("One is " + sequenceOne);
			System.out.println("Two is " + sequenceTwo);
		}//end array for loop iterator
		System.out.println(sequenceOne);
	}//end compareItems()
	
	private static boolean isMatchWithEndPiece(int startLocation, String endPiece, String genomeWord)
	{
		//startLocation is where I will start comparing against in the endPiece
		//	it is the location starting from the end of the string.
		
		//assume it is not a match
		boolean isMatch = false;
		int wordLen = genomeWord.length();
		//int endLen = endPiece.length();
		
		if(wordLen < startLocation)
		{
			//there is no overlap
			//	endPiece:			-----
			//	genomeWord:			  ---
			if (genomeWord.equals(endPiece.substring(startLocation - wordLen, startLocation)))
			{
				isMatch = true;
				return isMatch;
			} else
			{
				isMatch = false;
				return isMatch;
			}
		} else
		{
			//there is an overlap
			//	endPiece:			-----
			//	genomeWord:		------
			int endPieceRemainder = startLocation;
			
			//check if the overlapping substring of the genomeWord equals the remainder of the endPiece
			if (genomeWord.substring(0,1).equals(endPiece.substring(startLocation - wordLen, startLocation)))
			{
				isMatch = true;
				return isMatch;
			} else
			{
				isMatch = false;
				return isMatch;
			}
		}//end if loop
	}//end isMatchWithEndPiece()
}//end main