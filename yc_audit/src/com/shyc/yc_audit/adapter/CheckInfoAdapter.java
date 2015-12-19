package com.shyc.yc_audit.adapter;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shyc.yc_audit.R;
import com.shyc.yc_audit.data.CheckInfo;
/*
 * @ClassName: CheckInfoAdapter
 * @Description: 审核历史适配器
 * @author shanxiaoping
 * @date 2015年12月13日 下午3:16:35
 */
public class CheckInfoAdapter extends BaseAdapter<CheckInfo> {
	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if(arg1 == null){
			arg1 = ShowUtil.LoadXmlView(getContext(),R.layout.checkinfo_item);	
			holder = new Holder();
			holder.name = (TextView)arg1.findViewById(R.id.check_info_name);
			holder.role = (TextView)arg1.findViewById(R.id.check_info_role);
			holder.content = (TextView)arg1.findViewById(R.id.check_info_content);
			arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		CheckInfo checkInfo = getData(arg0);
		holder.name.setText("审核人姓名:"+checkInfo.getRealname());
	    holder.role.setText("审核人身份:"+checkInfo.getRolename());
	    holder.content.setText(checkInfo.getOpinion());
		return arg1;
	}
	class Holder{
		TextView name;
		TextView role;
		TextView content;
	}
	
}
