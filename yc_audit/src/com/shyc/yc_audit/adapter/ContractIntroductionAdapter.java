package com.shyc.yc_audit.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shyc.yc_audit.R;
import com.shyc.yc_audit.data.ContractIntroduction;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;

public class ContractIntroductionAdapter extends
		BaseAdapter<ContractIntroduction> {

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if (arg1 == null) {
			arg1 = ShowUtil.LoadXmlView(getContext(),
					R.layout.contract_introduction_item);
			holder = new Holder();
			
			holder.serialNumber =(TextView) arg1.findViewById(R.id.contract_introduction_serialnumber);
		
			holder.supplierName =(TextView) arg1.findViewById(R.id.contract_introduction_suppliername);
			
			holder.reviewOfPeople =(TextView) arg1.findViewById(R.id.contract_introduction_reviewofpeople);
			
			holder.reviewTime =(TextView) arg1.findViewById(R.id.contract_introduction_review_time);
			
			holder.reviewStatus =(TextView) arg1.findViewById(R.id.contract_introduction_review_status);
			
			holder.auditStatus =(TextView) arg1.findViewById(R.id.contract_introduction_audit_status);
			arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		
		final ContractIntroduction contract = getData(arg0);
		
		
		
		holder.serialNumber.setText("合同编号:"+contract.getSerialNumber());
	
		holder.supplierName.setText("供应商名称:"+contract.getSupplierName());
	
		holder. reviewOfPeople.setText("送审人:"+contract.getReviewOfPeople());
		
		//holder.reviewTime.setText("送审时间:"+contract.getReviewTime());
		
		//holder.reviewStatus.setText("送审状态:"+(contract.getReviewStatus().equals("1")?"初次送审":contract.getReviewStatus()+"次送审"));
		
		holder.auditStatus.setText("审核状态:"+(contract.getAuditStatus().equals("0")?"待审核":contract.getAuditStatus().equals("1")?"已通过":"不通过 "));

		arg1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onItem(new Object[]{
						contract
				});
			}
		});
		return arg1;
	}

	class Holder {
		/**
		 * 编号
		 */
		TextView serialNumber;
		/**
		 * 供货商名称
		 */
		TextView supplierName;
		/**
		 * 送审人
		 */
		TextView reviewOfPeople;
		/**
		 * 送审时间
		 */
		TextView reviewTime;
		/**
		 * 初审状态
		 */
		TextView reviewStatus;
		/**
		 * 审核状态
		 */
		TextView auditStatus;

	}
}
