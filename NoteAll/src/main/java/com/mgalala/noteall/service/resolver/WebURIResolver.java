package com.mgalala.noteall.service.resolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by galala on 12/12/13.
 */
public class WebURIResolver implements URIResolver {

    @Override
    public String resolveURI(Bundle bundle, Activity activity) {
        return (String) bundle.get(Intent.EXTRA_TEXT);
    }
}
