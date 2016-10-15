import java.util.Arrays;
import java.util.Arrays.*;

public class AlienAnalysis {
	public static void main(String[] args)
	{
		//define the path to the directory containing the files
		String filePath = "/home/dave/programming/java/workspace/alien_data/";
		String fileType = "1k_digest_";
		
		//the Genome format takes three strings for writing the file.
		Genome genOne = new Genome(filePath,fileType,"BC");
		Genome genTwo = new Genome(filePath,fileType,"DE");
		Genome genThree = new Genome(filePath,fileType,"DFAD");
		Genome genFour = new Genome(filePath,fileType,"EDA");
		
		String[] genomeArrayOne = genOne.getGenomeArray();
		String[] genomeArrayTwo = genTwo.getGenomeArray();
		String[] genomeArrayThree = genThree.getGenomeArray();
		String[] genomeArrayFour = genFour.getGenomeArray();
		
		System.out.println(genOne.getLastGenomeWord());
		System.out.println(genTwo.getLastGenomeWord());
		System.out.println(genThree.getLastGenomeWord());
		System.out.println(genFour.getLastGenomeWord());
		
		
		/*
		//iterate through array to print each entry.
		for(int i = 0; i <= genomeArrayOne.length; i++)
		{
			try
			{
				System.out.println(genomeArrayOne[i]);
			} catch (ArrayIndexOutOfBoundsException e)
			{
				System.err.println("Reached end of array.");
			}
		}
		*/
		System.exit(0);
	}
}
