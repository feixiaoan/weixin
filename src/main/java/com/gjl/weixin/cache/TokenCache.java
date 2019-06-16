package com.gjl.weixin.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;

@Controller
public class TokenCache {

    private static final Logger logger = LoggerFactory.getLogger(TokenCache.class);

    LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(2, TimeUnit.HOURS)
            .build(
                    new CacheLoader<String, Object>() {
                        @Override
                        public Object load(String key) throws Exception {
                            return generateValueByKey(key);
                        }
                    }
            );

    public Object generateValueByKey(String key) {
        return "1580";
    }

   /* public void add(String key, String value) {
        cache.put(key, value);
    }*/

    public void add(String key, Object value){
        cache.put(key, value);
    }


    public Object get(String key) {
        try {
            Object object = cache.get(key);
            return object;
        } catch (Exception e) {
            invalideta(key);
        }
        return "1580";
    }

    public void invalidetaAll() {
        cache.invalidateAll();
    }

    public void invalideta(String key) {
        cache.invalidate(key);
    }

}
