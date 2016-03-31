package uta.edu.tutorme.beans;

/**
 * Created by ananda on 3/30/16.
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FilterRequest implements Serializable{

    @SerializedName("simple-hints")
    protected List<FilterElementSimple> simpleHints;

    @SerializedName("range-hints")
    protected List<FilterElementRange> rangeHints;

    @SerializedName("list-hints")
    protected List<FilterElementList> listHints;
    protected int page;
    protected int pageSize;

    public List<FilterElementSimple> getSimpleHints() {
        return simpleHints;
    }

    public void setSimpleHints(List<FilterElementSimple> simpleHints) {
        this.simpleHints = simpleHints;
    }


    public List<FilterElementRange> getRangeHints() {
        return rangeHints;
    }

    public void setRangeHints(List<FilterElementRange> rangeHints) {
        this.rangeHints = rangeHints;
    }

    public List<FilterElementList> getListHints() {
        return listHints;
    }

    public void setListHints(List<FilterElementList> listHints) {
        this.listHints = listHints;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "FilterRequest [simpleHints=" + simpleHints + ", rangeHints="
                + rangeHints + ", listHints=" + listHints + ", page=" + page
                + ", pageSize=" + pageSize + "]";
    }

}