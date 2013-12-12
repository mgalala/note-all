package com.mgalala.noteall.service.resolver;


import android.app.Activity;
import android.os.Bundle;

/**
 * Created by galala on 12/12/13.
 */
public interface URIResolver {
    String resolveURI(Bundle bundle, Activity activity);
}
