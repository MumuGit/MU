package com.mu.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 加载Image工具类
 * Created by SLX on 2017/6/13 0013.
 */

public class ImageUtils {

    public static final int ICON_DEFAULT = R.mipmap.ic_launcher;
    public static final int ICON_ERROR = R.mipmap.ic_launcher;

    //------------对外部暴露的方法---------

    /**
     * 从网络默认加载图片（包括内存缓存、硬盘缓存）
     *
     * @param targeImage
     */
    public static void displayImageFromUrl(Context context, String url, ImageView targeImage) {
        _displayImage(context, url, targeImage, false, ICON_ERROR, ICON_DEFAULT);
    }
    public static void displayImageFromFile(Context context, File file, ImageView targeImage) {
        _displayImage(context, file, targeImage, false, ICON_ERROR, ICON_DEFAULT);
    }



    /**
     * 从网络默认加载图片并且不使用内存缓存
     *
     * @param targeImage
     */
    public static void displayImageFromUrlWithNoMemoryCache(Context context, String url, ImageView targeImage) {
        _displayImage(context, url, targeImage, true, ICON_ERROR, ICON_DEFAULT);
    }

    /**
     * 展示GIF
     *
     * @param targeImage
     */
    public static void displayGif(Context context, String url, ImageView targeImage) {
        _displayGif(context, url, targeImage, false, ICON_ERROR, ICON_DEFAULT);
    }

    /**
     * 从网络默认加载图片并指定大小
     *
     * @param targeImage
     */
    public static void displayImageFromUrlWithSize(Context context, String url, ImageView targeImage, int width, int height) {
        _displayImageWithSize(context, url, targeImage, false, width, height, ICON_ERROR, ICON_DEFAULT);
    }

    public static void displayCircleImage(Context context, String url, final ImageView iv) {
        RequestOptions options = new RequestOptions()
                .placeholder(ICON_DEFAULT)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        iv.setImageDrawable(resource);
                    }
                });

    }


    //-------内部私有方法------------
    private static void _displayImage(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId, int loadingImgId) {
        RequestOptions options = new RequestOptions()
                .error(errorImgId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(isSkipMemoryCache)//是否跳过内存缓存
                .placeholder(loadingImgId);
        Glide.with(cotext)
                .load(new MyGlideUrl(url))
                .apply(options)
                .into(targeImage);
    }
    private static void _displayImage(Context cotext, File file, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId, int loadingImgId) {
        RequestOptions options = new RequestOptions()
                .error(errorImgId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(isSkipMemoryCache)//是否跳过内存缓存
                .placeholder(loadingImgId);
        Glide.with(cotext)
                .load(file)
                .apply(options)
                .into(targeImage);
    }

    private static void _displayImage(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId) {
        RequestOptions options = new RequestOptions()
                .error(errorImgId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(isSkipMemoryCache)//是否跳过内存缓存
                ;
        Glide.with(cotext)
                .load(new MyGlideUrl(url))
                .apply(options)
                .into(targeImage);
    }

    private static void _displayImageWidthCircle(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId, int loadingImgId) {
        RequestOptions options = new RequestOptions()
                .error(errorImgId)
                .transform(new CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(isSkipMemoryCache)//是否跳过内存缓存
                .placeholder(loadingImgId);
        Glide.with(cotext)
                .load(new MyGlideUrl(url))
                .apply(options)
                .into(targeImage);
    }

    //带加载成功监听
    private static void _displayImageWithListener(Context cotext, String url, final ImageView targeImage, boolean isSkipMemoryCache, final OnLoadFinishListener listener) {
        Glide.with(cotext).asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        targeImage.setImageBitmap(resource);
                        listener.finsish(resource);
                    }
                });
    }

    public interface OnLoadFinishListener {
        void finsish(Bitmap bm);
    }

    private static void _displayGif(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int errorImgId, int loadingImgId) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadingImgId)
                .error(errorImgId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(isSkipMemoryCache);//跳过内存缓存
        Glide.with(cotext).asGif()
                .load(url)
                .apply(options)
                .into(targeImage);
    }

    private static void _displayImageWithSize(Context cotext, String url, ImageView targeImage, boolean isSkipMemoryCache, int width, int height, int errorImgId, int loadingImgId) {
        RequestOptions options = new RequestOptions()
                .error(errorImgId)
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(isSkipMemoryCache)//跳过内存缓存
                .placeholder(loadingImgId);
        Glide.with(cotext)
                .load(url)
                .apply(options)
                .into(targeImage);
    }

    public static void displayImageInSelectPhoto(Context cotext, String path, ImageView targeImage, int width, int height) {
        RequestOptions options = new RequestOptions()
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(cotext)
                .load(path)
                .apply(options)
                .into(targeImage);
    }

    //使用BitmapFactory.Options的inSampleSize参数来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }

    public static Bitmap resizeImageInRatio(Bitmap bitmap, int maxSide) {
        int width = bitmap.getWidth();
        int high = bitmap.getHeight();
        if (width > maxSide || high > maxSide) {
            int max = Math.max(width, high);
            int wc = (int) (((float) width) / max * maxSide);
            int hc = (int) (((float) high) / max * maxSide);

            bitmap = ImageUtils.resizeImage(bitmap, wc, hc);
        }
        return bitmap;
    }

    public static File resizeImageInRatioToFile(File file, int maxSide,
                                                Context context) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设为true，节约内存
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);//返回null
        int height = options.outHeight;//得到源图片height，单位px
        int width = options.outWidth;//得到源图片的width，单位px
        float scale = 1;
        if (height > maxSide || width > maxSide) {
            //计算图片实际的宽高和目标图片宽高的比率
            final float heightRate = (float) maxSide / (float) height;
            final float widthRate = (float) maxSide / (float) width;
            scale = heightRate < widthRate ? widthRate : heightRate;
        } else {
            return file;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // if you want to rotate the Bitmap
        int degree = parseImageDegree(file.getAbsolutePath());
        matrix.postRotate(degree);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), 0, 0, width,
                height, matrix, true);
        File result = null;
        try {
            result = saveFile(resizedBitmap, file.getName(), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取图片旋转角度
     *
     * @param path 图片路径
     * @return
     */
    private static int parseImageDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static File saveFile(Bitmap bm, String fileName, Context context) throws IOException {
        File dirFile = context.getCacheDir();
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File result = new File(dirFile.getPath() + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(result));
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
        return result;
    }

//    //
//    public void resizeImageToFileAsync(final File file, OnCompressListener listener) {
//        Bitmap bitmap;
//        BitmapFactory.
//                bitmap.
//                mListener = listener;
//        mHandler = getHandler();
//        listener.onCompressStart();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                File target = compressedToFile(file);
//                Message message = mHandler.obtainMessage();
//                message.obj = target;
//                message.sendToTarget();
//            }
//        }).start();
//
//    }

    public static boolean isJPG(String path) {
        File file = new File(path);
        return file.getName().toLowerCase().endsWith("jpg");
    }


    public static byte[] transformBitmapToByte(Bitmap bm) {
        byte[] bytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
        bytes = out.toByteArray();
        if (bytes == null || bytes.length <= 0) {
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static int getZoomScale(String imageFile, int imagwWidth, int imageHeight) {
        int scale = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        while (options.outWidth / scale >= imagwWidth
                || options.outHeight / scale >= imageHeight) {
            scale *= 2;
        }
        return scale;
    }

    public static class MyGlideUrl extends GlideUrl {

        private String mUrl;

        public MyGlideUrl(String url) {
            super(url);
            mUrl = url;
        }

        @Override
        public String getCacheKey() {
            return mUrl.replace(findTokenParam(), "");
        }

        private String findTokenParam() {
            String tokenParam = "";
            int tokenKeyIndex = mUrl.indexOf("?token=") >= 0 ? mUrl.indexOf("?token=") : mUrl.indexOf("&token=");
            if (tokenKeyIndex != -1) {
                int nextAndIndex = mUrl.indexOf("&", tokenKeyIndex + 1);
                if (nextAndIndex != -1) {
                    tokenParam = mUrl.substring(tokenKeyIndex + 1, nextAndIndex + 1);
                } else {
                    tokenParam = mUrl.substring(tokenKeyIndex);
                }
            }
            return tokenParam;
        }

    }


}


