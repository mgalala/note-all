package com.mgalala.noteall.service;

import android.app.Activity;
import android.os.Bundle;

import com.mgalala.noteall.activity.R;
import com.mgalala.noteall.model.MobileResource;
import com.mgalala.noteall.service.resolver.StorageURIResolver;
import com.mgalala.noteall.service.resolver.URIResolver;
import com.mgalala.noteall.service.resolver.WebURIResolver;
import com.mgalala.noteall.util.IntentTypeEnum;

/**
 * Created by galala on 12/11/13.
 */
public class URIResolverFactory {
    private URIResolver uriResolver;

    public MobileResource getResourceURI(String intentType, Bundle bundle, Activity activity) {
        MobileResource mobileResource = new MobileResource();
        if (intentType.equals(IntentTypeEnum.IMAGE.getType())) {
            uriResolver = new StorageURIResolver();
            mobileResource.setType(activity.getString(R.string.photo_category));
        } else if (intentType.equals(IntentTypeEnum.VIDEO.getType())) {
            uriResolver = new StorageURIResolver();
            mobileResource.setType(activity.getString(R.string.video_category));
        } else if (intentType.startsWith(IntentTypeEnum.DOCUMENT.getType())) {
            uriResolver = new StorageURIResolver();
            mobileResource.setType(activity.getString(R.string.document_category));
        } else if (intentType.equals(IntentTypeEnum.WEB.getType())) {
            uriResolver = new WebURIResolver();
            mobileResource.setType(activity.getString(R.string.web_category));
        } else {
            return null;
        }
        mobileResource.setUri(uriResolver.resolveURI(bundle, activity));
        return mobileResource;
    }


}
