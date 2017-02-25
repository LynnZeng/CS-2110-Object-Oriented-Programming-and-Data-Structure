/** NetId: zz449, lz455 . Time spent: 5 hours, 30 minutes.
 *  An instance maintains info about the PHD of a person. */

public class PHD {

	private String name ; // Name of the person, Length >0
	private char gender ; //gender of the person, 'f' for female, 'm' for male
	private int year ;//year PHD was awarded,
	private int month ;//month PHD was awarded, in 1...12
	private PHD firstAdvisor ;//The first PHD advisor of this person null if unknown.
	private PHD secondAdvisor ;//The second PHD advisor, 1st is null, 2nd must be null
	private int numOfAdvisees ;// # of PHD advisees of this person

	/** Constructor: an instance for a person with name n, gender g, PHD year y,
and PHD month m. Its advisors are unknown, and it has no advisees.
Precondition: n has at least 1 char. m is in 1..12. g is 'f' for female or 'm' for male. */
	public PHD (String n, char g,int y, int m){
		assert n.length() > 0 ;
		name = n ; 

		assert g == 'f' || g == 'm' ;
		gender = g ;

		year = y ;

		assert m > 0 && m < 13; 
		month = m ;
	}

	/** Return the name of this person. */
	public String getName(){
		return name ; 
	}

	/** Return the year this person got their PHD. */
	public int getYear(){
		return year ;
	}

	/** Return the month this person got their PHD. */
	public int getMonth(){
		return month ;
	}

	/** Return the value of the sentence "This person is male." */
	public boolean isMale(){
		return ( gender == 'm' ) ; 		
	}

	/** Return the first advisor of this PHD (null if unknown).*/
	public PHD advisor1(){
		return firstAdvisor ;
	}

	/** Return the second advisor of this PHD (null if unknown or non-existent). */
	public PHD advisor2(){
		return secondAdvisor ;
	}

	/** Return the number of PHD advisees of this person.*/
	public int numAdvises() {
		return numOfAdvisees ;
	}

	/** Add p as the first advisor of this person.
Precondition: the first advisor is unknown and p is not null. */
	public void setAdvisor1(PHD p) {
		assert p != null && firstAdvisor == null ;
		firstAdvisor = p  ;
		firstAdvisor.numOfAdvisees = firstAdvisor.numOfAdvisees + 1 ;
	}

	/** Add p as the second advisor of this person.
Precondition: The first advisor (of this person) is known, the second advisor
is unknown, p is not null, and p is different from the first advisor. */
	public void setAdvisor2(PHD p) {
		assert firstAdvisor != null && secondAdvisor == null && p != null && p != firstAdvisor ;
		secondAdvisor = p ;
		secondAdvisor.numOfAdvisees = secondAdvisor.numOfAdvisees + 1 ;

	}

	/** Constructor: a PHD with name n, gender g, PHD year y, PHD month m,
first advisor adv, and no second advisor.
Precondition: n has at least 1 char, g is 'f' for female or 'm' for male,
m is in 1..12, and adv is not null. */
	public PHD (String n, char g,int y, int m, PHD adv){
		assert n.length() > 0 ;
		name = n ; 

		assert g == 'f' || g == 'm' ;
		gender = g ;

		year = y ;

		assert m >= 1 & m <= 12 ; 
		month = m ;

		assert adv != null ;
		firstAdvisor =  adv ;
		firstAdvisor.numOfAdvisees = firstAdvisor.numOfAdvisees + 1 ;
	}

	/** Constructor: a PHD with name n, gender g, PHD year y, PHD month m,
first advisor adv1, and second advisor adv2.
Precondition: n has at least 1 char. g is 'f' for female or 'm' for male.
m is in 1..12. adv1 and adv2 are not null. adv1 and adv2 are different. */
	public PHD (String n, char g,int y, int m, PHD adv1 , PHD adv2){
		assert n.length() > 0 ;
		name = n ; 

		assert g == 'f' || g == 'm' ;
		gender = g ;

		year = y ;

		assert m >= 1 & m <= 12 ; 
		month = m ;

		assert adv1 != null ;
		firstAdvisor =  adv1 ;
		firstAdvisor.numOfAdvisees = firstAdvisor.numOfAdvisees + 1 ;

		assert adv2 != null && adv2 != adv1 ;
		secondAdvisor = adv2 ;
		secondAdvisor.numOfAdvisees = secondAdvisor.numOfAdvisees + 1 ;
	}

	/** Return value of "this person got their PHD before p did."
Precondition: p is not null. */ 
	public boolean gotBefore(PHD p) {
		assert p != null ;
		return (p.year == year && p.month > month) || p.year > year;
		
	}
	
	/**Return value of ¡°p is not null and this person and p are
intellectual siblings." */
	public boolean arePHDSiblings(PHD p) {
		assert p != null && p.name != name;
		return (p.firstAdvisor == firstAdvisor && p.firstAdvisor != null) ||
				(p.secondAdvisor == secondAdvisor && p.secondAdvisor != null) ||
				(p.firstAdvisor == secondAdvisor && p.firstAdvisor != null)|| 
				(p.secondAdvisor == firstAdvisor && p.secondAdvisor != null ) ;
	}
}
