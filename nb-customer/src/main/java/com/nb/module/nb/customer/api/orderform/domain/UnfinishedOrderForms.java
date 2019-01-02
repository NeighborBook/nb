package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

import java.util.List;

@Data
public class UnfinishedOrderForms<T> {

	private Long willBeOverdue = 0L;

	private Long overdue = 0L;

	private List<UnfinishedOrderForm<T>> unfinishedOrderForms;

	public UnfinishedOrderForms() {
	}

	public UnfinishedOrderForms(Long willBeOverdue, Long overdue, List<UnfinishedOrderForm<T>> unfinishedOrderForms) {
		this.willBeOverdue = willBeOverdue;
		this.overdue = overdue;
		this.unfinishedOrderForms = unfinishedOrderForms;
	}

	public void cal(Long days) {
		if (null != days) {
			if (-3L <= days && days <= 0L) {
				willBeOverdue++;
			} else if (0L < days) {
				overdue++;
			}
		}
	}
}
