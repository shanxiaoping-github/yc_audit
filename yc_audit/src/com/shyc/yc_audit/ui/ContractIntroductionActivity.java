package com.shyc.yc_audit.ui;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
public class ContractIntroductionActivity extends BaseActivity implements
		OnClickListener, OnRefreshListener<ListView>, OnLastItemVisibleListener {
	/**
	 * contractlistview 合同listview contractAdapter 合同适配器
	 */
	private ListView contractlistview;
	private ContractIntroductionAdapter contractAdapter;
	/**
	 * 审核 auditStatus 审核状态 ,0待审核，1已审核 empty 是否空数据 waitAudit 等待审核 alreadyAudit
	 * 已审核
	 */
	private int auditStatus;
	private TextView empty;
	private TextView waitAudit;
	private TextView alreadyAudit;
	// 上下拉
	private PullToRefreshListView pullRefresh;
	private int currentPage;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.contract_introduction_layout);
		currentPage = 1;
		pullRefresh = (PullToRefreshListView) findViewById(R.id.contract_introduction_pullrefresh);
		pullRefresh.setOnRefreshListener(this);
		pullRefresh.setOnLastItemVisibleListener(this);
		contractlistview = pullRefresh.getRefreshableView();
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
		pullRefresh.setAdapter(contractAdapter);
		// 审核
		auditStatus = 0;
		empty = (TextView) findViewById(R.id.contract_introduction_empty);
		waitAudit = (TextView) findViewById(R.id.contract_introduction_wait_audit);
		waitAudit.setOnClickListener(this);
		alreadyAudit = (TextView) findViewById(R.id.contract_introduction_already_audit);
		alreadyAudit.setOnClickListener(this);
		getList(auditStatus,currentPage,true,true);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// getList(auditStatus);
	}

	/**
	 * @Title getList
	 * @Description 获取合同列表
	 * @param status
	 *            0 未审核 1已审核
	 * @return void
	 * @throws
	 * @author shanxiaoping
	 * @data 2015年12月19日
	 */
	private void getList(int status, int page, final boolean isFresh,
			final boolean isLoading) {

		HttpContractListClient client = new HttpContractListClient();
		client.cleanResponse();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				pullRefresh.onRefreshComplete();
				ShowUtil.closeHttpDialog();
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				pullRefresh.onRefreshComplete();
				ShowUtil.closeHttpDialog();
				HttpContractListClient client = (HttpContractListClient) asynHttpClient;
				if (isFresh) {
					contractAdapter.setList(client.getList());
					if (isLoading) {
						if (client.getList().size() > 0) {
							empty.setVisibility(View.GONE);
						} else {
							empty.setVisibility(View.VISIBLE);
							empty.setText("暂无合同");
						}
					}
				} else {
					if (client.getList().size() > 0) {
						currentPage++;
						contractAdapter.getList().addAll(client.getList());
					} else {
						showShortToast("没有更多数据");
					}
				}
				contractAdapter.notifyDataSetChanged();
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				pullRefresh.onRefreshComplete();
				ShowUtil.closeHttpDialog();
				return false;
			}
		});
		UserInfo userinfo = (UserInfo) SXPApplication.getInstance()
				.getSXPRuntimeContext().getData(UserInfo.class.getName());
		client.setPramas(new Object[] {
				status == 0 ? HttpAdress.CONTRACT_LIST_ACTION
						: HttpAdress.CONTRACT_ALREADY_LIST_ACTION,
				userinfo.getUserName(),page });
		if (isFresh) {
			if (isLoading) {
				ShowUtil.openHttpDialog("加载中...");
				empty.setVisibility(View.VISIBLE);
				empty.setText("");
			}
		}
		client.subRequestPost(status == 0 ? HttpAdress.CONTRACT_LIST_URL
				: HttpAdress.CONTRACT_ALREADY_LIST_URL);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.contract_introduction_wait_audit:
			selectAuditStatus(0);
			break;
		case R.id.contract_introduction_already_audit:
			selectAuditStatus(1);
			break;
		}
	}

	/**
	 * @Title selectAuditStatus
	 * @Description 选择审核状态
	 * @param auditStatus
	 *            0待审核，1已审核
	 * @return void
	 * @throws
	 * @author shanxiaoping
	 * @data 2015年12月19日
	 */
	private void selectAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
		waitAudit.setBackgroundResource(R.color.baseColor);
		waitAudit.setTextColor(getResources().getColor(R.color.lightGray));
		alreadyAudit.setBackgroundResource(R.color.baseColor);
		alreadyAudit.setTextColor(getResources().getColor(R.color.lightGray));
		switch (auditStatus) {
		case 0:
			waitAudit.setBackgroundResource(R.color.lightBlue);
			waitAudit.setTextColor(getResources().getColor(R.color.white));
			break;
		case 1:
			alreadyAudit.setBackgroundResource(R.color.lightBlue);
			alreadyAudit.setTextColor(getResources().getColor(R.color.white));
			break;
		}
		currentPage = 1;
		getList(auditStatus,currentPage,true,true);
	}

	/**
	 * 退出软件监听
	 */
	private long lastTime;
	private long howTime = 1000;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - lastTime > 1000) {
				lastTime = System.currentTimeMillis();
				showShortToast("连按两次退出软件");

			} else {

				SXPApplication.outApp(true);

			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onLastItemVisible() {
		// TODO Auto-generated method stub
		int page = currentPage + 1;
		getList(auditStatus,page,false,false);
	}

	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		currentPage = 1;
		getList(auditStatus,currentPage,true,false);
	}

}
