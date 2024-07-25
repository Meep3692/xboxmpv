package ca.awoo.xboxmpv;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinator {
    List<List<String>> components;

    public Combinator(){
        components = new LinkedList<>();
    }

    public Combinator optional(String s){
        components.add(List.of(s, ""));
        return this;
    }

    public Combinator or(String... s){
        components.add(List.of(s));
        return this;
    }

    public Combinator fixed(String s){
        components.add(List.of(s));
        return this;
    }

    public List<String> explode(){
        return explode(0);
    }

    private List<String> explode(int from){
        if(from == components.size() - 1){
            return components.get(from);
        }else{
            List<String> strings = new ArrayList<>();
            for(String option : components.get(from)){
                List<String> ends = explode(from + 1);
                for(String end : ends){
                    strings.add(option + end);
                }
            }
            return strings;
        }
    }
}
