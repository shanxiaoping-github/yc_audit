package com.shyc.yc_audit.ui;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;

import com.shyc.yc_audit.R;
import com.shyc.yc_audit.adapter.ContractIntroductionAdapter;
import com.shyc.yc_audit.data.ContractIntroduction;
import com.shyc.yc_audit.data.UserInfo;
import com.shyc.yc_audit.http.HttpAdress;
import com.shyc.yc_audit.http.HttpContractListClient;
import com.shyc.yc_audit.util.ShowUtil;

/**
 * 合同详情
 * 
 * @author xiaoping.shan
 *
 */
public class ContractIntroductionActivity extends BaseActivity {
	/**
	 * 合同listview
	 */
	private ListView contractlistview;
	private ContractIntroductionAdapter contractAdapter;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.contract_introduction_layout);
		contractlistview = (ListView) findViewById(R.id.contract_introduction_listview);

		contractAdapter = new ContractIntroductionAdapter();
		contractAdapter.setContext(this);
	
		contractAdapter.setListener(new AdapterItemListener() {

			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				ContractIntroduction contract = (ContractIntroduction) objects[0];
				Bundle bundle = new Bundle();
				bundle.putSerializable("contract", contract);
				openActivity(ContractDetailActivity.class, bundle);
				return false;
			}
		});
		contractlistview.setAdapter(contractAdapter);
		//getList();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getList();
	}

	private void getList() {
//		ArrayList<ContractIntroduction> list = new ArrayList<ContractIntroduction>();
//		for (int i = 0; i < 5; i++) {
//			ContractIntroduction contract = new ContractIntroduction();
//			contract.setSerialNumber("wdadda");
//			contract.setSupplierName("友邦");
//			contract.setReviewOfPeople("李晓波");
//			contract.setReviewTime("2015-05-17 09:53");
//			contract.setReviewStatus("初审");
//			contract.setAuditStatus("通过");
//			list.add(contract);
//		}
//		return list;
		HttpContractListClient client = new HttpContractListClient();
		client.cleanResponse();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				return false;
			}
			
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				HttpContractListClient client = (HttpContractListClient)asynHttpClient;
				contractAdapter.setList(client.getList());
				contractAdapter.notifyDataSetChanged();
				return false;
			}
			
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				return false;
			}
		});
		UserInfo userinfo = (UserInfo)SXPApplication.getInstance().getSXPRuntimeContext().getData(UserInfo.class.getName());
		client.setPramas(new Object[]{
				HttpAdress.CONTRACT_LIST_ACTION,
				userinfo.getUserName(),
		});
		ShowUtil.openHttpDialog("加载中...");
		client.subRequestPost(HttpAdress.CONTRACT_LIST_URL);

	}
	
	private long lastTime;
	private long howTime = 1000;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(System.currentTimeMillis()-lastTime>1000){
				lastTime = System.currentTimeMillis();
				showShortToast("连按两次退出软件");
			
			}else{
				
				SXPApplication.outApp(true);
				
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
