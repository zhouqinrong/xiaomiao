package com.example.utils;

import java.io.File;
import java.io.IOException;

import com.example.test.R;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

public class ImageLoadUtils {
	private static ImageLoadUtils instance = null;
	public Context mContext = null;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	public static ImageLoadUtils getInstance(Context context) {
		if (instance == null) {
			instance = new ImageLoadUtils(context.getApplicationContext());
		}
		return instance;
	}

	private ImageLoadUtils(Context context) {
		mContext = context;
		ImageLoaderConfiguration config = null;
		try {
			config = new ImageLoaderConfiguration.Builder(mContext).threadPriority(Thread.NORM_PRIORITY)
					.denyCacheImageMultipleSizesInMemory()
					.memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 * 1024)).memoryCacheSize(5 * 1024 * 1024)
					.memoryCacheSizePercentage(50)
					.tasksProcessingOrder(QueueProcessingType.FIFO)
					.diskCache(new LruDiskCache(getDiskCacheDir(mContext, "imagecache"), null, new Md5FileNameGenerator(), 10 * 1024 * 1024, 0))
					.threadPoolSize(5).writeDebugLogs() 
					.build();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (config != null) {
			// Initialize ImageLoader with configuration.
			ImageLoader.getInstance().init(config);
		}

		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		if (config != null) {
			ImageLoader.getInstance().init(config);
		}
		options = new DisplayImageOptions.Builder()  
                .showImageOnLoading(R.drawable.ic_launcher)  
                .showImageOnFail(R.drawable.ic_launcher)  
                .cacheInMemory(true)  
                .cacheOnDisk(true)  
                .bitmapConfig(Bitmap.Config.RGB_565)  
                .build();
		
	}
	public File getDiskCacheDir(Context context, String uniqueName) {  
	    String cachePath;  
	    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())  
	            || !Environment.isExternalStorageRemovable()) {  
	        cachePath = context.getExternalCacheDir().getPath();  
	    } else {  
	        cachePath = context.getCacheDir().getPath();  
	    }  
	    return new File(cachePath + File.separator + uniqueName);  
	}  
	
	public void loadImage(String url, ImageView imageView){
		
		ImageLoader.getInstance().displayImage(url, imageView, options);
		
	}
	

}
