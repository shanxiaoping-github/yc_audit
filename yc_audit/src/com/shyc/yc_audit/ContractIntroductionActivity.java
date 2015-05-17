package com.shyc.yc_audit;

import java.util.ArrayList;

import com.shyc.yc_audit.adapter.ContractIntroductionAdapter;
import com.shyc.yc_audit.data.ContractIntroduction;

import android.os.Bundle;
import android.widget.ListView;
import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.ui.BaseActivity;

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
		contractAdapter.setList(getList());
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
	}

	private ArrayList<ContractIntroduction> getList() {
		ArrayList<ContractIntroduction> list = new ArrayList<ContractIntroduction>();
		for (int i = 0; i < 5; i++) {
			ContractIntroduction contract = new ContractIntroduction();
			contract.setSerialNumber("wdadda");
			contract.setSupplierName("友邦");
			contract.setReviewOfPeople("李晓波");
			contract.setReviewTime("2015-05-17 09:53");
			contract.setReviewStatus("初审");
			contract.setAuditStatus("通过");
			list.add(contract);
		}
		return list;

	}

}
