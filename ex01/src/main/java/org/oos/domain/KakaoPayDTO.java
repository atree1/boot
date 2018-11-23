package org.oos.domain;

import java.util.Date;

import org.json.JSONObject;

import lombok.Data;
@Data
public class KakaoPayDTO {

	private String cid,tid;
	private String partner_order_id,partner_user_id,item_name,item_code,payment_method_type;
	private Integer quantity,total_amount;
	private JSONObject amount, card_info;
	private Date created_at, approved_at;
}
