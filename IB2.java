package weka.classifiers.lazy;

import weka.core.Instance;
import weka.core.Instances;
import javax.swing.JOptionPane;
public class IB2 extends IB1{
	
	@Override
	public void buildClassifier(Instances instances) throws Exception {
		
		Instances trainingSet = new Instances(instances, 0, 1);
		super.buildClassifier(trainingSet);
		int count=1;
		Instance current = null;
		for(int i=1; i<instances.numInstances(); ++i){
			current = instances.instance(i);
	    	if (classifyInstance(current) != current.classValue()){
	    		updateClassifier(current);
	    		++count;
	    	}
		}
	    //JOptionPane.showMessageDialog(null, "Number of Instances in set: " + count);
	}
	
	@Override
	public String toString() {

	    return ("IB2 classifier");
	  }
}