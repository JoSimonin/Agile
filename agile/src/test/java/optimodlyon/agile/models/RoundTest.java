package optimodlyon.agile.models;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import org.assertj.core.api.Assertions;
import optimodlyon.agile.util.Time;

public class RoundTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRoundWarehouse() {
		Time ts = new Time(8,30,25);
		Warehouse w =new Warehouse((long)1, (float) 5, (float) 10, ts );
		Round myRound = new Round(w);
		Assertions.assertThat(myRound.getListPath()).isEmpty();
		assertSame(w,myRound.getStart());
		assertSame(w.getTimeStart(),myRound.getStartTime());
		assertSame(w.getTimeStart(),myRound.getEndTime());
	}

	@Test
	public void testRoundRound() {
		Time ts = new Time(8,30,25);
		Warehouse w =new Warehouse((long)1, (float) 5, (float) 10, ts );
		Round myRound = new Round(w);
		Round myRound2= new Round(myRound);
		Assertions.assertThat(myRound2.getListPath()).isEmpty();
		assertSame(w,myRound2.getStart());
		assertSame(w.getTimeStart(),myRound2.getStartTime());
		assertSame(w.getTimeStart(),myRound2.getEndTime());
		Round myRound3= new Round((Round)null);
	}

	@Test
	public void testRoundArrayListOfPathWarehouseTime() {
		Time ts = new Time(8,30,25);
		Warehouse w =new Warehouse((long)1, (float) 5, (float) 10, ts );
		Map<Long, List<Segment>>completeMap = new HashMap<Long, List<Segment>>();
        Intersection i0 = new Intersection((long)0,(float)5.0,(float)3.0);
        Intersection i1 = new Intersection((long)1,(float)3.0,(float)3.0);
        Intersection i2 = new Intersection((long)2,(float)4.0,(float)3.0);
        Intersection i3 = new Intersection((long)3,(float)1.0,(float)3.0);
        Intersection i4 = new Intersection((long)4,(float)1.0,(float)3.0);
        Intersection i5 = new Intersection((long)5,(float)1.0,(float)3.0);
        Segment s0 = new Segment(i0,i1,2);
        Segment s1 = new Segment(i1,i3,3);
        Segment s2 = new Segment(i0,i3,4);
        Segment s3 = new Segment(i0,i2,4);
        Segment s4 = new Segment(i2,i3,1);
        Segment s5 = new Segment(i2,i4,3);
        Segment s6 = new Segment(i3,i4,20);
        Segment s7 = new Segment(i3,i5,15);
        List<Segment> a0 = new ArrayList<Segment>();
        List<Segment> a1 = new ArrayList<Segment>();
        List<Segment> a2 = new ArrayList<Segment>();
        List<Segment> a3 = new ArrayList<Segment>();
        List<Segment> a4 = new ArrayList<Segment>();
        List<Segment> a5 = new ArrayList<Segment>();
        a0.add(s0);
        a0.add(s2);
        a0.add(s3);
        a1.add(s1);
        a2.add(s4);
        a2.add(s5);
        a3.add(s6);
        a3.add(s7);
        completeMap.put((long)0, a0);
        completeMap.put((long)1, a1);
        completeMap.put((long)2, a2);
        completeMap.put((long)3, a3);
        completeMap.put((long)4, a4);
        completeMap.put((long)5, a5);
	MapManagement.getInstance().getMap().setGraph(completeMap);
    List<Long> listIdInter1 = new ArrayList<Long>();
    listIdInter1.add(i0.getId());
    listIdInter1.add(i2.getId());
    listIdInter1.add(i3.getId());
    List<Long> listIdInter2 = new ArrayList<Long>();
    listIdInter2.add(i0.getId());
    listIdInter2.add(i3.getId());
    listIdInter2.add(i4.getId());
    Delivery myDelivery1= new Delivery(i3, 10);
    Delivery myDelivery2= new Delivery(i4, 20);
    Time myTime1 = new Time(0,0,30);
    Time myTime2 = new Time(0,12,12);
    Path myPath1=new Path(listIdInter1,myDelivery1,myTime1);
    Path myPath2=new Path(listIdInter2,myDelivery2,myTime2);
    ArrayList<Path> listPath = new ArrayList<Path>();
    listPath.add(myPath1);
    listPath.add(myPath2);
	Round myRound = new Round(listPath,w);
	Assertions.assertThat(myRound.getListPath()).containsAll(listPath);
	assertSame(myRound.getStart(),w);
	assertSame(myRound.getStartTime(),w.getTimeStart());
	assertSame(myRound.getTotalDuration());
	assertSame(myRound.getEndTime(),myRound.getTotalDuration());
	
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRoundTimes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStartTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetListPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetListPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEndTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEndTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStartTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalDuration() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
