package io.featureflow.client.model;

import io.featureflow.client.FeatureflowUser;

import java.util.List;

/**
 * Created by oliver on 18/11/16.
 */
public class Audience {
    public String id;   //the audience has an id if it has been saved
    public String name;
    public List<Condition> conditions;

    public Audience() {}

    public Audience(String id, String name, List<Condition> conditions) {
        this.id = id;
        this.name = name;
        this.conditions = conditions;
    }
    //check that all conditions match (it is an AND - to do an OR you would effectively use the 'is in' operator)
    public boolean matches(FeatureflowUser user) {
        if(conditions==null||conditions.size()==0)return true;
        for (Condition condition : conditions) {
            if(!condition.matches(user)){
                return false;
            }
        }
        return true;
    }
}
