package javabootcamp;

public enum Category{
	RNB("R&B"),
	ALTERNATIVE("ALTERNATIVE"), 
	
	JAZZ("JAZZ"),
	MODERN_JAZZ("MODERN_JAZZ"),
	ELECTRONIC_JAZZ("ELECTRONIC_JAZZ"),
	
	BLUES("BLUES"),
	
	ROCK("ROCK"),
	ELECTRONIC_ROCK("ELECTRONIC_ROCK"),
	ALTERNATIE_ROCK("ALTERNATIE_ROCK"),
	HARDROCK("HARDROCK"),
	
	NU_METAL("NU_METAL"),
	ALTERNATIVE_METAL("ALTERNATIVE_METAL"),
	DEATH_METAL("DEATH_METAL"),
	TRASH_METAL("TRASH_METAL"),
	
	TRAP("TRAP"),
	TRANS("TRANS"),
	
	HIP_HOP("HIP_HOP");

   private String value;
   private Category(String value)
   {
      this.value = value;
   }

   public String toString()
   {
      return this.value;
   }
}