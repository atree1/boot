package org.oos.domain;

import java.util.Date;

import org.json.JSONObject;

import lombok.Data;
@Data
public class KakaoPayInfoDTO {

	private String aid, tid, cid, sid;
	private String partner_order_id, partner_user_id, payment_method_type;
	private AmountVO amount;
	private String item_name, item_code, payload;
	private Integer quantity;
	private Date created_at, approved_at;
}
