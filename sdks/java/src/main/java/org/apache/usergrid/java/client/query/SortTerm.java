package org.apache.usergrid.java.client.query;

/**
 * Created by ApigeeCorporation on 7/15/15.
 */
public class SortTerm {
  public String term;
  public String order;

  public SortTerm(String term, String order) {
    this.term = term;
    this.order = order;
  }
}
