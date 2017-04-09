package cn.cherish.mboot.common.solr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.geo.Point;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

/**
 * Created by Cherish on 2017/1/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(solrCoreName = "collection1")
public class Product implements java.io.Serializable {

    @Id
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private Double price;

    @Field("cat")
    private List<String> category;

    @Field("store")
    private Point location;

    @Field
    private String description;


}
