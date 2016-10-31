/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gabortoth.ml.GA;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gtoth
 */
public class Helper {
    
    public static List<Double> encodeNNGenotype(List<List<List<Double>>> weights) {
        List<Double> flattenWeigts = new ArrayList<>();
        
        weights.forEach((List<List<Double>>ll)->ll.forEach((List<Double> l)->l.forEach(flattenWeigts::add)));
        
        return flattenWeigts;
    }
}
