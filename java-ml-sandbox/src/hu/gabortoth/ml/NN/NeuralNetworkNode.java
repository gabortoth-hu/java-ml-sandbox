package hu.gabortoth.ml.NN;



import hu.gabortoth.ml.utils.Functions.*;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author gtoth
 */
public class NeuralNetworkNode {
    private final List<Double> weights;
    private final Func1<Double, Double> activatorFunction;
    private final List<Integer> connections;
    
    public NeuralNetworkNode(int inputNodes, 
            Func1<Integer,Double> weightsDistribution,
            Func1<Double,Double> activationFunction,
            Func1<Integer,Boolean> connectionsDistribution) {
        this.activatorFunction = activationFunction;
        
        this.connections = Stream.iterate(0, i->i+1).limit(inputNodes).filter(v->connectionsDistribution.apply(v)).collect(toList());

        this.weights = Stream.iterate(0, i->i+1).limit(inputNodes).map(i->connectionsDistribution.apply(i) ? weightsDistribution.apply(i):0.0).collect(toList());
        
        // ^a fentiekben azert nem kell az index a map-bol, mert a Stream-ban levo ertekek 0-inputNodes-1-ig fel vannak toltve
                        
    }
    
    public Double passValues(List<Double> values) {
        return this.activatorFunction.apply(
                this.connections.stream().mapToDouble(connection -> values.get(connection) * this.weights.get(connection)).sum()
            );
    }
    
    public List<Double> getWeights() {
        return this.weights;
    }
}
