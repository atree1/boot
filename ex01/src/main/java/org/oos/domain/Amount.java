package org.oos.domain;

import lombok.Data;

@Data
public class Amount {
	private Integer total,tax_free,vat;
}
