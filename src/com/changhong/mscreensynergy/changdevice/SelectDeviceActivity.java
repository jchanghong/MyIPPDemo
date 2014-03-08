package com.changhong.mscreensynergy.changdevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.changhong.mscreensynergy.R;
import com.changhong.mscreensynergy.changdevice.LazyScrollView.OnScrollListener;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SelectDeviceActivity extends Activity {

	private LazyScrollView waterfall_scroll;
	private LinearLayout waterfall_container;
	private ArrayList<LinearLayout> waterfall_items;
	private Display display;
	private AssetManager assetManager;
	private List<String> image_filenames;
	private final String image_path = "images";

	private int itemWidth;

	private int column_count = 1;// ��ʾ����
	private int page_count = 3;// ÿ�μ���15��ͼƬ
	private int current_page = 0;

	public static Bitmap myBitmap = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_device);

		display = this.getWindowManager().getDefaultDisplay();
		itemWidth = ( display.getWidth() / 3)* 2;// �����Ļ��С����ÿ�д�С
		assetManager = this.getAssets();
		InitLayout();
		scrollToInitPositon();
	}

	private void scrollToInitPositon() {
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				waterfall_scroll.scrollTo(0, display.getHeight()/2 + 50);
				waterfall_scroll.smoothScrollTo(0, display.getHeight()/2 + 50);
			}
			
		}, 200);//
	}
	private void InitLayout() {
		waterfall_scroll = (LazyScrollView) findViewById(R.id.waterfall_scroll);
		
		waterfall_scroll.getView();
		waterfall_scroll.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onTop() {
				Log.d("LazyScroll", "Scroll to top");
			}

			@Override
			public void onScroll() {
				Log.d("LazyScroll", "Scroll");
				System.out.println("LazyScroll---Scroll");
			}

			@Override
			public void onBottom() {
				AddItemToContainer(++current_page, page_count);
			}
		});

		waterfall_container = (LinearLayout) this
				.findViewById(R.id.waterfall_container);
		waterfall_items = new ArrayList<LinearLayout>();

		for (int i = 0; i < column_count; i++) {
			LinearLayout itemLayout = new LinearLayout(this);
			LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(
					itemWidth, LayoutParams.WRAP_CONTENT);
	
			itemLayout.setPadding(2, 2, 2, 2);
			itemLayout.setOrientation(LinearLayout.VERTICAL);
			itemLayout.setGravity(Gravity.CENTER_HORIZONTAL);

			itemLayout.setLayoutParams(itemParam);
			waterfall_items.add(itemLayout);
			waterfall_container.addView(itemLayout);
		}

		// ��������ͼƬ·��
		try {
			image_filenames = Arrays.asList(assetManager.list(image_path));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ��һ�μ���
		AddItemToContainer(current_page, page_count);
	}

	private void AddItemToContainer(int pageindex, int pagecount) {
		
		int j = 0;
		int imagecount = 3;
		
		for (int i = pageindex * pagecount; i < pagecount * (pageindex + 1)
				&& i < imagecount; i++) {
			j = j >= column_count ? j = 0 : j;
			AddImage(j++);
		}

	}

	private void AddImage(int columnIndex) {
		
		ImageView imageView = (ImageView) LayoutInflater.from(this).inflate(
				R.layout.waterfallitem, null);
		
		imageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("xxxxxxxxxx");
				showToast("selectDevice");
			}
			
		});
		
		waterfall_items.get(columnIndex).addView(imageView);
		
		TaskParam param = new TaskParam();
		param.setAssetManager(assetManager);
		param.setItemWidth(itemWidth);
		
		ImageLoaderTask task = new ImageLoaderTask(imageView);
		task.execute(param);

	}
	
	private void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
