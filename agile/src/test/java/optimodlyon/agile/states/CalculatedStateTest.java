package optimodlyon.agile.states;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import optimodlyon.agile.models.CityMap;
import optimodlyon.agile.models.Deliverer;
import optimodlyon.agile.models.Intersection;
import optimodlyon.agile.models.MapManagement;
import optimodlyon.agile.models.Round;
import optimodlyon.agile.models.Segment;
import optimodlyon.agile.models.Warehouse;
import optimodlyon.agile.util.Time;

public class CalculatedStateTest {

	@Test
	public void testAddDelivery() {
		CalculatedState cs = new CalculatedState();
		cs.addDelivery((long)0);
		/*
		 * We initialize a pseudo map
		 */
		Map<Long, List<Segment>>completeMap = new HashMap<Long, List<Segment>>();
		Time t = new Time(8,0,0);
        Warehouse i0 = new Warehouse((long)0,(float)5.0,(float)3.0,t);
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
        Segment s8 = new Segment(i5,i4,5);
        Segment s9 = new Segment(i4,i0,5);
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
        a4.add(s9);
        a5.add(s8);
        completeMap.put((long)0, a0);
        completeMap.put((long)1, a1);
        completeMap.put((long)2, a2);
        completeMap.put((long)3, a3);
        completeMap.put((long)4, a4);
        completeMap.put((long)5, a5);
        CityMap citymap = new CityMap();
        citymap.setGraph(completeMap);
        MapManagement.getInstance().setMap(citymap);
        MapManagement.getInstance().setWarehouse(i0);
        
        /*
         * Test: addDelivery when no deliverer is instanciated
         */
        cs.addDelivery((long)5);

		Time t1 = new Time(12,0,0);
		Time t2 = new Time(18,0,0);
		Round r1 = new Round(i0, t );
		r1.setStartTime(t);
		r1.setEndTime(t1);
		Round r2 = new Round(i0, t1);
		r2.setStartTime(t1);
		r2.setEndTime(t2);
		List<Round> l1 = new ArrayList<Round>();
		l1.add(r1);
		l1.add(r2);
		
		Deliverer del1 = new Deliverer((long)0);
		Deliverer del2 = new Deliverer((long)1);
		Deliverer del3 = new Deliverer((long)2);
		
		Map<Long, Deliverer> ldel = new HashMap<Long, Deliverer>();
		ldel.put(del1.getId(), del1);
		ldel.put(del2.getId(), del2);
		ldel.put(del3.getId(), del3);
		MapManagement.getInstance().setListDeliverer(ldel);
		
		MapManagement.getInstance().assignRounds(l1);
		
		cs.addDelivery((long)5);
		assertEquals(MapManagement.getInstance().getListDeliverer().get((long)0).getListRound().size(),2,0);
		
		Round rnull = null;
		l1.add(rnull);
		ldel = new HashMap<Long, Deliverer>();
		ldel.put(del1.getId(), del1);
		ldel.put(del2.getId(), del2);
		ldel.put(del3.getId(), del3);
		MapManagement.getInstance().setListDeliverer(ldel);
		MapManagement.getInstance().assignRounds(l1);
		
		cs.addDelivery((long)5);
	}

	@Test
	public void testFindBestDeliverer() {
		CalculatedState cs = new CalculatedState();
		/*
		 * Test: find Best deliverer with a null deliverer map
		 */
		Map<Long, Deliverer> m = new HashMap<Long,Deliverer>();
		Deliverer d1 = cs.findBestDeliverer(m);
		assertEquals(d1, null);
		/*
		 * Test: find best deliverer with no deliverers
		 */
		Deliverer d2 = cs.findBestDeliverer(MapManagement.getInstance().getListDeliverer());
		assertEquals(d2, null);
		
		
		Time t = new Time(8,0,0);
		Time t1 = new Time(12,0,0);
		Time t2 = new Time(18,0,0);
		Warehouse wh = new Warehouse((long)0, t);
		Round r1 = new Round(wh, t );
		r1.setStartTime(t);
		r1.setEndTime(t1);
		Round r2 = new Round(wh, t1);
		r2.setStartTime(t1);
		r2.setEndTime(t2);
		List<Round> l1 = new ArrayList<Round>();
		l1.add(r1);
		l1.add(r2);
		
		Deliverer del1 = new Deliverer((long)0);
		Deliverer del2 = new Deliverer((long)1);
		Deliverer del3 = new Deliverer((long)2);
		
		Map<Long, Deliverer> ldel = new HashMap<Long, Deliverer>();
		ldel.put(del1.getId(), del1);
		ldel.put(del2.getId(), del2);
		ldel.put(del3.getId(), del3);
		MapManagement.getInstance().setListDeliverer(ldel);
		
		MapManagement.getInstance().assignRounds(l1);
		
		/*
		 * Test: find best deliverer with 2 different deliverers with different round Endtime
		 */
		Deliverer d3 = cs.findBestDeliverer(MapManagement.getInstance().getListDeliverer());
		assertEquals(d3,del1);
	}

	@Test
	public void testCalculateRoundForOneNode() {
		fail("Not yet implemented");
	}

}