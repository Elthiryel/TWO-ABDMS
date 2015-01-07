package pl.edu.agh.two.abdms.agent;

import jade.content.onto.*;
import jade.content.schema.*;

public class DataPreprocessorOntology extends Ontology implements DataPreprocessorVocabulary {

    private static Ontology theInstance = new DataPreprocessorOntology();

    public static Ontology getInstance() {
        return theInstance;
    }

    private DataPreprocessorOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance(), new CFReflectiveIntrospector());

        try {
            add(new PredicateSchema(ON), On.class);
            add(new PredicateSchema(SET_DATA_MODEL), SetDataModel.class);

            PredicateSchema ps = (PredicateSchema) getSchema(ON);
            ps.add(ON_PROPERTY_NAME, (ConceptSchema) getSchema(BasicOntology.AID));

            ps = (PredicateSchema) getSchema(SET_DATA_MODEL);
            ps.add(ON_PROPERTY_NAME, (ConceptSchema) getSchema(BasicOntology.AID));
        } catch (OntologyException oe) {
            oe.printStackTrace();
        }
    }

}
