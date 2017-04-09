package cn.cherish.mboot.common.solr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cherish on 2017/1/6.
 */
@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void test() {

        this.repository.deleteAll();

        List<String> category = new ArrayList<>();
        category.add("testCategory");
        Point point = new Point( 1 , 2);
        // insert some products
        this.repository.save(new Product("1", "Nintendo Entertainment System",0.0,category,point,"描述"));
        this.repository.save(new Product("2", "Sega Megadrive",5.0,category,point,"描述2"));
        this.repository.save(new Product("3", "Sony Playstation",6.6,category,point,"描述3"));

        // fetch all
        System.out.println("Products found by findAll():");
        System.out.println("----------------------------");
        for (Product product : this.repository.findAll()) {
            System.out.println(product);
        }
        System.out.println();

        // fetch a single product
        System.out.println("Products found with findByNameStartingWith('So'):");
        System.out.println("--------------------------------");
        for (Product product : this.repository.findByNameStartingWith("So")) {
            System.out.println(product);
        }
        System.out.println();
    }




}
