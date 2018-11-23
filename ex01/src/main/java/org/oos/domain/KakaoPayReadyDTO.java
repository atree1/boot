package org.oos.domain;

import java.util.Date;

import lombok.Data;
@Data
public class KakaoPayReadyDTO {
	private String tid, tms_result, next_redirect_pc_url;
	private Date created_at;
}
