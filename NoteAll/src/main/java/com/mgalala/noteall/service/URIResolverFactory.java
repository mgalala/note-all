package com.mgalala.noteall.service;

import android.app.Activity;
import android.os.Bundle;

import com.mgalala.noteall.service.resolver.StorageURIResolver;
import com.mgalala.noteall.service.resolver.URIResolver;
import com.mgalala.noteall.util.IntentType;

/**
 * Created by galala on 12/11/13.
 */
public class URIResolverFactory {
    private URIResolver uriResolver;

    public String getResourceURI(String intentType, Bundle bundle, Activity activity) {
        if (intentType.equals(IntentType.IMAGE.getType()) ||
                intentType.equals(IntentType.VIDEO.getType()) ||
                intentType.startsWith(IntentType.DOCUMENT.getType())) {
            uriResolver = new StorageURIResolver();
        } else if () {

        }

        return uriResolver.resolveURI(bundle, activity);

    }


}
