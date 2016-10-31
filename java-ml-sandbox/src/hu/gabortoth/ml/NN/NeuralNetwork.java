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
public class NeuralNetwork {
    private final List<NeuralNetworkLayer> layers;
    private final Integer inputs;
    public Integer getInputs() {  return inputs; }
    
    private final List<Func1<Double, Double>> inputMapping;
    
    public NeuralNetwork(Integer inputs,
                        List<Integer> hiddenLayers,
                        Integer outputs,
                        Func3<Integer, Integer, Integer, Double> weightsDistribution,
                        Func2<Integer, Integer, Func1<Double, Double>> activationFunctionDistribution,
                        Func3<Integer, Integer, Integer, Boolean> connectionsDistribution
            ) {
        this.inputs = inputs;
        
        this.inputMapping = Stream.iterate(0, i->i+1).limit(inputs).map(i->activationFunctionDistribution.apply(0,i)).collect(toList());
        
        // concat...
        List<Integer> layerSizes = hiddenLayers.stream().collect(toList());
        layerSizes.add(outputs);
        
        List<Integer> layerNumbers = Stream.iterate(0, i->i+1).limit(layerSizes.size()).collect(toList());
        this.layers = layerNumbers.stream()
                        .map(layerNumber->new NeuralNetworkLayer(
                                layerSizes.get(layerNumber),
                                (layerNumber == 0 ? inputs : layerSizes.get(layerNumber -1)) + 1,
                                (node, input) -> weightsDistribution.apply(layerNumber + 1, node, input),
                                (node) -> activationFunctionDistribution.apply(layerNumber + 1, node),
                                (node, input) -> connectionsDistribution.apply(layerNumber + 1, node, input))
                            ).collect(toList());
        
    }
    
    public List<Double> passValues(List<Double> values) {
        
        final List<Double> fValues = values.stream().collect(toList());
        values = Stream.iterate(0, i->i+1).limit(values.size()).map(i->this.inputMapping.get(i).apply(fValues.get(i))).collect(toList());
        
        for(Integer i = 0; i < this.layers.size(); i++) {
            values.add(1.0);
            values = this.layers.get(i).passValues(values);
        }
        
        return values;
    }
    
    public List<List<List<Double>>> getWeights() {
        return this.layers.stream().map(l->l.getWeights()).collect(toList());
    }
    
}
