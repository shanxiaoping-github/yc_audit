package com.shyc.yc_audit.ui;

import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.StringUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shyc.yc_audit.R;
import com.shyc.yc_audit.adapter.ProductAdapter;
import com.shyc.yc_audit.data.ContractDetail;
import com.shyc.yc_audit.data.ContractIntroduction;
import com.shyc.yc_audit.data.UserInfo;
import com.shyc.yc_audit.http.HttpAdress;
import com.shyc.yc_audit.http.HttpContractDetailClient;
import com.shyc.yc_audit.http.HttpContractSubmitResultClient;
import com.shyc.yc_audit.util.ShowUtil;

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

	private ListView productListView;

	private TextView yesTxt;
	private TextView noTxt;

	private CheckBox yesBox;
	private CheckBox noBox;

	private EditText auditOpinion;
	
	
	private ProductAdapter productAdapter;

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
		productListView = (ListView) findViewById(R.id.contract_detail_product_listview);
		productAdapter = new ProductAdapter();
		productAdapter.setContext(this);
		productListView.setAdapter(productAdapter);
		
		
		
		
		auditOpinion = (EditText) findViewById(R.id.contract_detail_audit_opinion);

		yesBox = (CheckBox) findViewById(R.id.contract_detail_yes);
		yesBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					noBox.setChecked(false);
					setSelect(0);
				}

			}
		});
		noBox = (CheckBox) findViewById(R.id.contract_detail_no);
		noBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					yesBox.setChecked(false);
					setSelect(1);
				}

			}
		});

		yesTxt = (TextView) findViewById(R.id.contract_detail_yes_txt);
		noTxt = (TextView) findViewById(R.id.contract_detail_no_txt);

		findViewById(R.id.contract_detail_back).setOnClickListener(this);
		findViewById(R.id.contract_detail_submit).setOnClickListener(this);

		yesBox.setChecked(true);
		
		getDetail();

	}

	/**
	 * 获取合同详情
	 */
	private void getDetail() {
		HttpContractDetailClient client = new HttpContractDetailClient();
		client.cleanResponse();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub

				HttpContractDetailClient client = (HttpContractDetailClient) asynHttpClient;
				ContractDetail contractDetail = client.getContractDetail();
				setContractDetail(contractDetail);
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		client.setPramas(new Object[] { HttpAdress.CONTRACT_RESULT_INFO_ACTION,
				contract.getSerialNumber(), "1"

		});
		client.subRequestGet(HttpAdress.CONTRACT_RESULT_INFO_URL);

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.contract_detail_submit:
			submit();
			break;
		case R.id.contract_detail_back:
			finishBase();
			break;
		}
	}

	private void submit() {
		HttpContractSubmitResultClient client = new HttpContractSubmitResultClient();
		client.cleanResponse();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				showShortToast("审核失败");
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				HttpContractSubmitResultClient client = (HttpContractSubmitResultClient)asynHttpClient;
				if(client.getFlag().equals("1")){
					showShortToast("审核成功");
					finishBase();
				}else{
					showShortToast("审核失败");
				}
				return false;
			}

			public boolean onEmpty() {
				
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				showShortToast("审核失败");
				return false;
			}
		});
		//
		// "contractId",
		// "lv",
		// "userName",
		// "status",
		// "opinion"
		String opinionStr  = auditOpinion.getText().toString();
		UserInfo userInfo = (UserInfo) SXPApplication.getInstance()
				.getSXPRuntimeContext().getData(UserInfo.class.getName());
		client.setPramas(new Object[] { HttpAdress.CONTRACT_AUDIT_ACTION,
				contract.getSerialNumber(), "1", userInfo.getUserName(),
				yesBox.isChecked() ? "1" : "2",
				StringUtil.isEmpty(opinionStr)?"":opinionStr

		});
		ShowUtil.openHttpDialog("审核提交中...");
		client.subRequestGet(HttpAdress.CONTRACT_AUDIT_URL);

	}

	/**
	 * 设置详情
	 */
	private void setContractDetail(ContractDetail contractDetail) {
		
		
		
		code.setText("合同编号: "
				+ contractDetail.getContractInfo().getContractId());
		company.setText("公司: " + contractDetail.getCrateContractOrg());
		protocol.setText("收付款协议: " + contractDetail.getTermName());
		signTime.setText("签订日期: " + contractDetail.getContractInfo().getDate1());
		sendTime.setText("发货日期: "
				+ contractDetail.getContractInfo().getStartDate());
		comeTime.setText("到货日期: "
				+ contractDetail.getContractInfo().getEndDate());
		openName.setText("开票名称: " + contractDetail.getOrgName());
		provideName.setText("供应商名称: "
				+ contractDetail.getContractInfo().getSupplierName());

		contracTermContent.setText(contractDetail.getContractInfo().getTxt7());
	
		productAdapter.setList(contractDetail.getProductList());
		productAdapter.notifyDataSetChanged();
		
	}

	private void setSelect(int index) {
		yesTxt.setTextColor(getResources().getColor(R.color.lightGray));
		noTxt.setTextColor(getResources().getColor(R.color.lightGray));
		switch (index) {
		case 0:
			yesTxt.setTextColor(getResources().getColor(R.color.lightBlue));
			break;

		case 1:
			noTxt.setTextColor(getResources().getColor(R.color.lightBlue));
			break;
		}
	}

}
