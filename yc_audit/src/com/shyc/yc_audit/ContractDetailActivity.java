package com.shyc.yc_audit;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.shyc.yc_audit.data.ContractIntroduction;

import sxp.android.framework.ui.BaseActivity;

/**
 * 合同详情
 * 
 * @author xiaoping.shan
 *
 */
public class ContractDetailActivity extends BaseActivity implements
		OnClickListener {
	private ContractIntroduction contract;
	/**
	 * 基本信息
	 */
	private TextView code;
	private TextView company;
	private TextView protocol;
	private TextView signTime;
	private TextView sendTime;
	private TextView comeTime;
	private TextView openName;
	private TextView provideName;

	private TextView contracTermContent;

	private ListView productList;
	private TextView postResult;
	private TextView downContract;
	private TextView checkContract;
	private TextView contractAuditResult;
	private TextView contractAuditOpinion;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.contract_detail_layout);
		contract = (ContractIntroduction) getIntent().getExtras().get(
				"contract");

		/**
		 * 简要信息
		 */
		code = (TextView) findViewById(R.id.contract_code);
		company = (TextView) findViewById(R.id.contract_company);
		protocol = (TextView) findViewById(R.id.contract_pay_protocol);
		signTime = (TextView) findViewById(R.id.contract_signed_time);
		sendTime = (TextView) findViewById(R.id.contract_send_time);
		comeTime = (TextView) findViewById(R.id.contract_come_time);
		openName = (TextView) findViewById(R.id.contract_open_name);
		provideName = (TextView) findViewById(R.id.contract_provide_name);
		/**
		 * 条款
		 */
		contracTermContent = (TextView) findViewById(R.id.contract_term_content);
		/**
		 * 操作
		 */
		productList = (ListView) findViewById(R.id.contract_detail_product_listview);
		postResult = (TextView) findViewById(R.id.contract_post_result);
		downContract = (TextView) findViewById(R.id.contract_down);
		checkContract = (TextView) findViewById(R.id.contract_check);
		contractAuditResult = (TextView) findViewById(R.id.contract_audit_result);
		contractAuditOpinion = (TextView) findViewById(R.id.contract_audit_opinion);

		postResult.setOnClickListener(this);
		downContract.setOnClickListener(this);
		checkContract.setOnClickListener(this);

		// private ListView productList;
		// private TextView postResult;
		// private TextView downContract;
		// private TextView checkContract;
		// private TextView contractAuditResult;
		// private TextView contractAuditOpinion;

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.contract_post_result:

			break;

		case R.id.contract_down:

			break;
		case R.id.contract_check:

			break;
		}
	}

}
