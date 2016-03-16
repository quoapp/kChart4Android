package com.wuchuanlong.stockview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.stockview.R;
import com.wedroid.framework.activity.WeDroidFragmentActivity;
import com.wuchuanlong.stockview.chart.ChartTouchEvent;
import com.wuchuanlong.stockview.chart.KChartUtil;
import com.wuchuanlong.stockview.chart.PriceInfo;
import com.wuchuanlong.stockview.chart.SingleStockInfo;
import com.wuchuanlong.stockview.chart.StockBusiness;
import com.wuchuanlong.stockview.chart.StockCache;
import com.wuchuanlong.stockview.chart.Type;
import com.wuchuanlong.stockview.fragment.DayChartFragment;
import com.wuchuanlong.stockview.fragment.MonthChartFragment;
import com.wuchuanlong.stockview.fragment.TimeChartFragment;
import com.wuchuanlong.stockview.fragment.WeekChartFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class NormalStockChartActivity extends WeDroidFragmentActivity implements ChartTouchEvent {
	ViewPager viewPager;
	private String name = "��������";
	private String code = "600875";
	private String market = "SH";
	private TextView nowPriceView;
	private TextView zhanDieEView;
	private TextView zhandieFView;
	private TextView openPriceView;
	private TextView dealCountView;
	private TextView yesPriceView;
	private TextView hslView;
	private TextView highPriceView;
	private TextView neipanView;
	private TextView sylView;
	private TextView lowPriceView;
	private TextView waipanView;
	private TextView zfView;
	private TextView dealMoneyView;
	private TextView shizhiView;
	private TextView ltszView;
	final int kSize = 4;
	private String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.chart_activity_stock_normal_layout);
		name = getIntent().getStringExtra("name");
		code = getIntent().getStringExtra("code");
		market = getIntent().getStringExtra("market");
		name = "��������";
		code = "600875";
		market = "SH";
		type = getIntent().getStringExtra("type");
		StockCache.put(StockCache.CODE, code);
		StockCache.put(StockCache.MARKET, market);
		StockCache.put(StockCache.STOCK_TYPE, type);
		StockCache.put(StockCache.NAME, name);
		initView();
		initData();
	}

	PriceInfo info;

	public void requestSuccess(Object result, int requestToken) {
		if (requestToken == StockBusiness.SINGLE_STOCK) {
			info = (PriceInfo) result;
			setTextView();
		}
	}

	public void initData() {
		// ��ȡͷ������
		Map<String, String> param = new HashMap<String, String>();
		param.put("stock_list", market + ":" + code);
		new StockBusiness(StockBusiness.SINGLE_STOCK, this, param).execute();

		viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		viewPager.setOffscreenPageLimit(kSize);
		viewPager.setCurrentItem(0);

	}

	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		nowPriceView = (TextView) findViewById(R.id.tv_now);// �ּ�
		zhanDieEView = (TextView) findViewById(R.id.tv_zde);// �ǵ�
		zhandieFView = (TextView) findViewById(R.id.tv_zdf);// �ǵ���
		openPriceView = (TextView) findViewById(R.id.tv_open);// ����
		dealCountView = (TextView) findViewById(R.id.tv_deal);// �ɽ���
		yesPriceView = (TextView) findViewById(R.id.tv_yes);// ����
		hslView = (TextView) findViewById(R.id.tv_hsv);// ������
		highPriceView = (TextView) findViewById(R.id.tv_high);// ��߼�
		neipanView = (TextView) findViewById(R.id.tv_neipan);// ����
		sylView = (TextView) findViewById(R.id.tv_syl);// ������
		lowPriceView = (TextView) findViewById(R.id.tv_low);// ���
		waipanView = (TextView) findViewById(R.id.tv_wp);// ����
		zfView = (TextView) findViewById(R.id.tv_zf);// ���
		dealMoneyView = (TextView) findViewById(R.id.tv_deal_money);// �ɽ���
		shizhiView = (TextView) findViewById(R.id.tv_shizhi);// ��ֵ
		ltszView = (TextView) findViewById(R.id.tv_ltsz);// ��ͨ��ֵ
	}

	public void setTextView() {
		if (info == null) {
			info = StockCache.get(StockCache.STOCK_INFO, PriceInfo.class);
		}
		if (info != null) {
			nowPriceView.setText("" + info.getNow());
			zhanDieEView.setText("" + info.getUp());
			zhandieFView.setText("" + info.getUppercent());
			openPriceView.setText("" + info.getOpen());
			dealCountView.setText("" + info.getVolume());
			yesPriceView.setText("" + info.getYesterday());
			hslView.setText("" + info.getHsl());
			highPriceView.setText("" + info.getHigh());
			neipanView.setText("" + info.getInside());
			sylView.setText("" + info.getPrg());
			lowPriceView.setText("" + info.getLow());
			waipanView.setText("" + info.getOutside());
			zfView.setText("" + info.getFlux());
			dealMoneyView.setText("" + info.getAmount());
			shizhiView.setText("" + info.getZsz());
			ltszView.setText("" + info.getLtsz());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		StockCache.removeAll();
	}

	/**
	 * ��ָ����
	 */
	public void updateRelativeView(SingleStockInfo info, Type chartType) {
	}

	public void updateRelativeView(PriceInfo info, Type chartType) {
	}

	@Override
	public void ifParentIterceptorEvent(boolean interceptor) {
		viewPager.requestDisallowInterceptTouchEvent(interceptor);
	}

	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.tv_time) {
			viewPager.setCurrentItem(0);
		} else if (id == R.id.tv_day) {
			viewPager.setCurrentItem(1);
		} else if (id == R.id.tv_week) {
			viewPager.setCurrentItem(2);
		} else if (id == R.id.tv_month) {
			viewPager.setCurrentItem(3);
		} else if (id == R.id.tv_finish) {
			finish();
		}
	}

	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			updateNavigater(arg0);
		}

		@Override
		public void onPageSelected(int arg0) {

		}

	}

	class MyViewPagerAdapter extends FragmentPagerAdapter {

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		final int size = 4;

		List<Fragment> fragmentList = new ArrayList<>(size);

		@Override
		public Fragment getItem(int arg0) {
			return getFragmentByPosition(arg0);
		}

		@Override
		public int getCount() {
			return size;
		}

		Fragment timeFragment;
		Fragment dayFragment;
		Fragment weekFragment;
		Fragment monthFragment;

		private Fragment getFragmentByPosition(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				if (timeFragment == null) {
					timeFragment = new TimeChartFragment();
					fragmentList.add(0, timeFragment);
				}
				fragment = fragmentList.get(0);
				break;
			case 1:
				if (dayFragment == null) {
					dayFragment = new DayChartFragment();
					fragmentList.add(1, dayFragment);
				}
				fragment = fragmentList.get(1);
				break;
			case 2:
				if (weekFragment == null) {
					weekFragment = new WeekChartFragment();
					fragmentList.add(2, weekFragment);
				}
				fragment = fragmentList.get(2);
				break;
			case 3:
				if (monthFragment == null) {
					monthFragment = new MonthChartFragment();
					fragmentList.add(3, monthFragment);
				}
				fragment = fragmentList.get(3);
				break;
			}
			return fragment;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		}
	}

	public void updateNavigater(int position) {
		int normalRes = R.drawable.stock_k_time_text_normal;
		int pressRes = R.drawable.stock_k_time_text_press;
		findViewById(R.id.tv_time).setBackgroundResource(position == 0 ? pressRes : normalRes);
		findViewById(R.id.tv_day).setBackgroundResource(position == 1 ? pressRes : normalRes);
		findViewById(R.id.tv_week).setBackgroundResource(position == 2 ? pressRes : normalRes);
		findViewById(R.id.tv_month).setBackgroundResource(position == 3 ? pressRes : normalRes);
	}

	@Override
	public void clickedTwo() {
		StockCache.put(StockCache.CODE, code);
		StockCache.put(StockCache.MARKET, market);
		StockCache.put(StockCache.STOCK_TYPE, "15");
		StockCache.put(StockCache.NAME, name);
		Intent intent = new Intent(getApplicationContext(), BigStockChartActivity.class);
		startActivity(intent );
	}

}