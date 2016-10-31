/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gabortoth.ml.NN;

import hu.gabortoth.ml.utils.Functions.*;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author gtoth
 */
public class NeuralNetworkLayer {
    private List<NeuralNetworkNode> nodes;
    public int getSize() { return nodes.size(); }
    
    public NeuralNetworkLayer(int layerSize, int inputNodes,
            Func2<Integer,Integer,Double> weightsDistribution,
            Func1<Integer,Func1<Double, Double>> activationFunctionsDistribution,
            Func2<Integer,Integer,Boolean> connectionsDistribution
    ) {
        this.nodes = Stream.iterate(0, i->i+1).limit(layerSize)
                .map(i->new NeuralNetworkNode(
                    inputNodes,
                    (input)->weightsDistribution.apply(i,input),
                    activationFunctionsDistribution.apply(i),
                    (input)->connectionsDistribution.apply(i,input)
                )).collect(toList());
    }
    
    public  List<Double> passValues(List<Double> values) {
        return this.nodes.stream().map(node->node.passValues(values)).collect(toList());
    }
    
    public List<List<Double>> getWeights() {
        return this.nodes.stream().map(node->node.getWeights()).collect(toList());
    }
}
