import static org.junit.Assert.*;

import org.junit.Test;

public class PHDTester {

	@Test
	public void testConstructor1() {
		PHD p1= new PHD("daryl",  'm',2017 , 8) ;
		assertEquals( "daryl" , p1.getName() ) ;
		assertEquals( 2017 , p1.getYear() ) ;
		assertEquals( 8 , p1.getMonth() ) ;
		assertEquals( true , p1.isMale() ) ;
		assertEquals( null, p1.advisor1() ) ;
		assertEquals( null, p1.advisor2() ) ;
		assertEquals ( 0 , p1.numAdvises()) ;

		PHD p2= new PHD("Christina", 'f', 2016, 7) ;
		assertEquals( "Christina" , p2.getName() ) ;
		assertEquals( 2016 , p2.getYear() ) ;
		assertEquals( 7 , p2.getMonth() ) ;
		assertEquals( false, p2.isMale() ) ;
		assertEquals( null, p2.advisor1() ) ;
		assertEquals( null, p2.advisor2() ) ;
		assertEquals ( 0 , p2.numAdvises()) ;

	}
	
	@Test
    public void testSetters1() {
		PHD p1= new PHD("daryl",  'm',2017 , 8) ;
		PHD p2= new PHD("Christina", 'f', 2017, 8) ;
		PHD p3= new PHD("Jim",  'm', 2017, 8) ;
		p2.setAdvisor1( p1 ) ; 
		p3.setAdvisor1( p1 ) ;
		p3.setAdvisor2( p2 ) ;
		
		assertEquals( p1, p2.advisor1() );
		assertEquals( null, p2.advisor2() );
		assertEquals( p1, p3.advisor1() );
		assertEquals( p2, p3.advisor2() );
		assertEquals( null, p1.advisor1() );
		assertEquals( null, p1.advisor2() );
		assertEquals( 2, p1.numAdvises()) ;
		assertEquals( 1, p2.numAdvises()) ;
		assertEquals( 0, p3.numAdvises()) ;
	}
	
	@Test
    public void testConstructor2() {
		PHD p1= new PHD("daryl",  'm',2017 , 8) ;
		PHD p2= new PHD("Christina", 'f', 2016, 7, p1) ;
		PHD p3= new PHD("Jim",  'm', 2017, 8, p1 , p2) ;
		
		assertEquals( "Christina" , p2.getName() ) ;
		assertEquals( 2016 , p2.getYear() ) ;
		assertEquals( 7 , p2.getMonth() ) ;
		assertEquals( false , p2.isMale() ) ;
		assertEquals( p1, p2.advisor1() );
		assertEquals( null, p2.advisor2() );
		
		assertEquals( "Jim" , p3.getName() ) ;
		assertEquals( 2017 , p3.getYear() ) ;
		assertEquals( 8 , p3.getMonth() ) ;
		assertEquals( true , p3.isMale() ) ;
		assertEquals( p1, p3.advisor1() );
		assertEquals( p2, p3.advisor2() );
		assertEquals( 0, p3.numAdvises()) ;
		assertEquals( 1, p2.numAdvises()) ;
		assertEquals( 2, p1.numAdvises()) ;

	}
	
	@Test
    public void testMethods() {
		PHD p1= new PHD("daryl",  'm',2017 , 8) ;
		PHD p2= new PHD("Christina", 'f', 2017, 7, p1) ;
		PHD p3= new PHD("Jim",  'm', 2016, 8, p1 , p2) ;
		
        assertEquals( false, p1.gotBefore(p3))	;
        assertEquals( false, p1.gotBefore(p2))	;
        assertEquals( false, p1.arePHDSiblings(p3))	;
        assertEquals( false, p1.arePHDSiblings(p2))	;
               
        assertEquals( true, p2.gotBefore(p1))	;
        assertEquals( false, p2.gotBefore(p3))	;
        assertEquals( true, p2.arePHDSiblings(p3))	;
        assertEquals( false, p2.arePHDSiblings(p1))	;
		
        assertEquals( true, p3.gotBefore(p1))	;
        assertEquals( true, p3.gotBefore(p2))	;
        assertEquals( false, p3.arePHDSiblings(p1))	;
        assertEquals( true, p3.arePHDSiblings(p2))	;

	}
	
	
	
}
