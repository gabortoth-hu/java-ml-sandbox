/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gabortoth.ml.GA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gtoth
 */
public class HelperTest {
    
    public HelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encodeNNGenotype method, of class Helper.
     */
    @Test
    public void testEncodeNNGenotype() {
        System.out.println("encodeNNGenotype");
        Double counter = 1.0;
               
        List<List<List<Double>>> weights = new ArrayList<List<List<Double>>>();
        
        for(int i=0; i < 2; i++) {
            weights.add( new ArrayList<List<Double>>());
            for(int j=0; j < 2; j++) {
                List<Double> list = new ArrayList<Double>();
                for(int k=0; k < 2; k++)
                {
                    list.add(counter);
                    counter+=0.5;
                }
                weights.get(i).add(list);
            }
        }
        
        List<Double> expResult = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5);
        List<Double> result = Helper.encodeNNGenotype(weights);
        assertEquals(expResult, result);

    }
    
}
