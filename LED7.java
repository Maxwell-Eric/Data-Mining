package weka.datagenerators.classifiers.classification;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.FastVector;
import java.util.Random;

public class LED7 extends LED24{

   @Override
   public Instances defineDataFormat() throws Exception {
    FastVector      atts;
    FastVector      attValues;
    int             i;
    int             n;

    m_Random = new Random(getSeed());

    // number of examples is the same as given per option
    setNumExamplesAct(getNumExamples());

    // set up attributes
    atts = new FastVector();
    
    
    //**************************************
    for (n = 1; n <= 7; n++) {
      attValues = new FastVector();
      for (i = 0; i < 2; i++)
        attValues.addElement("" + i);
      atts.addElement(new Attribute("att" + n, attValues));
    }
    //***************************************
    
    attValues = new FastVector();
    for (i = 0; i < 10; i++)
      attValues.addElement("" + i);
    atts.addElement(new Attribute("class", attValues));
    
    // dataset
    m_DatasetFormat = new Instances(getRelationNameToUse(), atts, 0);
    
    return m_DatasetFormat;
  }
  
  @Override
  public Instance generateExample() throws Exception {
    Instance      result;
    double[]      atts;
    int           i;
    int           selected;
    Random        random;

    result = null;
    random = getRandom();

    if (m_DatasetFormat == null)
      throw new Exception("Dataset format not defined.");

    atts     = new double[m_DatasetFormat.numAttributes()];
    selected = random.nextInt(10);
    for (i = 0; i < 7; i++) {
      if ((1 + (random.nextInt(100))) <= getNoisePercent())
        atts[i] = m_originalInstances[selected][i] == 0 ? 1 : 0;
      else
        atts[i] = m_originalInstances[selected][i];
    }
    
    atts[atts.length - 1] = selected;

    // create instance
    result  = new Instance(1.0, atts);
    result.setDataset(m_DatasetFormat);

    return result;
  }
  
  public static void main(String[] args) {
    runDataGenerator(new LED7(), args);
  }
  
}