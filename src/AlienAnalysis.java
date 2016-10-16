public class AlienAnalysis {
	public static void main(String[] args)
	{
		//define the path to the directory containing the files
		String filePath = "/home/dave/programming/java/workspace/alien_data/";
		String fileType = "1k_digest_";
		
		//the Genome Object takes three strings for reading the file, the last ones being the
		//	cutting points.
		Genome genOne = new Genome(filePath,fileType,"BC");
		Genome genTwo = new Genome(filePath,fileType,"DE");
		Genome genThree = new Genome(filePath,fileType,"DFAD");
		Genome genFour = new Genome(filePath,fileType,"EDA");

		//this algorithm works for the 1k files, but fails in the 10k files after reading item 267 in the array. 
		//This is probably because the two sequences I am using equal each other, which is a
		//	weakness as my program can only sequence starting from the end. 
		
		String correctSequence = compareItems(genOne,genThree);
		System.out.println("The correct sequence is: " + correctSequence);
		System.exit(0);
	}
	
	//helper method to compare two items
	private static String compareItems(Genome genOne, Genome genTwo)
	{
		//Define the endpiece to be analysed 
		String endPiece = genTwo.getLastGenomeWord();
				
		//Define two strings to be built up
		//sequence one is always the end piece
		String sequenceOne = endPiece;
		String sequenceTwo = "";
		
		//determine the full length of the sequence
		String[] temporaryGenomeArray = genOne.getGenomeArray();
		int fullSequenceLength = (String.join("",temporaryGenomeArray)).length();
		
		int startLocationModifier = 0;
		
		//initialise the match with false
		boolean isMatch = false;
		boolean isShorter = false;
		boolean swapArrays = false;
		boolean comparingSequences = true;
			
		//Iterate through every item in the genomeArray
		while(comparingSequences)
		{
			//if the two strings being compared swap places, the array from which we take
			//	values swap their places too.
			if (swapArrays)//use the array the second Genome object from compareItems(1,2)
			{//yes  
				String[] genomeArray = genTwo.getGenomeArray();
				int arrayLength = genomeArray.length;

				//for each genomeArray (local) value:
				for(int i = 0; i < arrayLength; i++)
				{
					//check the boolean conditions
					isMatch = isMatchWithEndPiece(sequenceOne.length() - startLocationModifier,sequenceOne,genomeArray[i]);
					isShorter = isResultShorter((sequenceTwo.length() + genomeArray[i].length()), sequenceOne.length());

					if(isMatch)
					{//yes:
							if(isShorter)
							{//yes:
								//updateSequences(isShorter) (global)
								sequenceTwo = genomeArray[i] + sequenceTwo;
								startLocationModifier += genomeArray[i].length();
								
							} else
							{//no:
								//update and swap sequences
								String placeholder = sequenceOne;
								sequenceOne = genomeArray[i] + sequenceTwo;
								sequenceTwo = placeholder;
								startLocationModifier = sequenceTwo.length();
								//swap arrays
								swapArrays = !swapArrays;
							}
							
							//reset i and start from beginning till there are no more matches.
							i = -1;
					} else {;}//do nothing (restart for loop)	
				}//end for loop
			}else //use the first Genome object from compareItems(1,2)
			{//no
				String[] genomeArray = genOne.getGenomeArray();
				int arrayLength = genomeArray.length;
				
				//for each genomeArray (local) value:
				for(int i = 0; i < arrayLength; i++)
				{
					//check the boolean conditions
					isMatch = isMatchWithEndPiece(sequenceOne.length() - startLocationModifier,sequenceOne,genomeArray[i]);

					isShorter = isResultShorter((sequenceTwo.length() + genomeArray[i].length()), sequenceOne.length());

					if(isMatch)
					{//yes:
							if(isShorter)
							{//yes:
								//updateSequences(isShorter) (global)
								sequenceTwo = genomeArray[i] + sequenceTwo;
								startLocationModifier += genomeArray[i].length();
								
							} else
							{//no:
								//update and swap sequences
								String placeholder = sequenceOne;
								sequenceOne = genomeArray[i] + sequenceTwo;
								sequenceTwo = placeholder;
								startLocationModifier = sequenceTwo.length();
								//swap arrays
								swapArrays = !swapArrays;
							}
							
							//reset i and start from beginning till there are no more matches.
							i = -1;
					} else {;}//do nothing (restart for loop)		
				}//end for loop
				
			}//end if swapArrays check

			//check if finished
			if(sequenceOne.length() == fullSequenceLength)
			{//yes
				comparingSequences = false;
			} else
			{//no
				comparingSequences = true;	
			}
		}//end while loop
		
		return sequenceOne;		
		
	}//end compareItems()
	
	//Check if the resultant 
	private static boolean isResultShorter(int sequenceTwoLength, int sequenceOneLength)
	{
		if (sequenceOneLength > sequenceTwoLength)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private static boolean isMatchWithEndPiece(int startLocation, String sequenceOne, String genomeArrayItem)
	{
		boolean isMatch = false;
		int itemLen = genomeArrayItem.length();

		if(itemLen < startLocation)
		{
			//there is no overlap
			//	sequenceOne:				-----
			//	genomeArryayItem:			  ---
			if (genomeArrayItem.equals(sequenceOne.substring(startLocation - itemLen, startLocation)))
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
			//	sequenceOne:				-----
			//	genomeArryayItem:		------
			
			//overlap of sequenceOne
			String sequenceOneOverlap = sequenceOne.substring(0, startLocation);
			//overlap of genomeArrayItem
			String genomeArrayItemOverlap = genomeArrayItem.substring(genomeArrayItem.length()-sequenceOneOverlap.length(),genomeArrayItem.length());
			
			//check if the overlapping substring of the genomeWord equals the remainder of the endPiece
			if (sequenceOneOverlap.equals(genomeArrayItemOverlap))
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