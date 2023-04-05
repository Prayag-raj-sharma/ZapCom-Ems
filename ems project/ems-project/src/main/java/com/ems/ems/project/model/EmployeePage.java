/*package com.ems.ems.project.model;

import org.springframework.data.domain.Sort;

public class EmployeePage {

	private int pageSize;
	private int pageNumber;
	private String sortBy;
	
	public EmployeePage() {
		
	}
	
	public EmployeePage(int pageSize, int pageNumber, String sortBy) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.sortBy = sortBy;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
    public Sort.Direction getSortDirection() {
        
        return Sort.Direction.ASC;
    }
	
	
	
}*/





package com.ems.ems.project.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

public class EmployeePage {

    @QueryParam("pageNo")
    @DefaultValue("1")
    private Integer pageNo;

    @QueryParam("pageSize")
    @DefaultValue("2")
    private Integer pageSize;

    @QueryParam("sortBy")
    @DefaultValue("id")
    private String sortBy;

    public EmployeePage() {
    	
    }

    public EmployeePage(Integer pageNo, Integer pageSize, String sortBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Pageable toPageable() {
        return PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    }

}

