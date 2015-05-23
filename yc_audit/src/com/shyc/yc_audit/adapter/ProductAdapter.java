package com.shyc.yc_audit.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shyc.yc_audit.R;
import com.shyc.yc_audit.data.ContractProducts;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;

public class ProductAdapter extends BaseAdapter<ContractProducts>{

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if(arg1 == null){
			arg1 = ShowUtil.LoadXmlView(getContext(),R.layout.product_item);
			holder = new Holder();
			holder.name = (TextView)arg1.findViewById(R.id.product_item_name);
			holder.unit = (TextView)arg1.findViewById(R.id.product_item_unit);
			holder.totalNumber = (TextView)arg1.findViewById(R.id.product_item_all_number);
			holder.totalPrice = (TextView)arg1.findViewById(R.id.product_item_all_price);
			arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		ContractProducts product = getData(arg0);
		holder.name.setText(product.getProductname());
		holder.unit.setText("("+product.getUnitprice()+"元/"+product.getUnit()+")");
		holder.totalNumber.setText("总数量:"+product.getTotal());
		float totalPrice = Float.valueOf(product.getTotal())*Float.valueOf(product.getUnitprice());
		holder.totalPrice.setText("总价格:"+String.valueOf(totalPrice));
	
		return arg1;
	}
class Holder{
	TextView name;
	TextView unit;
	TextView totalNumber;
	TextView totalPrice;
	
}
}
