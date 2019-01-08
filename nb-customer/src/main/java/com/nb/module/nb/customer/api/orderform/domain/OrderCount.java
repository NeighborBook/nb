package com.nb.module.nb.customer.api.orderform.domain;

import lombok.Data;

@Data
public class OrderCount {

	private Long count = 0L;

	private Long unconfirmed = 0L;

	private Long starting = 0L;

	private Long willBeOverdue = 0L;

	private Long overdue = 0L;

	public void cal(Long days) {
		if (null != days) {
			if (days < -3L) {
				starting++;
			} else if (-3L <= days && days <= 0L) {
				willBeOverdue++;
			} else if (0L < days) {
				overdue++;
			}
		} else {
			unconfirmed++;
		}
	}

}
