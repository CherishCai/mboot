package cn.cherish.mboot.extra.solr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by Cherish on 2017/1/6.
 */
public interface ProductRepository extends SolrCrudRepository<Product, String> {

    List<Product> findByNameStartingWith(String name);

    Page<Product> findByPrice(Double price, Pageable page);

    Page<Product> findByNameOrDescription(@Boost(2) String name, String description, Pageable page);

    @Highlight(prefix = "<b>", postfix = "</b>")
    HighlightPage<Product> findByNameIn(Collection<String> name, Pageable page);

    @Query(value = "name:?0")
    @Facet(fields = { "cat" }, limit=20)
    FacetPage<Product> findByNameAndFacetOnCategory(String name, Pageable page);


}