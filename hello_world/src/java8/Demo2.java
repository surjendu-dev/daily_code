package java8;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo2 {
    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1,5,10,9,15,25,30,25,10);
        List<Integer> squareEven = l.stream().map(x->x%2 == 1?x*x:x).collect(Collectors.toList());
        System.out.println(squareEven);
        var sq=l.stream().collect(Collectors.mapping(x->x%2==1?x*x:x,Collectors.toList()));
        System.out.println(sq);
        sq=l.stream().collect(Collectors.collectingAndThen(Collectors.toList()
        ,a->a.stream().map(b->b%2==1?b*b:b).collect(Collectors.toList())));
        System.out.println(sq);
        sq=l.stream().collect(Collectors.groupingBy(a->a%2, LinkedHashMap::new,Collectors.toList()))
                .entrySet().stream().map(c->{
                    if((c.getKey())%2==1){
                      return c.getValue().stream().map(z->z*z).collect(Collectors.toList());
                    }
                    return c.getValue();
                }).flatMap(s->s.stream()).collect(Collectors.toList());
        System.out.println(sq);

    }
}
