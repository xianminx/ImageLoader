/**
 * Copyright 2012 Novoda Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.novoda.imageloader.core.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.novoda.imageloader.core.loader.util.BitmapDisplayer;
import com.novoda.imageloader.core.loader.util.LoaderTask;

public class ImageWrapper {

    private static final String URL_ERROR = "_url_error";
    private String url;
    private String previewUrl;
    private int width;
    private int height;
    private int previewWidth;
    private int previewHeight;
    private int loadingResourceId;
    private int notFoundResourceId;
    private boolean isUseCacheOnly;
    private ImageView imageView;
    private boolean saveThumbnail;

    private Animation animation;

    public ImageWrapper(ImageView imageView) {
        initWrapper(imageView);
    }

    private void initWrapper(ImageView imageView){
        this.imageView = imageView;
        ImageTag tag = (ImageTag) imageView.getTag();
        if (tag == null) {
            return;
        }
        this.url = tag.getUrl();
        this.loadingResourceId = tag.getLoadingResourceId();
        this.notFoundResourceId = tag.getNotFoundResourceId();
        this.isUseCacheOnly = tag.isUseOnlyCache();
        this.height = tag.getHeight();
        this.width = tag.getWidth();
        this.previewHeight = tag.getPreviewHeight();
        this.previewWidth = tag.getPreviewWidth();
        this.saveThumbnail = tag.isSaveThumbnail();
        if (notFoundResourceId == 0) {
            this.notFoundResourceId = tag.getLoadingResourceId();
        }
        this.previewUrl = tag.getPreviewUrl();
        this.animation = tag.getAnimation();
    }

    public String getCurrentUrl() {
        ImageTag tag = (ImageTag) imageView.getTag();

        if (tag.getUrl() != null) {
            return tag.getUrl();
        } else {
            return URL_ERROR;
        }
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void runOnUiThread(BitmapDisplayer displayer) {
        Activity a = (Activity) imageView.getContext();
        a.runOnUiThread(displayer);
    }

    public Context getContext() {
        return (Activity) imageView.getContext();
    }

    public void setBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        imageView.startAnimation(animation);
    }

    public boolean isCorrectUrl(String url) {
        return url.equals(getUrl());
    }

    public int getLoadingResourceId() {
        return loadingResourceId;
    }

    public int getNotFoundResourceId() {
        return notFoundResourceId;
    }

    public boolean isUrlChanged() {
        return !getUrl().equals(getCurrentUrl());
    }

    public boolean isUseCacheOnly() {
        return isUseCacheOnly;
    }

    public boolean isSaveThumbnail() {
        return saveThumbnail;
    }

    public void setSaveThumbnail(boolean saveThumbnail) {
        this.saveThumbnail = saveThumbnail;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public LoaderTask getLoaderTask() {
        ImageTag tag = (ImageTag)imageView.getTag();
        return tag.getLoaderTask();
    }

    public void setLoaderTask(LoaderTask task) {
        ImageTag tag = (ImageTag)imageView.getTag();
        tag.setLoaderTask(task);
    }

}
